import Vue from 'vue'
import 'element-ui/lib/theme-chalk/index.css'

import {
  Button, Input, FormItem, Form, Select, Option, Row, Col, Message, Radio, Checkbox, DatePicker,RadioGroup
} from 'element-ui'

Message.install = function (Vue) {
  //替换js默认语法为elementui语法
  Vue.prototype.$message = Message
}


Vue.use(Button)
Vue.use(Input)
Vue.use(FormItem)
Vue.use(Form)
Vue.use(RadioGroup)
Vue.use(Select)
Vue.use(Option)
Vue.use(Row)
Vue.use(Col)
Vue.use(Message)
Vue.use(Radio)
Vue.use(Checkbox)
Vue.use(DatePicker)