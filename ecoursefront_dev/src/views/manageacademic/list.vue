<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        教员列表
    </div>
    <a-divider style="margin-bottom:-2px"/>
    <academiclist/>
    <router-view></router-view>
  </div>
</template>

<script>
import academiclist from '@/components/tables/academictable.vue'
export default {
  name: 'manegeacademic',
  components:{
    academiclist
  }
  
}
</script>

<style>
</style>
