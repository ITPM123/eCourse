<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        编辑公告
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <editAnnouncementContent />
    <router-view></router-view>
  </div>
</template>

<script>
import editAnnouncementContent from '@/components/conentWord/editAnnouncementContent'

export default {
  name: 'AnnouncementEdit',
  components:{
    editAnnouncementContent
  }
}
</script>

<style>
</style>
