<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        申报课程
    </div>
    <a-divider />
    <declarecourse/>
    <router-view></router-view>
  </div>
</template>

<script>
import declarecourse from '@/components/forms/declarecourse.vue'
import axios from "axios"
export default {
  name: 'declareCourse',
  components:{
    declarecourse
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
                username:2,
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
