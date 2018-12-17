<template>
  <div>
    <!-- <div style="margin-bottom:2rem">
      <span style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">课件列表</span>
      <div>
        <a-upload
          action="/homework/getCourseHomeworkList"
          :fileList="fileList"
          :remove="handleRemove"
          :beforeUpload="beforeUpload"
        >
          <a-button style="margin:5px">选择文件</a-button>
        </a-upload>

        <a-button
          class="upload-demo-start"
          type="primary"
          style="margin:5px"
          @click="()=>handleUpload()"
          :disabled="fileList.length === 0"
          :loading="uploading"
        >{{uploading ? '上传中' : '上传课件' }}</a-button>
      </div>
    </div>
    <a-divider style="margin-bottom:0"></a-divider>-->
    <coursewaretable/>
    <router-view></router-view>
  </div>
</template>

<script>
import coursewaretable from "@/components/tables/coursewaretable.vue";
import axios from "axios";

export default {
  name: "courseware",

  components: {
    coursewaretable
  },

  data() {
    return {
      fileList: [],
      uploading: false
    };
  },
  computed: {
    isAdmin() {
      if (this.$store.state.userInfo.role == 0) {
        return true;
      } else {
        return false;
      }
    },

    isAcademic() {
      if (this.$store.state.userInfo.role == 1) {
        return true;
      } else {
        return false;
      }
    },
    isTeacher() {
      if (this.$store.state.userInfo.role == 2) {
        return true;
      } else {
        return false;
      }
    },
    isStudent() {
      if (this.$store.state.userInfo.role == 3) {
        return true;
      } else {
        return false;
      }
    },
    isTest() {
      if (this.$store.state.userInfo.role == -1) {
        //测试菜单时的权限
        return true;
      } else {
        return false;
      }
    }
  },

  methods: {
    handleRemove(file) {
      // console.log(zzz);
      const index = this.fileList.indexOf(file);
      const newFileList = this.fileList.slice();
      newFileList.splice(index, 1);
      this.fileList = newFileList;
      return true;
    },
    handleUpload() {
      let that = this;
      console.log(that.fileList[0]);
      let param = new FormData();
      param.append("course_id", this.$store.state.courseInfo.course_id);
      param.append("name", that.fileList[0].name);
      param.append("file", that.fileList[0]);
      param.append("visibility", "true");
      this.uploading = true;
      axios({
        url: "/courseware/uploadCourseware",
        method: "post",
        data: param
      })
        .then(() => {
          console.log("aa");
          that.fileList = [];
          that.uploading = false;
          // $message.success('提交成功');
        })
        .catch(() => {
          that.uploading = false;
          // this.$message.error('上传失败');
        });
    },

    beforeUpload(file) {
      this.fileList = [...this.fileList, file];
      return false;
    }
  }
};
</script>

<style>
</style>
