import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/views/login/index'
import Main from '@/views/login/main'

Vue.use(Router)

export default new Router({
  routes: [
    // 引入新建vue页面  path为路径
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/main',
      name: 'Main',
      component: Main
    }
  ]
})
