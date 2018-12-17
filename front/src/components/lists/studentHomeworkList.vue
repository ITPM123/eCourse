<template>
  <div>
    <a-table :columns="columns" :dataSource="data">
      <span slot="aid" slot-scope="text">{{text}}</span>
      <span slot="hasReviewed" slot-scope="text">
        <a-tag v-if="text" color="#1ABC9C" :key="1">已审核</a-tag>
        <a-tag v-else color="#E74C3C" :key="0">未审核</a-tag>
      </span>
      <span slot="action" slot-scope="record">
        <a-button type="primary" v-if="record.marked" @click="showModal">查看</a-button>
        <a-button type="primary" v-else @click="showModal1">审核</a-button>

        <a-modal
          title="审核学生作业"
          :visible="visible1"
          @ok="()=>handleOk(record)"
          :confirmLoading="confirmLoading"
          @cancel="handleCancel"
          okText="确认"
          cancelText="取消"
        >
          <a-input v-model="score" placeholder="评分" style="margin-bottom: 30px"/>
          <a-textarea v-model="comment" placeholder="评语"/>
        </a-modal>

        <a-modal
          title="查看学生作业"
          :visible="visible2"
          @ok="handleOk2"
          :confirmLoading="confirmLoading"
          @cancel="handleCancel2"
          okText="知道了"
          cancelText="关闭"
        >
          <p>分数：{{score1}}</p>
          <p>评语：{{command}}</p>
        </a-modal>
      </span>
    </a-table>
  </div>
</template>

<script>
import axios from "axios";

// var score;
// var command;

const columns = [
  {
    title: "学生学号",
    dataIndex: "person.personId",
    key: "person.personId"
  },
  {
    title: "学生姓名",
    dataIndex: "person.realName",
    key: "person.realName"
  },
  {
    title: "作业文件",
    dataIndex: "attachment_filename",
    key: "attachment_filename"
  },
  {
    title: "提交时间",
    dataIndex: "submit_time",
    width: "15%",
    scopedSlots: { customRender: "submit_time" }
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];

import StudentHomeworkScore from "@/components/forms/StudentHomeworkScore.vue";
export default {
  name: "homeworkReview",

  components: {
    StudentHomeworkScore
  },

  data: function() {
    return {
      data: [],
      columns,

      score: "",
      comment: "",

      ModalText: "Content of the modal",
      visible1: false,
      visible2: false,
      confirmLoading: false
      //   score1: 100,
      //   command: "认真"
    };
  },
  mounted() {
    this.init();
  },

  methods: {
    init() {
      let param = new FormData();
      param.append("homework_id", this.$store.state.homeworkInfo.homwwork_id);
      param.append("pageNumber", 0);
      param.append("pageSize", 10);
      axios({
        url: "/homework/getStudentHomeworkList",
        method: "post",
        data: param
      }).then(Response => {
        console.log("获取学生作业列表");
        console.log(Response);
        this.data = Response.data;
      });
    },

    showModal() {
      this.visible2 = true;
    },

    showModal1() {
      this.visible1 = true;
    },

    /**提交审核意见 */
    handleOk(record) {
      console.log(record.id);
      let param = new FormData();
      param.append("do_homework_id", record.id);
      param.append("score", this.score);
      param.append("comment", this.comment);
      axios({
        url: "/homework/reviewStudentHomework",
        method: "post",
        data: param
      }).then(Response => {
        console.log(Response);
      });
    },

    /**取消审核 */
    handleCancel() {
      console.log("取消审核");
      this.visible1 = false;
      this.visible2 = false;
    },

    /**知道了 */
    handleOk2() {
      console.log("取消查看");
      this.visible1 = false;
      this.visible2 = false;
    },
    /**取消查看 */
    handleCancel2() {
      console.log("取消查看");
      this.visible1 = false;
      this.visible2 = false;
    }
  }
};
</script>
