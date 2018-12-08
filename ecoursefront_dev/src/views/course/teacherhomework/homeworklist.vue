<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        课程作业
    </div>
    <a-divider/>
    <homework-list></homework-list>
    <router-view></router-view>
  </div>
</template>

<script>
//学生作业列表
//import homeworktable from  '@/components/tables/homeworktable.vue'
import homeworkList from '@/components/lists/homeworkList.vue'
export default {
  name: 'homeworklist',
  components:{
    //homeworktable
    homeworkList
  },
  methods:{
  }
}
</script>

<style>
</style>
