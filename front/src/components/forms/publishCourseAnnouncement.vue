<template>
  <a-card>
    <a-form>
      <a-form-item label="公告标题" prop="title">
        <a-input v-model="titleName" placeholder="请输入标题"/>
      </a-form-item>
      <a-form-item label="公告内容" prop="content">
        <a-textarea
          v-model="content"
          @change="setAnnouncement"
          placeholder="请输入内容"
          :autosize="{ minRows: 20 }"
        />
      </a-form-item>

      <a-form-item/>

      <a-form-item>
        <a-button type="primary" @click="handleSubmit">发布</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script>
import axios from "axios";

export default {
  name: "createannounce",
  data() {
    return {
      formLayout: "horizontal",
      titleName: "",
      content: ""
    };
  },
  methods: {
    setAnnouncement: function() {
      this.$emit("transferAnnouncement", this.titlename);
    },

    /**处理提交 */
    handleSubmit: function() {
      
      let that=this
      let param = new FormData();
      param.append("courseId", this.$store.state.courseInfo.course_id);
      param.append("title", this.titleName);
      param.append("content", this.content);
      axios({
        url: "/courseAnnouncement/add",
        method: "post",
        data: param
      })
      .then(response=>{
        that.$store.commit("changeContent", "courseAnnounceList")
        // that.$store.commit('changeContent',"")
      })
    }
  }
};
</script>