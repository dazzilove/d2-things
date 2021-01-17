<template>
  <v-app>
    <TodayTaskNavbar/>
    <v-main>
      <div style="margin: 15px;">
        <router-view></router-view>
      </div>
    </v-main>
    <TodayTaskFooter/>
  </v-app>
</template>

<script lang="ts">
import Vue from 'vue';
import TodayTaskNavbar from '@/components/TodayTaskNavbar.vue';
import TodayTaskFooter from '@/components/TodayTaskFooter.vue';
import TodayTask from '@/views/TodayTask.vue';

export default Vue.extend({
  name: 'App',

  components: {
    TodayTask, TodayTaskNavbar, TodayTaskFooter,
  },

  data: () => ({
    //
  }),
});
</script>


