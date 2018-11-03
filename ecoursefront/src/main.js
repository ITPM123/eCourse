import Vue from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
import VueRouter from 'vue-router'
import 'ant-design-vue/dist/antd.css'
import routes from './routes'
Vue.config.productionTip = false

Vue.use(Antd)
Vue.use(VueRouter)
const router=new VueRouter({
    routes
})
new Vue({
    router,
  render: h => h(App)
}).$mount('#app')