<template>
  <div>
    <setprofil/>
    <router-view></router-view>
  </div>
</template>

<script>
import setprofil from '@/components/tabs/setprofil.vue'
export default {
  name: 'setprofile',
  components:{
    setprofil
  }
}
</script>

<style>
</style>
