<template>
  <div>
    <div class="defaultColPadding">
      <v-btn 
        block 
        color="primary" 
        :disabled="isTaskStarted"
        v-show="!isTaskStarted"
        @click="startTodayTask">{{ startTodayTaskText }}</v-btn>
      <v-btn 
        v-show="isTaskStarted"
        block
        color="secondary"
        @click="restartTodayTask">reset</v-btn>
    </div>
    <v-row no-gutters>
      <v-col cols="12" xs="12" sm="5" class="defaultColPadding">
        <RandomTaskComp />
      </v-col>
      <v-col cols="12" xs="12" sm="7" class="defaultColPadding">
        <TaskComp 
          v-for="(task, index) in taskItems" 
          :key="index"
          :taskId="task.id" />
      </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { getModule } from 'vuex-module-decorators';
import AxiosService from '@/service/axios.service';
import AxiosResponse from '@/service/axios.service';

import tasks from '@/store/modules/tasks';
import TaskComp from '@/components/TaskComp.vue';
import RandomTaskComp from '@/components/RandomTaskComp.vue';

@Component({
  components: {TaskComp, RandomTaskComp},
})
export default class TodayTask extends Vue {
  get taskItems() {
    return tasks.taskList;
  }

  get isTaskStarted() {
    return tasks.isTaskStarted;
  }

  get startTodayTaskText() {
    if (this.isTaskStarted) 
      return 'Daily Todo started!'
    else 
      return 'Daily Todo start!'
  }

  public restartTodayTask() {
      tasks.restartTodayTask();
  }

  public async startTodayTask() {
    const response: AxiosResponse = await AxiosService.instance.get('/api/todayTask/start');
    if (response.status === 200) {
      tasks.setTodayTasks(response.data.tasks);
      tasks.startTodayTask();
    } else {
      alert('faile');
    }
  }
}
</script>

<style scoped>
.defaultColPadding {
  padding:5px;
}
</style>