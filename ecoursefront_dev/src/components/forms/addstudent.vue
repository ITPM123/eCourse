<script>
import { Form } from 'ant-design-vue'

const residences = [{
  value: 'sc',
  label: '软件学院',
  children: [{
    value: 'se',
    label: '软件工程',
  }],
}, {
  value: 'csc',
  label: '计算机学院',
  children: [{
    value: 'cs',
    label: '计算机科学'
    },
    {
     value: 'ns',
    label: '网络安全'
    },
    {
    value: 'is',
    label: '信息安全'
    }],
}]



const Demo = {
  methods: {
    handleSubmit  (e) {
      e.preventDefault()
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values)
        }
      })
    },
},
  render () {
    const { getFieldDecorator } = this.form
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
    }
    return (
      <a-form id='components-form-demo-validate-other' onSubmit={this.handleSubmit}>

        <a-form-item
         {...{ props: formItemLayout }}
          label='姓名'
          hasFeedback
        >
          {getFieldDecorator('name', {
            rules: [{ required: true}, {whitespace: true,message:'姓名不能为空'},{ max:20, message: '姓名最大长度为20'}],
          })(
            <a-input placeholder='请输入姓名' />
          )}
        </a-form-item>

        <a-form-item
         {...{ props: formItemLayout }}
          label='学号'
          hasFeedback
        >
          {getFieldDecorator('id', {
            rules: [{  required: true},{whitespace: true, message:'学号不能为空'},{type:'string', pattern: new RegExp(/^[1-9]\d*$/, "g"), len: 12, message: '请输入12位数字学号'}],
          })(
            <a-input placeholder='请输入学号'/>
          )}
        </a-form-item>

        <a-form-item
          {...{ props: formItemLayout }}
          label='学院&专业'
          hasFeedback
        >
          {getFieldDecorator('residence', {
            // initialValue: [],
            rules: [{ type: 'array',required: true, message: '请选择学院和专业' }],
          })(
            <a-cascader placeholder='请选择学院和专业' options={residences} />
          )}
        </a-form-item>

        <a-form-item
          {...{ props: formItemLayout }}
          label='年级'
          hasFeedback
        >
          {getFieldDecorator('grade', {
            rules: [
              { required: true, message: '请选择年级' },
            ],
          })(
            <a-select placeholder='请选择年级'>
              <a-select-option value='2014'>2014</a-select-option>
              <a-select-option value='2015'>2015</a-select-option>
              <a-select-option value='2016'>2016</a-select-option>
              <a-select-option value='2017'>2017</a-select-option>
              <a-select-option value='2018'>2018</a-select-option>
              <a-select-option value='2019'>2019</a-select-option>
            </a-select>
          )}
        </a-form-item>

        

        <a-form-item
          wrapperCol={{ span: 12, offset: 6 }}
        >
          <a-button type='primary' htmlType='submit'>确认</a-button>
        </a-form-item>
      </a-form>
    )
  },
}

export default Form.create()(Demo)
</script>

<style>
</style>