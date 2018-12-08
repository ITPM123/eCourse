<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<template>
      <div>
      <div>
       <a-table bordered :dataSource="dataSource" :columns="columns">
      <template slot="name" slot-scope="text, record">
        <EditableCell :text="text" @change="onCellChange(record.key, 'name')"/>
      </template>
      <template slot="operation" slot-scope="text, record">
         <div>
    <div v-if="homeworkState">
    <el-button type="text" @click="showModal" :style="{color: '#2E8DEF'}">查看</el-button></div>
    <div  v-else>
    <el-button type="text" @click="showModal" :style="{color: '#2E8DEF'}">审核</el-button></div>
    <a-modal
      title="学生作业分数"
      :visible="visible"
      @ok="handleOk"
      :confirmLoading="confirmLoading"
      @cancel="handleCancel"
    >
   <div>
  <a-form :layout="formLayout">
    <a-form-item
      label='评分'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input v-model="stuScore" placeholder='输入学生作业评分' />
    </a-form-item>
    <a-form-item
      label='评语'
      :labelCol="formItemLayout.labelCol"
      :wrapperCol="formItemLayout.wrapperCol"
    >
      <a-input v-model="stuCommand" placeholder='输入学生作业评语' />
    </a-form-item>
    <a-form-item
      :wrapperCol="buttonItemLayout.wrapperCol"
    >
    </a-form-item>
  </a-form>
</div>
    </a-modal>
  </div>
      </template>
    </a-table>
  </div>
      </div>
</template>
<script>
export default {
    name:"homeworkReview",
    components: {
  },
  data(){
    return {
    dataSource: [{
        key: '0',
        studentNum: '201630665001',
        studentName: '张平',
        state: '未审核',
        homeworkFile:'file1',
        submitDate:'2018-10-21 08:23:32',
        homeworkState:1,
      }, {
        key: '1',
        studentNum: '201630665002',
        studentName: '李四',
        state: '已审核',
        homeworkFile:'file2',
        submitDate:'2018-10-23 08:23:32',
        homeworkState:1,
      }],
      count: 2,
      columns: [{
        title: '学生学号',
        dataIndex: 'studentNum',
        width: '15%',
        scopedSlots: { customRender: 'studentNum' },
      }, {
        title: '姓名',
        dataIndex: 'studentName',
         width: '10%',
        scopedSlots: { customRender: 'studentName' },
      }, {
        title: '状态',
        dataIndex: 'state',
        width: '10%',
        scopedSlots: { customRender: 'state' },
      },
       {
        title: '作业文件',
        dataIndex: 'homeworkFile',
        width: '20%',
        scopedSlots: { customRender: 'homeworkFile' },
      },
       {
        title: '提交时间',
        dataIndex: 'submitDate',
        width: '15%',
        scopedSlots: { customRender: 'submitDate' },
      },
      {
        title: '操作',
        dataIndex: 'operation',
        width: '10%',
        scopedSlots: { customRender: 'operation' },
        
      }],
      ModalText: 'Content of the modal',
      visible: false,
      confirmLoading: false,
      formLayout: 'horizontal',
    }
  },
    methods: {
    handleFormLayoutChange  (e) {
      this.formLayout = e.target.value
    },
    onCellChange (key, dataIndex) {
      return (value) => {
        const dataSource = [...this.dataSource]
        const target = dataSource.find(item => item.key === key)
        if (target) {
          target[dataIndex] = value
          this.dataSource = dataSource
        }
      }
    },
    onDelete (key) {
      const dataSource = [...this.dataSource]
      this.dataSource = dataSource.filter(item => item.key !== key)
    },
    handleAdd () {
      const { count, dataSource } = this
      const newData = {
        key: count,
        studentNum: `201630660${count+1} `,
        studentName: '肖奈',
        state:'未审核',
        homeworkFile:'',
        submitDate:'2018-10-23 08:23:32'
      }
      this.dataSource = [...dataSource, newData]
      this.count = count + 1
    },
    showModal() {
      this.visible = true
    },
    handleOk(e) {
      this.ModalText = 'The modal will be closed after two seconds';
      this.confirmLoading = true;
      setTimeout(() => {
        this.visible = false;
        this.confirmLoading = false;
      }, 500);
    },
    handleCancel(e) {
      console.log('Clicked cancel button');
      this.visible = false
    },
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
<style>
.editable-cell {
  position: relative;
}

.editable-cell-input-wrapper,
.editable-cell-text-wrapper {
  padding-right: 24px;
}

.editable-cell-text-wrapper {
  padding: 5px 24px 5px 5px;
}

.editable-cell-icon,
.editable-cell-icon-check {
  position: absolute;
  right: 0;
  width: 20px;
  cursor: pointer;
}

.editable-cell-icon {
  line-height: 18px;
  display: none;
}

.editable-cell-icon-check {
  line-height: 28px;
}

.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}

.editable-cell-icon:hover,
.editable-cell-icon-check:hover {
  color: #108ee9;
}

.editable-add-btn {
  margin-bottom: 8px;
}
</style>
