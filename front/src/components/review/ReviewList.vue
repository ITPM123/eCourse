<template>
  <div>
    <div style="font-weight:bold;font-size:2em">已审核列表</div>
    <a-button @click="handleView">未审核课程</a-button>

    <a-table :columns="columns" :dataSource="data">
      <span slot="action" slot-scope="record">
        <a href="javascript:;" @click="()=>handleClick(record)">查看</a>
      </span>
    </a-table>
  </div>
</template>


<script>
import axios from "axios";

const columns = [
  {
    title: "申报编号",
    dataIndex: "course_info.course_id",
    key: "course_id"
  },
  {
    title: "课程名称",
    dataIndex: "course_info.name",
    key: "name"
  },
  {
    title: "申报人编号",
    dataIndex: "course_info.person_id",
    key: "person_id"
  },
  {
    title: "课程学分",
    dataIndex: "course_info.credit"
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];

export default {
  name: "reviewList1",
  data: function() {
    return {
      data: [],
      columns
    };
  },

  mounted() {
    this.init();
  },

  methods: {
    init() {
      /**获取已审核列表长度 */
      axios.post("/reviewCourse/examinedCourseListCount").then(response => {
        console.log("获取已审核列表长度");
        console.log(response);
      });

      let that = this;
      // setTimeout(function() {
      let param = new FormData();
      param.append("pageSize", 10);
      param.append("pageNumber", 0);
      axios({
        url: "/reviewCourse/examinedCourseList",
        method: "post",
        data: param
      }).then(response => {
        console.log("获取已审核列表");
        console.log(response);
        that.data = response.data;
      });
      // }, 1000);
    },

    /**返回未审核课程列表*/
    handleView() {
      console.log("回到未审核列表");
      this.$store.commit("changeContent", "reviewList");
    },

    /**查看审核结果 */
    handleClick(record) {
      // this.$store.commit("changeContent", "reviewResulter");
      let that = this;
      let param = new FormData();
      param.append("review_id", record.review_id);
      axios({
        url: "/reviewCourse/getReview",
        method: "post",
        data: param
      }).then(response => {
        console.log("查看已审核课程信息");
        console.log(response);
        console.log(record.review_id);
        this.$store.commit("setReviewResult", record.review_id);
        that.$store.commit("changeContent", "reviewResulter");
      });
    }

    /**审核课程 */
    // clickHandle2(record) {
    //   console.log("审核课程");
    //   console.log(record);
    //   this.$store.commit("setCourseInfo", record);
    //   this.$store.commit("changeContent", "reviewDetail");
    // }
  }
};
</script>
