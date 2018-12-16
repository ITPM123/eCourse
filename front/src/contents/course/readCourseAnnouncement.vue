<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        查看课程公告
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <classAnnouncementContent/>
    <router-view></router-view>
  </div>
</template>

<script>
import classAnnouncementContent from '@/components/conentWord/classAnnouncementContent.vue'
export default {
  name: 'readCourseAnn',
  components:{
    classAnnouncementContent
  }
}
</script>

<style>
</style>
