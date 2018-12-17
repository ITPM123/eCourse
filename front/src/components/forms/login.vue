<script>
import { Form } from "ant-design-vue";
import axios from "axios";

const NormalLoginForm = {
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log("Received values of form: ", values);
          let that = this;
          let param = new FormData();
          param.append("username", values.userName);
          param.append("password", values.password);
          axios({
            url: "/login",
            method: "post",
            data: param
          }).then(response => {
            console.log(response);
            if ((response.data.errCode == 0)) {
              let c = response.data.data;
              // console.log(c);
              // c.role = c.role + 1;
              that.$store.commit("setUserInfo", c);
              that.$store.state.isLogin = true;
              that.$router.push("main");
            }
            else{
              console.log("用户不存在");
              that.$message.error("用户不存在");
            }
          });
        }
      });
    }
  },

  render() {
    const { getFieldDecorator } = this.form;
    return (
      <div style="width:40%;margin:10% 30% 30% ">
        <a-form
          id="components-form-demo-normal-login"
          onSubmit={this.handleSubmit}
          class="login-form"
        >
          <a-form-item>
            {getFieldDecorator("userName", {
              rules: [{ required: true, message: "请输入账号" }]
            })(
              <a-input
                prefix={
                  <a-icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />
                }
                placeholder="账号"
              />
            )}
          </a-form-item>
          <a-form-item>
            {getFieldDecorator("password", {
              rules: [{ required: true, message: "请输入密码" }]
            })(
              <a-input
                prefix={
                  <a-icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />
                }
                type="password"
                placeholder="密码"
              />
            )}
          </a-form-item>
          <a-form-item>
            {getFieldDecorator("remember", {
              valuePropName: "checked",
              initialValue: true
            })(<a-checkbox>记住我</a-checkbox>)}
            <a class="login-form-forgot" href="">
              忘记密码？
            </a>
            <a-button
              type="primary"
              htmlType="submit"
              class="login-form-button"
            >
              登录
            </a-button>
          </a-form-item>
        </a-form>
      </div>
    );
  }
};

export default Form.create()(NormalLoginForm);
</script>



<style>
#components-form-demo-normal-login .login-form {
  max-width: 300px;
}
#components-form-demo-normal-login .login-form-forgot {
  float: right;
}
#components-form-demo-normal-login .login-form-button {
  width: 100%;
}
</style>