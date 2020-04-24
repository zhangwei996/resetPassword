import Vue from 'vue'
import Router from 'vue-router'

// 路由懒加载

const ClassStu = () => import('../page/classStu.vue')
const Main = () => import('../page/main.vue');
const Blank = () => import('../page/Blank.vue');

Vue.use(Router)

var routes = [

  {
    path: '/classStu',
    component: ClassStu,
  },
  {
    path: '/main',
    component: Main,
    meta: {
      title: '学生密码重置'
    },
  },
  {
    path: '/Blank',
    component: Blank,
  }

]

export default new Router({
  routes
})
