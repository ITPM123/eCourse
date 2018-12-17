<script>
import { Form } from "ant-design-vue";
import axios from "axios";

const Demo = {
  methods: {
    handleSubmit(e) {
      let param = new FormData();
      let that =this
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log("Received values of form: ", values);
        }
        param.append("realName", values.name);
        param.append("code", values.code);
        param.append("school", values.school);
        param.append("password", 123456);
        param.append("role", 0);
        param.append("sex", 1);
        param.append("title", "");
        param.append("nickname","");
        param.append("major", "");
        param.append("grade", "");
        param.append("class", "");
        param.append("contact", "");
        param.append("email", "");
        param.append("motto", "");
      });

      axios({
        url: "/acdemicDean/create",
        method: "post",
        data: param
      }).then(response => {
        if(response.data.errCode==0){
          console.log("添加教员")
          console.log(response);
          that.$message.success("添加成功");
          this.$store.commit('changeContent','academicList')
        }
        else{
          that.$message.error("添加教务员失败")
        }

      });
    }
  },
  render() {
    const { getFieldDecorator } = this.form;
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 }
    };

    return (
      <a-form
        id="components-form-demo-validate-other"
        onSubmit={this.handleSubmit}
      >
        <a-form-item {...{ props: formItemLayout }} label="姓名" hasFeedback>
          {getFieldDecorator("name", {
            rules: [
              { required: true },
              { whitespace: true, message: "姓名不能为空" },
              { max: 20, message: "姓名最大长度为20" }
            ]
          })(<a-input v-model="name" placeholder="请输入姓名" />)}
        </a-form-item>

        <a-form-item {...{ props: formItemLayout }} label="工号" hasFeedback>
          {getFieldDecorator("code", {
            rules: [
              { required: true },
              { whitespace: true, message: "工号不能为空" },
              {
                type: "string",
                pattern: new RegExp(/^[1-9]\d*$/, "g"),
                len: 12,
                message: "请输入12位数字学号"
              }
            ]
          })(<a-input v-model="code" placeholder="请输入工号" />)}
        </a-form-item>

        <a-form-item {...{ props: formItemLayout }} label="性别" hasFeedback>
          {getFieldDecorator("sex", {
            rules: [{ required: true, message: "请选择性别" }]
          })(
            <a-select v-model="sex" placeholder="请选择性别">
              <a-select-option value="man">男</a-select-option>
              <a-select-option value="female">女</a-select-option>
            </a-select>
          )}
        </a-form-item>

        <a-form-item {...{ props: formItemLayout }} label="学院" hasFeedback>
          {getFieldDecorator("school", {
            rules: [{ required: true, message: "请选择学院" }]
          })(
            <a-select v-model="school" placeholder="请选择学院">
              <a-select-option value="软件学院">软件学院</a-select-option>
              <a-select-option value="艺术学院">艺术学院</a-select-option>
            </a-select>
          )}
        </a-form-item>

        <a-form-item wrapperCol={{ span: 12, offset: 6 }}>
          <a-button type="primary" htmlType="submit">
            确认
          </a-button>
        </a-form-item>
      </a-form>
    );
  }
};

export default Form.create()(Demo);
</script>