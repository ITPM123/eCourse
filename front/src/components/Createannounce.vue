<template>
 <a-card>
  <a-form>
    <a-form-item
      label='公告标题'
      prop="title"
    >
      <a-input v-model="titlename" 
      placeholder='请输入标题'/>
    </a-form-item>
    <a-form-item
      label='公告内容'
      prop="content"
    >
      <a-textarea v-model="connent" @change="setAnnouncement" 
       placeholder='请输入内容' :autosize="{ minRows: 20 }"/>
    </a-form-item>

    <a-upload action="/api/schoolAnnouncement/uploadFile" :multiple="true" :fileList="fileList" @change="handleChange">
    <a-button>
      <a-icon type="upload" /> 上传附件
    </a-button>
    </a-upload>

    <a-form-item/>

    <a-form-item>
      <a-button type='primary' @click="handleSubmit">发布</a-button>
    </a-form-item>
  </a-form>
 </a-card>
</template>

<script>



import axios from 'axios'
export default {
  name:'createannounce',
  data () {
    return {
      formLayout: 'horizontal',
      fileList: [{
        // uid: '-1',
        // name: 'xxx.png',
        // status: 'done',
        // url: 'http://www.baidu.com/xxx.png',
      }],
    }
  },
  methods: {
      setAnnouncement: function(){
      this.$emit('transferAnnouncement',this.titlename)

    },


      /**处理上传 */
      handleChange(info) {
      let fileList = info.fileList;

      // 1. Limit the number of uploaded files
      //    Only to show two recent uploaded files, and old ones will be replaced by the new
      fileList = fileList.slice(-2);

      // 2. read from response and show file link
      fileList = fileList.map((file) => {
        if (file.response) {
          // Component will show file.url as link
          file.url = file.response.url;
        }
        return file;
      });

      // 3. filter successfully uploaded files according to response from server
      fileList = fileList.filter((file) => {
        if (file.response) {
          return file.response.status === 'success';
        }
        return true;
      });

      this.fileList = fileList
    }, 

    /**处理提交 */
    handleSubmit: function () {
        axios({
            method: "post",
            url:"/login",
            headers:{
                // 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            },
            transformRequest:[function (data) {
                let newData = ''
                for (let k in data) {
                    newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
                }
                return newData
            }],
            data:{
                username:5205,
                password:123456
            }

        }).then(res=>{
            axios({
                method: 'get',
                url: '/courseAnnouncement/list?courseId=0',
            })
        })
    },
  },
}
</script>

<style>

</style>
