import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VePie from 'v-charts/lib/pie.common.js'
import axios from 'axios'
//vue-axios是将axios集成到Vue.js的小包装器 
//注意：使用vue的插件的写法（vue-axios）更符合vue整体的生态环境，直接写原型链会有些粗暴
import VueAxios from 'vue-axios'
import global from './common.vue'
import './plugins/element.js'
import './assets/style.css'
import qs from 'qs'

//全局使用qs（obj转链接字符串）
Vue.prototype.$qs = qs

// 全局使用 将引入的全局设置文件绑定到Vue的COMMON属性上 在组件中就可以通过this.COMMON调用组件了
Vue.prototype.COMMON = global

// 引入v-charts
Vue.component(VePie.name, VePie)

// 全局使用axios
Vue.use(VueAxios, axios);
axios.defaults.baseURL = global.host;
// axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');

Vue.filter('identity', function (str) {
  switch (str) {
    case "student":
      return "学生";
    case "teacher":
      return "教师";
    case "admin":
      return "管理员";
  }
})

Vue.filter('toWeek', function (value) {
  switch (value) {
    case '1' | 1:
      return '周一';
    case '2' | 2:
      return '周二';
    case '3' | 3:
      return '周三';
    case '4' | 4:
      return '周四';
    case '5' | 5:
      return '周五';
  }
})

Vue.filter('proptype', function (value) {
  switch (value) {
    case '0':
      return '选修';
    case '1':
      return '必修';
  }
})

Vue.filter('semester', function (value) {
  switch (value) {
    case '1':
      return '大一上学期';
    case '2':
      return '大一下学期';
    case '3':
      return '大二上学期';
    case '4':
      return '大二下学期';
    case '5':
      return '大三上学期';
    case '6':
      return '大三下学期';
    case '7':
      return '大四上学期';
    case '8':
      return '大四下学期';
  }
})

Vue.filter('status', function (value) {
  switch (value) {
    case '1':
      return '未结课';
    case '2':
      return '已结课';
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
Vue.config.productionTip = false
