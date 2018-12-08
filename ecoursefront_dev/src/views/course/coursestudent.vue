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
import studenttable from '@/components/tables/studenttable.vue'

export default {
  name: 'class',
  components:{
      studenttable
  }
  
}
</script>

<style>
</style>
