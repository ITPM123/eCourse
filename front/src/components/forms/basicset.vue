<template>
  <div style="margin:10% 30% 30%">
    <a-form :layout="formLayout">
      <a-form-item
        label="昵称"
        :labelCol="formItemLayout.labelCol"
        :wrapperCol="formItemLayout.wrapperCol"
      >
        <a-input v-model="userInfo.nickname" placeholder="输入昵称"/>
      </a-form-item>

      <a-form-item
        label="个性签名"
        :labelCol="formItemLayout.labelCol"
        :wrapperCol="formItemLayout.wrapperCol"
      >
        <a-input v-model="userInfo.motto" placeholder="输入个性签名"/>
      </a-form-item>

      <a-form-item
        label="联系方式"
        :labelCol="formItemLayout.labelCol"
        :wrapperCol="formItemLayout.wrapperCol"
      >
        <a-input v-model="userInfo.contact" placeholder="输入联系方式"/>
      </a-form-item>

      <a-form-item
        label="工作邮箱"
        :labelCol="formItemLayout.labelCol"
        :wrapperCol="formItemLayout.wrapperCol"
      >
        <a-input v-model="userInfo.email" placeholder="输入工作邮箱"/>
      </a-form-item>

      <a-form-item :wrapperCol="buttonItemLayout.wrapperCol">
        <a-button type="primary" @click="handleSubmit">确认修改</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      formLayout: "horizontal",
      userInfo:{
        nickname:null,
        motto: null,
        email: null,
        contact: null
      }
    };
  },
  mounted(){
    this.init();
    console.log(this.$store.state.userInfo)
    this.userInfo=this.$store.state.userInfo
  },

  methods: {
    /**获取用户个人信息 */
    init(){


    },
    handleSubmit() {
      let param = new FormData();
      let url =
        "nickname=" +
        this.userInfo.nickname +
        "&motto=" +
        this.userInfo.motto +
        "&email=" +
        this.userInfo.email +
        "&contact=" +
        this.userInfo.contact;
      axios({
        url: "/information/update?" + url,
        method: "get",
        data: param
      }).then(response => {
        console.log(response);
        console.log("更改个人信息");
      });
    }
  },

  computed: {
    formItemLayout() {
      const { formLayout } = this;
      return formLayout === "horizontal"
        ? {
            labelCol: { span: 4 },
            wrapperCol: { span: 14 }
          }
        : {};
    },

    buttonItemLayout() {
      const { formLayout } = this;
      return formLayout === "horizontal"
        ? {
            wrapperCol: { span: 14, offset: 4 }
          }
        : {};
    }
  }
};
</script>