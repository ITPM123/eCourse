<template>
  <div>
    <div style="margin-bottom:2rem">
        <span style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        课件列表
        </span>
        <a-upload action="//jsonplaceholder.typicode.com/posts/" @change="handleUpload" :showUploadList="false">
            <!-- <a type="upload" > 点击上传</a> -->
            <a-button>
              <a-icon type="upload"/>点击上传
            </a-button>
        </a-upload>
    </div>
    <a-divider style="margin-bottom:0"></a-divider>
    <coursewaretable/>
    <router-view></router-view>
  </div>
</template>

<script>
import coursewaretable from '@/components/tables/coursewaretable.vue'
export default {
  name: 'courseware',
  components:{
    coursewaretable
  }
}
</script>

<style>
</style>
