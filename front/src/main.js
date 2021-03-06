import Vue from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
//import Layout from 'ant-design-vue/lib/layout'
import VueRouter from 'vue-router'
import 'ant-design-vue/dist/antd.css'
import routes from './routes'
import axios from "axios"
import store from './store'
Vue.config.productionTip = false

Vue.use(Antd)
Vue.use(VueRouter)
axios.defaults.baseURL = "/api"
const router = new VueRouter({
  routes
})

router.beforeEach(function (to, from, next) {
  //未登录，跳转到登录页
  if (!store.state.isLogin) {
    if (to.path === '/login') {
      next()
    } else {
      next('/login')
    }
  }
  next();
})


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')