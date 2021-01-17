<template>
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
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { getModule } from 'vuex-module-decorators';

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
}
</script>

<style scoped>
.defaultColPadding {
  padding:5px;
}
</style>