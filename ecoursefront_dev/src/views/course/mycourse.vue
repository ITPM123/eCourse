<template>
  <div>
    <index/>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        我的课程
    </div>
    <a-divider />
    <coursecard/>
     <a-button @click="clickHandle">移动开发导论</a-button>
  </div>
</template>

<script>
import index from '@/components/Carousel/index.vue'
import coursecard from '@/components/lists/coursecard.vue'

export default {
  name: 'mycourse',
  components:{
    index,
    coursecard
  },
  methods: {
    clickHandle() {
      this.$router.push('courseinfo')
    }
  }
}
</script>

<style>
</style>
