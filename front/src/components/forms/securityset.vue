<template>
<div style="margin:10% 30% 30%">
  <a-form :layout="formLayout">
    <a-form-item label='请输入原密码' :labelCol="formItemLayout.labelCol" :wrapperCol="formItemLayout.wrapperCol">
      <a-input v-model="old_Password" type='password' placeholder='请输入原密码' />
    </a-form-item>

    <a-form-item label='请输入新密码' :labelCol="formItemLayout.labelCol" :wrapperCol="formItemLayout.wrapperCol">
      <a-input v-model="new_Password" type='password' placeholder='请输入新密码' />
    </a-form-item>

    <a-form-item  label='请确认新密码'  :labelCol="formItemLayout.labelCol" :wrapperCol="formItemLayout.wrapperCol">
      <a-input type='password' class='confirm' placeholder='请确认新密码' />
    </a-form-item>

    <a-form-item :wrapperCol="buttonItemLayout.wrapperCol"  >
      <a-button type='primary' @click="handleSubmit">提交</a-button>
    </a-form-item>
  </a-form>
   
</div>
</template>

<script>
import axios from "axios"

export default {
  data () {
    return {
      formLayout: 'horizontal',
      old_Password:'',
      new_Password:'',
    }
  },

  methods: {
    handleSubmit(){
      let param=new FormData();
      param.append("oldPassword",this.old_Password);
      param.append("newPassword",this.new_Password);
      axios({
        url:"/information/updatePassword",
        method:"post",
        data:param
      }).then(response=>{
        console.log(response)
        console.log("更改密码")
      })
    }
  },


  computed: {
    formItemLayout () {
      const { formLayout } = this
      return formLayout === 'horizontal' ? {
        labelCol: { span: 4 },
        wrapperCol: { span: 14 },
      } : {}
    },

    buttonItemLayout () {
      const { formLayout } = this
      return formLayout === 'horizontal' ? {
        wrapperCol: { span: 14, offset: 4 },
      } : {}
    },
  },
}
</script>