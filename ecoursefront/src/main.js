import Vue from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
//import Layout from 'ant-design-vue/lib/layout'
import VueRouter from 'vue-router'
import 'ant-design-vue/dist/antd.css'
import routes from './routes'

//Vue.component(Layout.name,Layout)
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