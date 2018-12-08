<template>
<div style="margin:10% 30% 30%">
  <a-form :layout="formLayout">
    <a-form-item
      label='请输入原密码'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input type='password' class='oldpassword' placeholder='input placeholder' />
    </a-form-item>
    <a-form-item
      label='请输入新密码'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input type='password' class='newpassword' placeholder='input placeholder' />
    </a-form-item>
        <a-form-item
      label='请确认新密码'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input type='password' class='confirm' placeholder='input placeholder' />
    </a-form-item>
    <a-form-item
      :wrapperCol="buttonItemLayout.wrapperCol"
    >
      <a-button type='primary'>Submit</a-button>
    </a-form-item>
  </a-form>
</div>
</template>

<script>
export default {
  data () {
    return {
      formLayout: 'horizontal',
    }
  },
  methods: {

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