<script>
import { Form } from 'ant-design-vue'

const CollectionCreateForm = Form.create()(
  {
    props: ['visible'],
    render () {
      const { visible, form } = this
      const { getFieldDecorator } = form
      return (
        <a-modal
          visible={visible}
          title='审核'
          okText='确认'
          cancelText='取消'
          onCancel={() => { this.$emit('cancel') }}
          onOk={() => { this.$emit('create') }}
        >
          <a-form layout='vertical'>
            <a-form-item label='审核反馈'>
              {getFieldDecorator('title', {
                rules: [{ required: true, message: '请输入审核反馈!' }],
              })(
                <a-input type='textarea' />
              )}
            </a-form-item>
            <a-form-item class='collection-create-form_last-form-item'>
              {getFieldDecorator('modifier', {
                initialValue: 'public',
              })(
                <a-radio-group>
                  <a-radio value='public'>通过</a-radio>
                  <a-radio value='private'>驳回</a-radio>
                </a-radio-group>
              )}
            </a-form-item>
          </a-form>
        </a-modal>
      )
    },
  }
)

export default {
  data () {
    return {
      visible: false,
    }
  },
  methods: {
    showModal () {
      this.visible = true
    },
    handleCancel  () {
      this.visible = false
    },
    handleCreate  () {
      const form = this.formRef.form
      form.validateFields((err, values) => {
        if (err) {
          return
        }

        console.log('Received values of form: ', values)
        form.resetFields()
        this.visible = false
      })
    },
    saveFormRef  (formRef) {
      this.formRef = formRef
    },
  },

  render () {
    return (
      <div>
        <a-button type='primary' onClick={this.showModal}>审核</a-button>
        <CollectionCreateForm
          wrappedComponentRef={this.saveFormRef}
          visible={this.visible}
          onCancel={this.handleCancel}
          onCreate={this.handleCreate}
        />
      </div>
    )
  },
}
</script>