import Vue from 'vue'
import Router from 'vue-router'
import global from './common.vue'
import noFound from './components/noFound'
import index from './components/index.vue'


// student
import student from './components/student/student.vue'
import studentCourse from './components/student/studentCourse.vue'
import chooseClass from './components/student/chooseClass.vue'
import studentGrade from './components/student/studentGrade.vue'
import studentInfo from './components/student/studentInfo.vue'


// teacher
import teacher from './components/teacher/teacher.vue'
import teacherCourse from './components/teacher/teacherCourse.vue'
import addCourse from './components/teacher/addCourse.vue'
import addClass from './components/teacher/addClass.vue'
import manageGrade from './components/teacher/manageGrade.vue'
import courseGrade from './components/teacher/courseGrade.vue'
import teacherInfo from './components/teacher/teacherInfo.vue'


//  admin
import admin from './components/admin/admin.vue'
import schoolInfo from './components/admin/schoolInfo.vue'
import manageStudent from './components/admin/manageStudent.vue'
import manageTeacher from './components/admin/manageTeacher.vue'
import manageFaculity from './components/admin/manageFaculity.vue'

Vue.use(Router)

var router = new Router({
  //默认哈希模式（路径带#号） 可以设置history模式（不带#号）
  // mode: "history",
  routes: [
    // 所有的没匹配到的 全部匹配404组件
    {
      path: '*',
      name: 'noFound',
      component: noFound
    },
    {
      path: '/',
      component: index
    },
    {
      path: '/student',
      redirect: '/student/studentCourse',
      component: student,
      children: [
        {
          path: 'studentCourse',
          component: studentCourse
        },
        {
          path: 'chooseClass',
          component: chooseClass
        },
        {
          path: 'studentGrade',
          component: studentGrade
        },
        {
          path: 'studentInfo',
          component: studentInfo
        }
      ]
    },
    {
      path: '/teacher',
      redirect: '/teacher/teacherCourse',
      component: teacher,
      children: [
        {
          path: 'teacherCourse',
          component: teacherCourse
        },
        {
          path: 'addCourse',
          component: addCourse
        },
        {
          path: 'addClass',
          component: addClass
        },
        {
          path: 'manageGrade',
          component: manageGrade
        },
        {
          path: 'manageGrade/:cid',
          component: courseGrade
        },
        {
          path: 'teacherInfo',
          component: teacherInfo
        },
      ]
    },
    {
      path: '/admin',
      redirect: '/admin/schoolInfo',
      component: admin,
      children: [
        {
          path: 'schoolInfo',
          component: schoolInfo
        },
        {
          path: 'manageStudent',
          component: manageStudent
        },
        {
          path: 'manageTeacher',
          component: manageTeacher
        },
        {
          path: 'manageFaculity',
          component: manageFaculity
        }
      ]
    }
  ]
})
// <router-link :to="...">（声明式）    ==     router.push(...)（编程式）
// 暴露完路由之后，在其他组件中可以通过 "this.$router.push('/路由名')" 进行转发

// 每次转发之前调用这个钩子函数
// 可作登录拦截
// 当检测不存在token的时候，直接转发到login页面
router.beforeEach((to, from, next) => {
  //路由中不能引用store 路由和Vuex均可在组件中使用
  if (sessionStorage.getItem('token') != null) {
    next();//放行
  } else {
    alert("no token!")
    window.location.href = global.login_location
    next();//放行
  }
})

export default router
