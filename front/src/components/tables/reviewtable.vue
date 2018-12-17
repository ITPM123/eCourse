<template>
  <div>
    <a-table :columns="columns" :dataSource="data">
      <span slot="action" slot-scope="record">
        <a v-if="record.hasReviewed" href="javascript:;" @click="clickHandle1">查看</a>
        <a v-else href="javascript:;" @click="()=>clickHandle2(record)">审核</a>
      </span>
    </a-table>
  </div>
</template>


<script>
import axios from "axios";

const columns = [
  {
    title: "申报编号",
    dataIndex: "course.course_id",
    key: "course_id"
  },
  {
    title: "课程名称",
    dataIndex: "course.name",
    key: "name"
  },
  {
    title: "申报人",
    dataIndex: "personName",
    key: "personName"
  },
  {
    title: "课程学分",
    dataIndex: "course.credit"
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];

export default {
  name: "reviewcourse",
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
      /**获取未审核列表长度 */
      axios.post("/reviewCourse/unexaminedCourseListCount").then(response => {
        console.log("获取未审核列表长度");
        console.log(response);
      });

      let that = this;
      // setTimeout(function() {
      let param = new FormData();
      param.append("pageSize", 10);
      param.append("pageNumber", 0);
      axios({
        url: "/reviewCourse/unexaminedCourseList",
        method: "post",
        data: param
      }).then(response => {
        console.log("获取未审核列表");
        console.log(response);
        that.data = response.data;
      });
      // }, 1000);
    },

    /**查看审核结果 */
    clickHandle1() {
      this.$store.commit("changeContent", "reviewResulter");
    },

    /**审核课程 */
    clickHandle2(record) {
      console.log("审核课程");
      console.log(record);
      this.$store.commit("setCourseInfo", record);
      this.$store.commit("changeContent", "reviewDetail");
    }
  }
};
</script>
