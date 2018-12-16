<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        审核列表
    </div>
    <a-divider style="margin-bottom:-1px"/>
    <reviewtable/>
    <router-view></router-view>
  </div>
</template>

<script>
import reviewtable from '@/components/tables/reviewtable.vue'
import axios from "axios"


export default {
  name: 'reviewList',
  components:{
    reviewtable
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
                username:1,
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
