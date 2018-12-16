<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        发布作业
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <publishHomework></publishHomework>
    <router-view></router-view>
  </div>
</template>

<script>
//学生作业列表
//import homeworktable from  '@/components/tables/homeworktable.vue'
import publishHomework from '@/components/forms/publishHomework.vue'
import axios from "axios"

export default {
  name: 'homeworkPublisher',
  components:{
    //homeworktable
    publishHomework
  },

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
