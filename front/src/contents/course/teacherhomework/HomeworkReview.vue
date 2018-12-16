<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        学生作业列表
    </div>
    <a-divider style="margin-bottom:-2px"/>
   <homeworkReview/>
    <router-view></router-view>
  </div>
</template>

<script>
//学生作业列表
//import homeworktable from  '@/components/tables/homeworktable.vue'
import homeworkReview from '@/components/lists/studentHomeworkList.vue'
export default {
  name: 'homeworkReviewer',
  components:{
    //homeworktable
    homeworkReview
  }
}
</script>

<style>
</style>
