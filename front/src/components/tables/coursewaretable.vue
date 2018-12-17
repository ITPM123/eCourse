<template>
  <div>
    <span style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">课件列表</span>
    <div style="margin-bottom:2rem" v-if="isTeacher">
      <div>
        <a-upload
          action="/homework/getCourseHomeworkList"
          :fileList="fileList"
          :remove="handleRemove"
          :beforeUpload="beforeUpload"
        >
          <a-button style="margin:5px">选择文件</a-button>
        </a-upload>

        <a-button
          class="upload-demo-start"
          type="primary"
          style="margin:5px"
          @click="()=>handleUpload()"
          :disabled="fileList.length === 0"
          :loading="uploading"
        >{{uploading ? '上传中' : '上传课件' }}</a-button>
      </div>

      <a-divider style="margin-bottom:0"></a-divider>

      <a-table :columns="teacherColumns" :dataSource="list">
        <!-- <span slot="aid" slot-scope="text">{{text}}</span> -->
        <span slot="visibility" slot-scope="text,record">
          <a-tag v-if="text" color="#1ABC9C" :key="0">可见</a-tag>
          <a-tag v-else color="#E74C3C" :key="1">不可见</a-tag>
        </span>
        
        <span slot="action" slot-scope="record">
          <a @click="handleChange(record.courseware_id)">切换可见性</a>
          <a-divider type="vertical"/>
          <a :href="'/api/download/Courseware/'+record.file_name">下载</a>
          <a-divider type="vertical"/>
          <a @click="onDelete(record.courseware_id)">删除</a>

          <!-- <a-popconfirm  v-if="list.length"
                title="确认删除？"
                okText="确认"
                cancelText="取消"
                @confirm="()=>onDelete(record.courseware_id)">
                    <a>删除</a>
          </a-popconfirm>-->
        </span>
      </a-table>
    </div>
    <a-table v-else :columns="studentColumns" :dataSource="list">
      <span slot="aid" slot-scope="text">{{text}}</span>
      <span slot="action" slot-scope="text,record">
        <a :href="'/api/download/Courseware/'+record.file_name">下载</a>
      </span>
    </a-table>
  </div>
</template>

<script>
import axios from "axios";

const teacherColumns = [
  {
    title: "课件名称",
    dataIndex: "courseware_name",
    key: "courseware_name"
  },
  {
    title: "上传时间",
    dataIndex: "upload_date",
    key: "upload_date"
  },
  {
    title: "可见性",
    dataIndex: "visibility",
    scopedSlots: { customRender: "visibility" }
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];

const studentColumns = [
  {
    title: "课件名称",
    dataIndex: "courseware_name",
    key: "courseware_name"
  },
  {
    title: "上传时间",
    dataIndex: "upload_date",
    key: "upload_date"
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];

var data = [
  {
    title: "first",
    visibility: true,
    key: "0"
  },
  {
    title: "second",
    visibility: true,
    key: "1"
  },
  {
    title: "third",
    visibility: false,
    key: "2"
  }
];

export default {
  name: "coursewaretable",
  mounted() {
    this.init();
  },

  data: function() {
    return {
      list: [],
      teacherColumns,
      studentColumns,
      fileList: [],
      uploading: false
    };
  },

  methods: {
    init() {
      let that = this;
      console.log(this.$store.state.userInfo.role);
      let param = new FormData();
      param.append("course_id", this.$store.state.courseInfo.course_id);
      // param.append("pageSize",this.pageSize);
      //param.append("course_id",16);
      param.append("pageNumber", 0); //
      param.append("pageSize", 10); //
      setTimeout(function() {
        axios({
          url: "/courseware/getCoursewareList",
          method: "post",
          data: param
        }).then(response => {
          console.log("课件列表");
          // console.log(that.list)
          that.list = response.data;
          console.log(that.list);
        });
      }, 1000);
    },

    //删除列表项
    onDelete(key) {
      const list = [...this.list];
      this.list = list.filter(item => item.courseware_id !== key);

      let param = new FormData();
      param.append("courseware_id", key);
      axios({
        url: "/courseware/deleteCourseware",
        method: "post",
        data: param
      }).then(response => {
        console.log(response);
      });
    },

    //更改可见性
    handleChange(key) {
      let list = [...this.list];
      for (var i = 0; i < list.length; i++) {
        if (list[i].courseware_id == key) {
          if (list[i].visibility == true) {
            list[i].visibility = false;

            let param = new FormData();
            param.append("courseware_id", key);
            param.append("visibility", false);
            axios({
              url: "/courseware/modifyCourseware",
              method: "post",
              data: param
            }).then(response => {
              console.log("切换为不可见");
              console.log(response);
            });
          } else {
            list[i].visibility = true;

            let param = new FormData();
            param.append("courseware_id", key);
            param.append("visibility", true);
            axios({
              url: "/courseware/modifyCourseware",
              method: "post",
              data: param
            }).then(response => {
              console.log("切换为可见");
              console.log(response);
            });
          }
          break;
        }
      }
      this.list = list;
    },
    handleRemove(file) {
      console.log(zzz);
      const index = this.fileList.indexOf(file);
      const newFileList = this.fileList.slice();
      newFileList.splice(index, 1);
      this.fileList = newFileList;
      return true;
    },
    handleUpload() {
      let that = this;
      console.log(that.fileList[0]);
      let param = new FormData();
      param.append("course_id", this.$store.state.courseInfo.course_id);
      param.append("name", that.fileList[0].name);
      param.append("file", that.fileList[0]);
      param.append("visibility", "true");
      this.uploading = true;
      axios({
        url: "/courseware/uploadCourseware",
        method: "post",
        data: param
      })
        .then(() => {
          console.log("aa");
          that.fileList = [];
          that.uploading = false;
          // $message.success('提交成功');
          that.init();
        })
        .catch(() => {
          that.uploading = false;
          // this.$message.error('上传失败');
        });
    },

    beforeUpload(file) {
      this.fileList = [...this.fileList, file];
      return false;
    },
    handleDownload(record) {}
  },

  computed: {
    getContent() {
      return this.$store.state.content;
    },
    isAdmin() {
      if (this.$store.state.userInfo.role == 0) {
        return true;
      } else {
        return false;
      }
    },
    isAcademic() {
      if (this.$store.state.userInfo.role == 1) {
        return true;
      } else {
        return false;
      }
    },
    isTeacher() {
      if (this.$store.state.userInfo.role == 2) {
        return true;
      } else {
        return false;
      }
    },
    isStudent() {
      if (this.$store.state.userInfo.role == 3) {
        return true;
      } else {
        return false;
      }
    },
    isTest() {
      if (this.$store.state.userInfo.role == -1) {
        //测试菜单时的权限
        return true;
      } else {
        return false;
      }
    },
    selectedCourse() {
      if (this.$store.state.courseId != null) {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>
