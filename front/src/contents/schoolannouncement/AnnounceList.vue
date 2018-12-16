<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        公告列表
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <announcementtable/>
    <router-view></router-view>
  </div>
</template>

<script>
import announcementtable from '@/components/tables/announcementtable.vue'
import axios from 'axios'
export default {
  name: 'announceList',
  mounted(){
    this.init()
  },
  components:{
    announcementtable
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
                username:1,
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
