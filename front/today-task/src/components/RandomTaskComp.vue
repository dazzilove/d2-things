<template>
  <div>
    <div v-if="isRandomTaskShow"> 
      <v-card outlined v-if="isProgressShow">
        <v-img :src="'/image/' + randomTask.imgSrc" height="250px"></v-img>
        <v-card-title>
          <v-chip v-show="randomTask.state === 'done'" color="green" dark label small style="margin-right:5px">DONE</v-chip>
          <v-chip v-show="randomTask.state === 'except'" color="red" dark label small style="margin-right:5px">EXCEPT</v-chip>
          {{ randomTask.title }}
        </v-card-title>
        <v-card-subtitle>{{ randomTask.desc }}</v-card-subtitle>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn 
            outlined 
            color="primary" 
            small 
            :disabled="!isTaskStarted"
            @click="onClickExceptBtn(randomTask.id)">
            Except
          </v-btn>
          <v-btn 
            outlined 
            color="primary" 
            small 
            :disabled="!isTaskStarted"
            @click="onClickDoneBtn(randomTask.id)">
            Done
          </v-btn>
          <v-btn 
            outlined 
            color="primary" 
            small 
            :disabled="!isTaskStarted"
            @click="onClickRedoBtn(randomTask.id)">
            Redo
          </v-btn>
        </v-card-actions>
      </v-card>
      <v-card outlined v-else>
        <v-skeleton-loader
          type="image, list-item-two-line, actions"
        ></v-skeleton-loader>
      </v-card>
    </div>
    <div v-else>
      <v-card outlined>
        <v-img
          src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
          height="250px"
        ></v-img>
      </v-card>
    </div>

    <v-btn
      depressed
      color="primary"
      style="width: 100%; margin-top: 5px"
      :disabled="isTaskAllClear || !isTaskStarted"
      @click="onClickRandomBtn"
    >
      What to do? ({{clearTaskCount}}/{{allTaskCount}})
    </v-btn>

    <v-snackbar v-model="snackbar">
      {{ snackbarMessage }}
      <template v-slot:action="{ attrs }">
        <v-btn
          color="pink"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator';
import tasks from '@/store/modules/tasks';
import { Task } from '@/store/models';

const sleep = (delay: any) => new Promise((resolve) => setTimeout(resolve, delay));
const timer = async () => {
    await sleep(2000);
};

@Component
export default class RandomTaskComp extends Vue {
  public isRandomTaskShow: boolean = false;
  public isProgressShow: boolean = false;
  public randomTask!: Task;
  public allTaskCount: number = 0;

  public snackbar: boolean =  false;
  public snackbarMessage: string = '';

  public created() {
    this.allTaskCount = tasks.allTaskCount;
  }

  get clearTaskCount() {
    return tasks.clearTaskCount;
  }

  get isTaskAllClear() {
    return tasks.workList.length === 0;
  }

  get isTaskStarted() {
    return tasks.isTaskStarted;
  }

  @Watch('tasks')
  public onTaskListChange(newTasks: Task[], oldTasks: Task[]) {
    console.log('onTaskListChange');
  }

  public clearRandomTask() {
    this.isRandomTaskShow = false;
    this.randomTask = {};
  }

  public async changeRandomTask() {
    this.isProgressShow = false;
    if (this.randomTask) {
      await sleep(1000);
    }
    this.randomTask = this.getRandomTask();
    this.isProgressShow = true;
  }

  public getRandomTask() {
    const tempTasks = tasks.workList;
    const randomNumber = this.generateRandomNumber(1, tempTasks.length);
    this.isRandomTaskShow = true;
    return tempTasks[randomNumber - 1];
  }

  public generateRandomNumber(min: number, max: number) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }

  public onClickRandomBtn() {
    this.changeRandomTask();
  }

  public onClickExceptBtn(id: string) {
    this.changeTaskState(id, 'except');
  }

  public onClickDoneBtn(id: string) {
    this.changeTaskState(id, 'done');
  }

  public onClickRedoBtn(id: string) {
    this.changeTaskState(id, '');
  }

  public async changeTaskState(id: string, state: string) {
    await tasks.changeTaskState({id, state});
    if (tasks.isSuccessChange) {
      this.snackbarMessage = '상태가 변경되었습니다.';
    } else {
      this.snackbarMessage = '상태변경에 실패 했습니다. 목록을 재조회 합니다.';
      tasks.reloadTodayTasks();
    }
    this.snackbar = true;
    this.randomTask.state = state;
  }

}
</script>