<template>
  <div>
    <a-table bordered :dataSource="dataSource" :columns="columns">
      <template slot="name" slot-scope="text, record">
        <EditableCell :text="text" @change="onCellChange(record.key, 'name')"/>
      </template>
      <template slot="operation" slot-scope="text, record">
        <div>
            <a type="text" @click="clickReview" :style="{color: '#2E8DEF'}">审核</a>
            <a-divider type="vertical" />
            <a type="text" @click="clickData" :style="{color: '#2E8DEF'}">数据</a>
        <a-popconfirm
          v-if="dataSource.length"
          title="确认删除?"
          @confirm="() => onDelete(record.key)">
          <a-divider type="vertical" />
          <a>删除</a>
        </a-popconfirm>
          </div>
      </template>
      <p slot="expandedRowRender" slot-scope="record" style="margin: 0">{{record.description}}</p>
    </a-table>
  </div>
</template>
<script>
import axios from "axios"
//import EditableCell from '@/components/teacherPage/EditableCell'
/*
* EditableCell Code https://github.com/vueComponent/ant-design-vue/blob/master/components/table/demo/EditableCell.vue
*/
export default {
  components: {
    //EditableCell,
  },
  data () {
    return {
      dataSource: [{
        key: '0',
        homeworkNum: '001',
        homeworkTitle: '高斯滤波',
        deadline: '2018-11-30 23:59:59',
        publishDate:'2018-11-01 23:59:59',
        description:'fdhsufy',
      }, {
        key: '1',
        homeworkNum: '002',
        homeworkTitle: '维纳斯滤波',
        deadline: '2018-11-15 23:59:59',
        publishDate:'2018-10-30 23:59:59',
        description:'fdhsufy',
      }],
      count: 2,
      columns: [{
        title: '作业标题',
        dataIndex: 'homeworkTitle',
        width: '25%',
        scopedSlots: { customRender: 'homeworkTitle' },
      }, {
        title: '截至日期',
        dataIndex: 'deadline',
         width: '25%',
        scopedSlots: { customRender: 'deadline' },
      }, {
        title: '发布日期',
        dataIndex: 'publishDate',
        width: '25%',
        scopedSlots: { customRender: 'publishDate' },
      },
      {
        title: '操作',
        dataIndex: 'operation',
        width: '20%',
        scopedSlots: { customRender: 'operation' },
        
      }],
    }
  },
  methods: {
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
   
    clickReview(){
       this.$store.commit('changeContent',"homeworkReviewer")
    },
    clickData(){
       this.$store.commit('changeContent',"dataHomework")
    }
  },
  /*获取后端课程作业接口
  mounted(){
    axios({
      method:'get',
      url:'localhost:8081/homework/getCourseHomeworkList'+this.course_id
    }).then(res=>{
      this.dataSource=res.data
    })
  }*/
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
