<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        公告详情
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <announcementContent/>
    <router-view></router-view>
  </div>
</template>

<script>
import announcementContent from '@/components/conentWord/announcementContent.vue'
export default {
  name: 'annConent',
  components:{
    announcementContent
  }
}
</script>

<style>
</style>
