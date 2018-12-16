<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        发布课程公告
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <publishAnnouncement/>
    <router-view></router-view>
  </div>
</template>

<script>
import publishAnnouncement from '@/components/forms/publishCourseAnnouncement.vue'
export default {
  name: 'publishCourseAnn',
  components:{
    publishAnnouncement
  }
}
</script>

<style>
</style>
