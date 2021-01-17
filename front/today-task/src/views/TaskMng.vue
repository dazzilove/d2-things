<template>
  <div>
    <h1>Task Management</h1>
    <v-data-table
      :headers="headers"
      :items="gridData"
      :sort-by="['subject', 'title']"
      :sort-desc="[false, false]"
      multi-sort
    >
      <template v-slot:item.id="{ item }">
        <v-btn outlined small class="mr-1" @click="onClickDelTask(item.id)">DEL</v-btn>
        <v-btn outlined small class="mr-1" @click="onClickEditTask(item.id)">EDIT</v-btn>
        <v-btn outlined small color="primary" @click="onClickEditTask(item.id)">TODO</v-btn>
      </template>
    </v-data-table>

    <div style="position: fixed; bottom: 1rem; right: 1rem; z-index:1">
      <v-fab-transition>
        <v-btn
          color="pink"
          fab
          dark
          @click="onClickAddTask"
        ><v-icon>mdi-plus</v-icon></v-btn>
      </v-fab-transition>
    </div>
    
    <v-dialog
      v-model="isShowFormDialog"
      persistent
      max-width="600px"
    >
      <v-card>
        <v-card-title>
          <span class="headline">Task</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-select
                  :items="['Math', 'Korean', 'English']"
                  label="Subject*"
                  required
                  v-model="taskFormData.subject"
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Title*"
                  v-model="taskFormData.title"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Desc"
                  v-model="taskFormData.desc"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Image"
                  v-model="taskFormData.imgSrc"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn 
            color="blue darken-1" 
            text 
            @click="onClickDialogClose">
            Close
          </v-btn>
          <v-btn 
            v-if="!taskFormData.id"
            color="blue darken-1" 
            text             
            @click="onClickAddTaskProcess">
            Save
          </v-btn>
          <v-btn 
            v-if="taskFormData.id" 
            color="blue darken-1" 
            text 
            @click="onClickEditTaskProcess">
            Edit
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
      
    <v-dialog v-model="isShowAlertDialog" max-width="290" >
      <v-card>
        <v-card-title>{{ alertMessage }}</v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn 
            color="green darken-1" 
            text 
            @click="isShowAlertDialog = !isShowAlertDialog">
            OK
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { getModule } from 'vuex-module-decorators';
import AxiosService from '@/service/axios.service';
import AxiosResponse from '@/service/axios.service';

import tasks from '@/store/modules/tasks';
import { Task } from '@/store/models';


@Component({
  components: { },
})
export default class TaskMng extends Vue {
  public headers: any[] = [
    { text: 'Subject', value: 'subject' },
    { text: 'Title', value: 'title' },
    { text: 'Desc', value: 'desc' },
    { text: 'Management', value: 'id' },
  ];
  public gridData: any = [];
  public isShowFormDialog: boolean = false;
  public isShowAlertDialog: boolean = false;
  public alertMessage: string = '';
  public taskFormData: any = {
    id: '',
    subject: '',
    title: '',
    desc: '',
    imgSrc: 'default.jpg',
    delYn: '',
  };

  public created() {
    this.getTasks();
  }

  public async getTasks() {
    const response: AxiosResponse = await AxiosService.instance.get('/api/todayTask/tasks');
    if (response.status === 200) {
      this.gridData = response.data.filter((task: Task) => task.delYn === 'N' || task.delYn == null);
    } else {
      alert('faile');
    }
  }

  public async addTaskProcess() {
    const response: AxiosResponse = await AxiosService.instance.post('/api/todayTask/task', {
      subject: this.taskFormData.subject,
      title: this.taskFormData.title,
      desc: this.taskFormData.desc,
      imgSrc: this.taskFormData.imgSrc,
    });
    if (response.status === 200) {
      this.alertMessage = 'success';
      this.isShowAlertDialog = true;
      this.getTasks();
    } else {
      alert('faile');
    }
    this.onClickDialogClose();
  }

  public async editTaskProcess() {
    const response: AxiosResponse = await AxiosService.instance.post('/api/todayTask/task/edit', {
      id: this.taskFormData.id,
      subject: this.taskFormData.subject,
      title: this.taskFormData.title,
      desc: this.taskFormData.desc,
      imgSrc: this.taskFormData.imgSrc,
      delYn: this.taskFormData.delYn,
    });
    if (response.status === 200) {
      this.alertMessage = 'success';
      this.isShowAlertDialog = true;
      this.getTasks();
    } else {
      alert('faile');
    }
    this.onClickDialogClose();
  }

  public async delTaskProcess(id: string) {
    const response: AxiosResponse = await AxiosService.instance.post('/api/todayTask/task/delete', {
      id,
    });
    if (response.status === 200) {
      this.alertMessage = 'success';
      this.isShowAlertDialog = true;
      this.getTasks();
    } else {
      alert('faile');
    }
    this.onClickDialogClose();
  }

  public onClickAddTaskProcess() {
    this.addTaskProcess();
  }

  public onClickEditTaskProcess() {
    this.editTaskProcess();
  }

  public onClickAddTask() {
    this.resetTaskFromData();
    this.isShowFormDialog = true;
  }

  public onClickEditTask(id: string) {
    const targetTask: Task = this.gridData.filter((task: Task) => task.id === id)[0];
    this.taskFormData = {
      id: targetTask.id,
      subject: targetTask.subject,
      title: targetTask.title,
      desc: targetTask.desc,
      imgSrc: targetTask.imgSrc,
      delYn: 'N',
    };
    this.isShowFormDialog = true;
  }

  public onClickDelTask(id: string) {
    this.delTaskProcess(id);
  }

  public onClickDialogClose() {
    this.isShowFormDialog = false;
    this.resetTaskFromData();
  }

  public resetTaskFromData() {
    this.taskFormData = {
      id: '',
      subject: '',
      title: '',
      desc: '',
      imgSrc: 'default.jpg',
      delYn: 'N',
    };
  }

}
</script>
