<template>
  <a-list
    :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 6, xxl: 4 }"
    :dataSource="data"
  >
    <a-list-item slot="renderItem" slot-scope="item, index">
      <template>
         <a-card hoverable style="margin:20%"> 
           <img alt="example" src="@/assets/logo.svg" slot="cover" />
           <a-card-meta :title="item.title" @click="clickhandle"/>
         </a-card> 
        </template>
    </a-list-item>
  </a-list>
</template>


<script>
const data = [
  {
    title: 'CLASS 1',
    src: '@/assets/logo.png'
  },
  {
    title: 'CLASS 2',
  },
  {
    title: 'CLASS 3',
  },
  {
    title: 'CLASS 4',
  },
  
]

export default {
  data () {
    return {
      data,
    }
  },
  methods:{
      clickhandle(){
        this.$router.push('student')
      }
  }
}
</script>
<style>

</style>