<template>
  <div>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        申报列表
    </div>
    <a-divider style="margin-bottom:-1px"/>
    <!-- <a-divider style="margin-bottom:0"></a-divider> -->
    <declaretable/>
    <router-view></router-view>
  </div>
</template>

<script>
import declaretable from '@/components/tables/declaretable.vue'
export default {
  name: 'declareList',
  components:{ 
    declaretable
  },
  methods:{

  }
}
</script>

<style>
</style>

