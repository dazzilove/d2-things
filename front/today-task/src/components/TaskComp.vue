<template>
  <div>
    <v-card outlined style="margin-bottom:5px;">
      <v-list-item three-line>
        <v-list-item-content>
          <div class="overline mb-1">
            <v-chip v-show="task.state === 'done'" color="green" dark label small>DONE</v-chip>
            <v-chip v-show="task.state === 'except'" color="red" dark label small>EXCEPT</v-chip>
            {{ task.subject }}
          </div>
          <v-list-item-title class="mb-1">
            {{ task.title }}
          </v-list-item-title>
          <v-list-item-subtitle class="mb-1">{{ task.desc }}</v-list-item-subtitle>
          <v-list-item-group class="mt-2">
            <v-btn outlined x-small class="btnMargin" @click="onClickExceptBtn(task.id)">
              Except
            </v-btn>
            <v-btn outlined x-small class="btnMargin" @click="onClickDoneBtn(task.id)">
              Done
            </v-btn>
            <v-btn outlined x-small class="btnMargin" @click="onClickRedoBtn(task.id)">
              Redo
            </v-btn>
          </v-list-item-group>
        </v-list-item-content>
        <img 
          :src="'/image/' + task.imgSrc" 
          :title="task.title"
          style="width:80px">
      </v-list-item>
    </v-card>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import { Task } from '@/store/models';
import tasks from '@/store/modules/tasks';

@Component
export default class TaskComp extends Vue {
  @Prop() public taskId?: string;
  public task!: Task;

  public created() {
    this.resetTask();
  }

  public resetTask() {
    const tempTasks = tasks.allList;
    this.task = tempTasks.filter((item: Task) => item.id === this.taskId)[0];
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
    tasks.changeTaskState({id, state});
    this.task.state = state;
  }
}
</script>

<style scoped>
.btnMargin {
  margin-right:3px;
}
</style>