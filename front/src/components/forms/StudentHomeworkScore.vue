<template>
  <div>
    <a-form :layout="formLayout">
      <a-form-item
        label="评分"
        :labelCol="formItemLayout.labelCol"
        :wrapperCol="formItemLayout.wrapperCol"
      >
        <a-input placeholder="请输入评分"/>
      </a-form-item>

      <a-form-item
        label="评语"
        :labelCol="formItemLayout.labelCol"
        :wrapperCol="formItemLayout.wrapperCol"
      >
        <a-input placeholder="请输入评语"/>
      </a-form-item>
      <a-form-item :wrapperCol="buttonItemLayout.wrapperCol"/>
    </a-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formLayout: "horizontal"
    };
  },
  methods: {
    handleFormLayoutChange(e) {
      this.formLayout = e.target.value;
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