import {
  getModule,
  Module,
  Mutation,
  Action,
  MutationAction,
  VuexModule,
} from 'vuex-module-decorators';
import store from '@/store';
import { Task } from '../models';

const name = 'tasks';
store.unregisterModule(name);

@Module({
  namespaced: true,
  name,
  store,
  dynamic: true,
})
class TasksModule extends VuexModule {
  public isTaskStarted: boolean = false;
  public todayTaskId: string = '';
  public taskList: Task[] = [
    // {
    //   id: '1',
    //   subject: 'Math',
    //   title: '쎈: 초등 수학 5-1',
    //   desc: '매일 2장',
    //   imgSrc: 'book1.jpg',
    //   state: '',
    // },
    // {
    //   id: '2',
    //   subject: 'Math',
    //   title: '한솔연산 MF2-2',
    //   desc: '매일 2장',
    //   imgSrc: 'book2.jpg',
    //   state: '',
    // },
    // {
    //   id: '3',
    //   subject: 'Korean',
    //   title: '뿌리깊은 초등국어 독해력 4단계',
    //   desc: '매일 2일',
    //   imgSrc: 'book3.jpg',
    //   state: '',
    // },
    // {
    //   id: '4',
    //   subject: 'Math',
    //   title: '기적의 수학 문장제 8',
    //   desc: '매일 2일',
    //   imgSrc: 'book4.jpg',
    //   state: '',
    // },
    // {
    //   id: '5',
    //   subject: 'Math',
    //   title: '최상위연산은수학이다 5A',
    //   desc: '매일 2장',
    //   imgSrc: 'book5.jpg',
    //   state: '',
    // },
  ];

  get allList() {
    return this.taskList;
  }

  get workList() {
    return this.taskList.filter((task: Task) => task.state !== 'done' && task.state !== 'except');
  }

  get clearTaskCount() {
    return this.taskList.filter((task: Task) => task.state === 'done' || task.state === 'except').length;
  }

  get allTaskCount() {
    return this.taskList.length;
  }

  @Mutation
  public changeState(param: any) {
    console.log('changeState');
    for (const task of this.taskList) {
      if (task.id === param.id) {
        task.state = param.state;
      }
      console.log('id=' + task.id +
        ', title=' + task.title +
        ', state=' + task.state);
    }
  }

  @Mutation
  public startTask() {
    this.isTaskStarted = true;
  }

  @Mutation
  public restartTask() {
    this.isTaskStarted = false;
  }

  @Mutation
  public setTasks(param: any) {
    this.taskList = param;
  }

  @Action({ commit: 'changeState' })
  public changeTaskState(param: any) {
    return { id: param.id, state: param.state };
  }

  @Action({ commit: 'startTask' })
  public startTodayTask() {
    return;
  }

  @Action({ commit: 'restartTask' })
  public restartTodayTask() {
    return;
  }

  @Action({ commit: 'setTasks' })
  public setTodayTasks(param: any) {
    return param;
  }

}

export default getModule(TasksModule);
