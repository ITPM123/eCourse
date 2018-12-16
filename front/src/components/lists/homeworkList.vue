<template>
  <div>
    <a-button  @click="clickHandle" >+发布作业</a-button>
    <a-table bordered :dataSource="dataSource" :columns="columns">

      <template slot="operation" slot-scope="text, record">
            <a type="text" @click="()=>clickReview(record)" :style="{color: '#2E8DEF'}">批改</a>
      </template>
      <p slot="expandedRowRender" slot-scope="record" style="margin: 0">{{record.description}}</p>
    </a-table>
  </div>
</template>

<script>
import axios from "axios"

const columns=[{
        title: '作业标题',
        dataIndex: 'homework_title',
        width: '25%',
        scopedSlots: { customRender: 'homework_title' },
      }, {
        title: '截至日期',
        dataIndex: 'deadline',
         width: '25%',
        scopedSlots: { customRender: 'deadline' },
      }, {
        title: '发布日期',
        dataIndex: 'release_time',
        width: '25%',
        scopedSlots: { customRender: 'release_time' },
      },
      {
        title: '操作',
        dataIndex: 'operation',
        width: '20%',
        scopedSlots: { customRender: 'operation' },
      }]

export default {
  components: {

  },
  data () {
    return {
      dataSource:[],
      columns,
    }
  },

mounted(){
  this.init();
},

methods: {
  /*获取作业列表*/
    init(){
      let param= new FormData();
      // param.append("course_id",this.$store.state.courseInfo.course_id);
      param.append("course_id",14);
      
      // param.append("course_id",this.pageNumber);
      param.append("pageNumber",0);
      param.append("pageSize",10);
      axios({
        url:"/homework/getCourseHomeworkList",
        method:"post",
        data:param
      }).then(response=>{
        console.log("获取作业列表");
        console.log(response);
        this.dataSource=response.data;
      })
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


    // 删除作业
    onDelete (key) {
      const dataSource = [...this.dataSource]
      this.dataSource = dataSource.filter(item => item.key !== key)
    },
   
  /**跳转到审核学生作业页 */
    clickReview(record){
      console.log(record)
       this.$store.commit("setHomeworkInfo",record)//当前作业实例
       this.$store.commit('changeContent',"homeworkReviewer")//切换页面
    },

    /**发布作业 */
    clickHandle(){
        this.$store.commit('changeContent',"homeworkPublisher")
      },

      /**查看数据 */
    clickData(){
      //  this.$store.commit('changeContent',"dataHomework")
    }
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
