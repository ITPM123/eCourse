<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        教员列表
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <academiclist/>
    <router-view></router-view>
  </div>
</template>

<script>
import academiclist from '@/components/tables/academictable.vue'
import axios from "axios"

export default {
  name: 'academicList',
  components:{
    academiclist
  },
  mounted(){
        this.login();
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
                username:4,
                password:123456
                }
                }).then(res=>{
                    console.log(res)
                })
        }
        }
  
}
</script>

<style>
</style>
