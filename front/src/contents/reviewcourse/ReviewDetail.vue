<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        审核课程
    </div>
    <a-divider />
    <reviewdetai/>
    <router-view></router-view>
  </div>
</template>

<script>
import reviewdetai from '@/components/lists/reviewdetai.vue'
export default {
  name: 'reviewDetail',
  components:{
      reviewdetai
  },
  methods: {
 
  }
}
</script>

<style>
</style>
