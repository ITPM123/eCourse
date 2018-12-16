<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        班级学生列表
    </div>
    <a-divider />
    <studenttable/>
    <router-view></router-view>
  </div>
</template>

<script>
import studenttable from '@/components/tables/classstudenttable.vue'
import axios from "axios"

export default {
  name: 'classStudent',
  components:{
      studenttable
  }
  ,
  mounted(){
    this.init()
  },

  methods: {
    /**初始化 */
   init(){
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
                username:2,
                password:123456
                }
                }).then(res=>{
                    console.log(res)
                })
     },
  }
  
}
</script>

<style>
</style>
