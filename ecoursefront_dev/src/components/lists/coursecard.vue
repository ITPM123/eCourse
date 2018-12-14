<template>
  <a-list
    :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 6, xxl: 4 }"
    :dataSource="data"
  >
    <a-list-item slot="renderItem" slot-scope="item, index" @click="clickHandle">
      <template>
         <a-card hoverable style="margin:20%"> 
           <img alt="example" src="@/assets/logo.svg" slot="cover" />
           <a-card-meta :title="item.title"/>
         </a-card> 
        </template>
    </a-list-item>
  </a-list>
</template>


<script>
const data = [
  {
    title: 'VUE1',
    src: '@/assets/logo.png'
  },
  {
    title: 'VUE2',
  },
  {
    title: 'VUE3',
  },
  {
    title: 'VUE4',
  },
  
]

export default {
  data () {
    return {
      data,
    }
  },
  methods:{
      clickHandle(){
        this.$router.push('courseinfo')
      }
  }
}
</script>
<style>

</style>