import Vue from 'vue';
import VueRouter, { RouteConfig } from 'vue-router';
import TodayTask from '@/views/TodayTask.vue';

Vue.use(VueRouter);

const routes: RouteConfig[] = [
  {
    path: '/',
    name: 'Main',
    component: TodayTask,
  },
  {
    path: '/todayTask',
    name: 'TodayTask',
    component: TodayTask,
  },
  {
    path: '/taskMng',
    name: 'TaskMng',
    component: () => import('@/views/TaskMng.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
