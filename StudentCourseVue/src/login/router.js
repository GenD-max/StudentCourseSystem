import Vue from 'vue'
import Router from 'vue-router'
import login from './components/Login.vue'
import register from './components/Register.vue'


Vue.use(Router)
var router = new Router({
  routes: [
    // path name component
    { path: '/', redirect: '/login'},
    { path: '/login', name: 'login', component: login },
    { path: '/register', name: 'register', component: register },
  ]
})

export default router
