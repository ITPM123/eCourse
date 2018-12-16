<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        课程公告
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <courseAnnouncementTable/>
    <!-- <router-view></router-view> -->
  </div>
</template>

<script>
import courseAnnouncementTable from '@/components/tables/courseAnnouncementTable.vue'
import axios from "axios"
export default {
  name: 'courseAnnounceList',
  components:{
    courseAnnouncementTable
  },
  mounted(){
    this.init()
  
  },
  methods:{
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
