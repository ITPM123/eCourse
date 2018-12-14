<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        申报表单
    </div>
    <a-divider />
    <declarecourse/>
    <router-view></router-view>
  </div>
</template>

<script>
import declarecourse from '@/components/forms/declarecourse.vue'
export default {
  name: 'declare',
  components:{
    declarecourse
  }
}
</script>

<style>
</style>
