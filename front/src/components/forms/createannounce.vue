<template>
  <div>
    <a-card>
      <a-form>
        <a-form-item label="公告标题" prop="title">
          <a-input v-model="titlename" placeholder="请输入标题"/>
        </a-form-item>

        <a-form-item label="公告内容" prop="content">
          <a-textarea
            v-model="content"
            @change="setAnnouncement"
            placeholder="请输入内容"
            :autosize="{ minRows: 20 }"
          />
        </a-form-item>

        <!-- <a-upload
          action="/api/schoolAnnouncement/uploadFile"
          :multiple="true"
          :fileList="fileList"
          @change="handleChange"
        >
          <a-button>
            <a-icon type="upload"/>上传附件
          </a-button>
        </a-upload> -->

        <a-form-item/>

        <a-form-item>
          <a-button type="primary" @click="handleSubmit">发布</a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "createannounce",

  data() {
    return {
      formLayout: "horizontal",
      titlename: " ",
      content: " ",
      fileList: []
    };
  },
  methods: {
    setAnnouncement: function() {
      this.$emit("transferAnnouncement", this.titlename);
    },

    /**处理上传 */
    handleChange(info) {
      let fileList = info.fileList;
      fileList = fileList.slice(-2);
      fileList = fileList.map((file) => {
        if (file.response) {
          file.url = file.response.url;
        }
        return file;
      });
      fileList = fileList.filter((file) => {
        if (file.response) {
          return file.response.status === 'success';
        }
        return true;
      });
      this.fileList = fileList
    },

    /**处理提交 */
    handleSubmit: function() {
      this.$store.commit("changeContent", "announceList");
      let param = new FormData();
      param.append("title", this.titlename);
      param.append("content", this.content);
      param.append("attachment1", "");
      axios({
        url: "/schoolAnnouncement/add",
        method: "post",
        data: param
      });
    }
  }
};
</script>

<style>
</style>
