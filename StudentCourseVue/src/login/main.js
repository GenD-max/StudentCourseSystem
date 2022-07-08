import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import global from '../common.vue'
import './plugins/element'
import qs from 'qs'

// 将引入的全局设置文件绑定到Vue的COMMON属性上
Vue.prototype.COMMON = global
//设置全局属性使用qs qs可以将json对象转化为url的拼接字符串 用来发送请求
Vue.prototype.$qs = qs //此处操作是将qs包绑定到vue的属性上 属性名为qs 此后可以通过this.$qs引用qs工具包

// 设置axios
Vue.use(VueAxios, axios);
axios.defaults.baseURL = global.host;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
Vue.config.productionTip = false