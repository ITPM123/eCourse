<template>
  <div>
    我是home
    <login />
    <router-view></router-view>
  </div>
</template>

<script>
import login from '@/components/forms/login.vue'
export default {
  name: 'home',
  components:{
    login
  }
}
</script>

<style>
</style>
