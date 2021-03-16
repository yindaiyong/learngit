import Vue from 'vue'
import VueRouter from 'vue-router'
import Cookies from "js-cookie"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('views/login/index.vue')
    },
    {
        path: '/home',
        name: 'home',
        component: ()=>import('views/home/index.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '*',
        name: '404',
        component: () => import('views/error/404.vue')
    }
]

const router = new VueRouter({
  mode: 'history',
  routes
})
// 修改路由重复点击错误
const originalPush = VueRouter.prototype.push
   VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
router.beforeEach((to, from, next) => {
    let token = Cookies.get('AuthToken')
    if (to.meta.requiresAuth) {
        if (token) {
            next()
        } else {
            // 是否跳转 /back/login
            // var reg = RegExp(/back/);
            // var jumpPath = '/login';
            // if(to.fullPath.match(reg)){
            //     jumpPath = '/back/login';
            // }
            // 
            next({
                path: '/login', // path: jumpPath,
                query: { redirect: to.fullPath }
            })
        }
    } else {
        next()
    }
})

export default router