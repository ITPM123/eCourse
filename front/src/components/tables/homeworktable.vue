<template>
  <a-table :columns="columns" :dataSource="data" >
    <template slot="action" slot-scope="text, record">
       <a @click="()=>handleView(record)">分数</a>
       <a-modal  title="作业分数" :visible="visible" @ok="handleOk" 
                :confirmLoading="confirmLoading" @cancel="handleCancel" okText="知道了">
            <p>分数：{{score}}</p>
            <p>评语：{{comment}}</p>
      </a-modal>

    <a-divider type="vertical" />

    <a-upload action="/homework/getCourseHomeworkList" :fileList="record.fileList"  :remove="handleRemove" 
    :beforeUpload="(file)=>beforeUpload(file,record)">
        <a @click="()=>view(record)">选择文件</a>
    </a-upload>

    <a class="upload-demo-start" type="primary"
      @click="()=>handleUpload(record)"
      :disabled="record.fileList.length === 0"
      :loading="record.uploading"
    >
      {{record.uploading ? '上传中' : '提交作业' }}
    </a>

    </template>
    <p slot="expandedRowRender" slot-scope="record" style="margin: 0">{{record.content.description}}</p>
  </a-table>
</template>



<script>
import axios from "axios"

const columns = [
  { title: '作业标题', dataIndex: 'content.homework_title', key: '' , width:'40%'},
  { title: '发布日期', dataIndex: 'content.release_time', key: '', width:'20%'},
  { title: '截止日期', dataIndex: 'content.deadline', key: '', width:'20%'},
  { title: '选择操作', dataIndex: '', key: 'x', width:'20%',scopedSlots: { customRender: 'action' } },
];


export default({
  name:'homeworktable',

  mounted(){
     this.init();
  },
  
  data() {
    return {
      data:[],
      columns,

      pagination:[],
      loading:false,
      // uploading:false,

      headers: {
        authorization: 'authorization-text',
      },

      fileList: [],
      uploading: false,
      score:"",
      comment:"",
      visible: false,
      confirmLoading: false,
    }
  },


  methods:{
    view(record){
      console.log(record.homwwork_id)
    },

    /**学生获取作业列表 */
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
         console.log("学生获取作业列表");
        console.log(response);
        for( let i=0;i<response.data.length;i++){
          let temp={
            "content":response.data[i],
            "fileList":[],
            uploading:false
          }
          this.data.push(temp)
        }
        //this.data=response.data;
      })
    },


    /** 确定操作*/
  handleOk() {
      this.ModalText = 'The modal will be closed after two seconds';
      this.visible = false;
      this.confirmLoading = true;
      setTimeout(() => {
        this.visible = false;
        this.confirmLoading = false;
      }, 200);
    },

    /**取消操作 */
    handleCancel() {
      // console.log('Clicked cancel button');
      this.visible = false;
    },

    /**查看分数 */
    handleView(record){
      this.visible = true
      console.log(record.content.homwwork_id);
      let param=new FormData();
      param.append("homework_id",record.content.homwwork_id);
      param.append("pageNumber",0);
      param.append("pageSize",10);
      axios({
        url:"/homework/getStudentHomeworkList",
        method:"post",
        data:param
      })
      .then(response=>{
        // console.log(response)
        // console.log(response.data)
        console.log(response.data)
        if(response.data.length!=0){
        this.score=response.data[0].score
        this.comment=response.data[0].comment
        }
      })
    },

    /**移除文件 */
    handleRemove(file) {
      let list=[...this.fileList]
      this.fileList=list.filter(item=>item.courseware_id!==key)
      const index = this.fileList.indexOf(file);
      const newFileList = this.fileList.slice();
      newFileList.splice(index, 1);
      this.fileList = newFileList;
      return ture;
    },

    /**预上传文件 */
    beforeUpload(file,record) {
      record.fileList = [...record.fileList, file]
      return false;
    },


    /**提交作业文件 */
    handleUpload(record) {
    console.log("上传文件");
      let param=new FormData();
       // param.append("homwwork_id",record.homwwork_id);
       param.append("homework_id",'6');
      // const { fileList } = this;
      const { fileList } = record.fileList;
      console.log(typeof(record.fileList))
      //fileList.forEach((file) => {
        param.append('attachment', record.fileList[0]);
      //});
      record.uploading = true
      axios({
        url: '/homework/uploadStudentHomework',
        method: 'post',
        processData: false,
        data: param,
      })
      .then(  ()=> {
          console.log("aa")
          record.fileList = []
          record.uploading = false
          // $message.success('提交成功');
        })
      .catch(() => {
          record.uploading = false
          // this.$message.error('上传失败');
      })
    }
  }
})
</script>