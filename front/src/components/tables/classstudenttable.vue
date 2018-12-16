<template>
  <div>
    <a-button @click="handleAdd">添加学生</a-button>
         <a-modal  title="添加学生"
                :visible="visible"
                 @ok="handleOk()"
                 :confirmLoading="confirmLoading"
                  @cancel="handleCancel" okText="确认" cancelText="取消" >
                   <a-input v-model="code" placeholder="输入学生学号" />
       </a-modal>

    <a-table bordered :dataSource="data" :columns="columns">
      <template slot="operation" slot-scope="text, record">
        <a-popconfirm title="确认删除"  @confirm="()=>onDelete(record,record.key)" okText="确认" cancelText="取消" >
          <a href="javascript:;">删除</a>
        </a-popconfirm>
      </template>
    </a-table>
  </div>
</template>


<script>
import axios from "axios"

const columns = [{
  title: '学生学号',
  dataIndex: 'code',
  width: '15%',
}, {
  title: '学生姓名',
  dataIndex: 'realName',
  width: '15%',
}, {
  title: '学院',
  dataIndex: 'school',
  width: '15%',
},{
  title: '专业',
  dataIndex: 'major',
  width: '20%',
},{
  title: '年级',
  dataIndex: 'grade',
  width: '10%',
}, {
  title: '选择操作',
  dataIndex: 'operation',
  scopedSlots: { customRender: 'operation' },
}]


export default {
  mounted(){
    this.init()
  },
  data () {
    return {
      data:[],
      columns,

      code:"",
      confirmLoading:false,
      visible:false,
    }
  },

  methods: {
    init(){
      // let url="?courseId="+this.$store.state.courseInfo.courseId
      let url="?courseId="+16
      axios({
        url:"/course/listStudent"+url,
        method:"get"
      }).then(response=>{
        console.log("获取班级学生列表");
        console.log(response);
        this.data=response.data.data
      })
    },

    onDelete (record,key) {
      const data = [...this.data]
      this.data = data.filter(item => item.key !== key)


      let url="?personId="+record.personId.toString()+"&courseId="+16
      console.log(record.personId.toString())
      console.log("删除班级学生")
      axios({
        url:"/course/removeStudent"+url,
        method:"get"
      }).then(response=>{
        console.log(response)
        
      })
    },

    handleAdd(record){
      this.visible = true

    },

    handleOk(){
      let that=this;
        this.visible=false;
        let param=new FormData();
        param.append("code",this.code);
        // param.append("courseId",this.$store.state.courseInfo.courseId);
        param.append("courseId",16);
        axios({
          url:"/course/addStudent",
          method:"post",
          data:param
        })
        .then(response=>{
          console.log(response)
          if(response.data.errCode==0){
            that.init();
          }
        })
    },

    handleCancel(){

    }
  }
}
</script>