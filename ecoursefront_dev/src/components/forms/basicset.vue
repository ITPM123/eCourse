<template>
<div style="margin:10% 30% 30%">
    <!-- <div> -->
  <a-form :layout="formLayout">
    <a-form-item
      label='昵称'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input placeholder='输入昵称' />
    </a-form-item>
    <a-form-item
      label='个性签名'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input placeholder='i输入个性签名' />
    </a-form-item>
    <a-form-item
      label='联系方式'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input placeholder='输入联系方式' />
    </a-form-item>
        <a-form-item
      label='工作邮箱'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input placeholder='输入工作邮箱' />
    </a-form-item>
    <a-form-item
      :wrapperCol="buttonItemLayout.wrapperCol"
    >
      <a-button type='primary'>确认修改</a-button>
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