import Vue from 'vue'
import Antd from 'ant-design-vue'
import router from './router'
import store from './store'
import App from './App.vue'
import './mock'
import 'ant-design-vue/dist/antd.css'
import './index.less'
import axios from 'axios'
Vue.prototype.$http = axios
Vue.config.productionTip=false
Vue.use(Antd)

// axios.defaults.baseURL='/api'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
