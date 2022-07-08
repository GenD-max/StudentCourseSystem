import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  // 公共state对象，存储所有组件的状态
  //Vuex里定义的变量相当于页面的局部变量 当页面做刷新操作就会消失 所以这里的state每次在消失后从session中取 改变其状态的话直接修改session
  state: null != window.sessionStorage.getItem("state") ? JSON.parse(window.sessionStorage.getItem("state")) : {
    id: '',//id各表的主键
    name: '',//登陆人的姓名
    identity: '',//身份 teacher student admin
    studentNum: '',
    teacherNum: '',
    courseNum: '',
    adminNum: '',
    token: ""//判断用户登录状态
  },
  // 唯一可以读取state值的方法，计算属性
  // 可以在其他组件中通过 "$store.getters.方法名" 获取状态信息
  getters: {
    getState(state) {
      return state;
    }
  },
  // 唯一可以修改state值得方法
  mutations: {
    setData(state, obj) {
      for (let key in obj) {
        if (obj[key] == null) {
          state[key] = "";
        } else {
          state[key] = obj[key].toString();
        }
      }
    },
    resetData(state) {
      for (let key in state) {
        state[key] = "";
      }
    },
  },
  //异步调用mutations方法
  // 可以通过 "this.$store.dispatch('方法名',参数)" 调用方法
  actions: {
    // context是上下文对象，mutations只能commit()调用，actions只能dispatch调用
    setLoginState(context, obj) {
      context.commit("setData", obj);
    },
    resetLoginState(context) {
      context.commit("resetData");
    }
  }
})
