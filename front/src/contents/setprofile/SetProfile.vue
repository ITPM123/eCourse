<template>
  <div>
    <setprofil/>
    <router-view></router-view>
  </div>
</template>

<script>
import setprofil from '@/components/tabs/setprofil.vue'
import axios from "axios"

export default {
  name: 'setProfile',

  components:{
    setprofil
  },

  mounted(){
    this.login()
  },
  methods:{
    login(){
      axios({
        method:"post",
        url:"/login",
        transformRequest:[function (data) {
           let newData = ''
           for (let k in data) {
              newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
              }
              return newData
              }],
              data:{
                username:3,
                password:123456
                }
                }).then(response=>{
                    console.log(response)
                })
    }
  }
}
</script>

<style>
</style>
