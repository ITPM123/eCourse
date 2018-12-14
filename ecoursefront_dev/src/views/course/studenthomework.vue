<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        课程作业
    </div>
    <a-divider style="margin-bottom:-1px"/>
    <homeworktable></homeworktable>
    <router-view></router-view>
  </div>
</template>

<script>
//学生作业列表
import homeworktable from  '@/components/tables/homeworktable.vue'
export default {
  name: 'studentHomework',
  components:{
    homeworktable
  },
  methods:{
  }
}
</script>

<style>
</style>
