<template>
  <div>
    <index/>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        课程班级
    </div>
    <a-divider />
    <courseclass/>
    <router-view></router-view>
  </div>
</template>

<script>
import index from '@/components/Carousel/index.vue'
import courseclass from '@/components/lists/courseclass.vue'

export default {
  name: 'course',
  components:{
    index,
    courseclass
  },
  methods: {
  }
}
</script>

<style>
</style>
