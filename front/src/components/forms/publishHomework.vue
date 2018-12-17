<template>
  <a-layout-content
    :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
  >
    <div :style="{ margin: '20px 10px 25px 10px'}">
      作业标题：
      <input v-model="homeworkTitle">
    </div>

    <div :style="{ margin: '20px 10px 25px 10px'}">截至时间：
      <a-date-picker
        format="YYYY-MM-DD HH:mm:ss"
        :disabledDate="disabledDate"
        :disabledTime="disabledDateTime"
        :showTime="{ defaultValue: moment('00:00:00', 'HH:mm:ss') }"
      />
    </div>

    <div :style="{ margin: '20px 10px 25px 10px'}">作业内容：
      <a-textarea v-model="homeworkContent" :rows="8"/>
    </div>

    <div :style="{ margin: '10px 10px 25px 10px'}">作业附件：
      <a-upload
        action="/homework/getCourseHomeworkList"
        :fileList="fileList"
        :remove="handleRemove"
        :beforeUpload="beforeUpload"
      >
        <a>选择文件</a>
      </a-upload>

      <a
        class="upload-demo-start"
        type="primary"
        @click="handleUpload()"
        :disabled="fileList.length === 0"
        :loading="uploading"
      >{{uploading ? '上传中' : '提交作业' }}</a>
    </div>

    <div :style="{ margin: '10px 10px 25px 10px'}">选择班级：
      <a-select
        mode="multiple"
        :defaultValue="['一班']"
        style="width: 20%"
        @change="handleChange"
        placeholder="选择班级"
        :style="{ margin: '20px 0px 25px 0px'}"
      >
        <a-select-option v-for="i in 9" :key="(i).toString(36)">{{(i).toString(36)}}班</a-select-option>
      </a-select>
    </div>

    <div>
      <a-button type="primary" :style="{margin:'10px 10px 15px 10px'}" @click="handleSubmit">提交</a-button>
      <a-button @click="handleCancel">取消</a-button>
    </div>
  </a-layout-content>
</template>


<script>
import moment from "moment";
import axios from "axios";

export default {
  name: "publishHomework",

  components: {},

  data() {
    return {
      collapsed: false,

      homeworkTitle: " ",
      homeworkContent: "",
      homeDeadline: "",

      fileList: [],
      uploading: false,

      fileList: []
    };
  },

  methods: {
    moment,

    range(start, end) {
      const result = [];
      for (let i = start; i < end; i++) {
        result.push(i);
      }
      return result;
    },

    disabledDate(current) {
      // Can not select days before today and today
      return current && current < moment().endOf("day");
    },

    disabledDateTime() {
      return {
        disabledHours: () => this.range(0, 24).splice(4, 20),
        disabledMinutes: () => this.range(30, 60),
        disabledSeconds: () => [55, 56]
      };
    },

    disabledRangeTime(_, type) {
      if (type === "start") {
        return {
          disabledHours: () => this.range(0, 60).splice(4, 20),
          disabledMinutes: () => this.range(30, 60),
          disabledSeconds: () => [55, 56]
        };
      }
      return {
        disabledHours: () => this.range(0, 60).splice(20, 4),
        disabledMinutes: () => this.range(0, 31),
        disabledSeconds: () => [55, 56]
      };
    },

    handleChange(info) {
      let fileList = info.fileList;
      fileList = fileList.slice(-2);
      fileList = fileList.map(file => {
        if (file.response) {
          file.url = file.response.url;
        }
        return file;
      });

      // 3. filter successfully uploaded files according to response from server
      fileList = fileList.filter(file => {
        if (file.response) {
          return file.response.status === "success";
        }
        return true;
      });

      this.fileList = fileList;
    },

    /**移除作业文件 */
    handleRemove(file) {
      const index = this.fileList.indexOf(file);
      const newFileList = this.fileList.slice();
      newFileList.splice(index, 1);
      this.fileList = newFileList;
    },
    /**预上传作业文件 */
    beforeUpload(file) {
      this.fileList = [...this.fileList, file];
      return false;
    },

    /**处理提交**/
    handleSubmit() {
      console.log();

      let param = new FormData();
      const { fileList } = this;
      fileList.forEach(file => {
        param.append("attachment", file);
      });
      this.uploading = true;

      param.append("course_id",this.$store.state.courseInfo.course_id);
      // param.append("course_id", 14);

      param.append("homework_title", this.homeworkTitle);
      param.append("description", this.homeworkContent);

      // param.append("deadline",this.homeDeadline);
      param.append("deadline", "2018-12-16");
      // //
      // param.append("attachment","");

      axios({
        url: "/homework/uploadCourseHomework",
        method: "post",
        data: param
      }).then(response => {
        console.log(response);
        this.fileList = [];
        this.uploading = false;
        this.$message.success("提交成功");
      });

      // this.$store.commit('changeContent',"HomeworkList");
    },

    /**取消提交**/
    handleCancel() {
      this.$store.commit("changeContent", "HomeworkList");
    }
  }
};
</script>



<style>
/* #components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color .3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255,255,255,.2);
  margin: 16px;
} */
</style>
