<template>
  <a-table :columns="columns" :dataSource="data" :pagination="pagination" :loading="loading"></a-table>
</template>
<script>
import axios from "axios";

const columns = [
  {
    title: "课程名称",
    dataIndex: "courseInfo.name",
    key: "name",
    sorter: true,
    width: "30%",
    scopedSlots: { customRender: "name" }
  },
  {
    title: "审核状态",
    dataIndex: "review_status",
    width: "20%"
  }
];

export default {
  name: "declaremanage",
  mounted() {
    this.init();
  },
  data() {
    return {
      data: [],
      pagination: {},
      loading: true,
      columns
    };
  },

  methods: {
    init() {
      let that = this;
      axios({
        url: "/reviewCourse/getCoursesByPerson",
        method: "post"
      }).then(response => {
        console.log("教师获取课程状态");
        console.log(response);
        that.data = response.data;
        that.loading = false;
      });
    },

    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        results: pagination.pageSize,
        page: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters
      });
    },
    onDelete(key) {
      const data = [...this.data];
      this.data = data.filter(item => item.key !== key);
    },
    viewHandle() {
      this.$router.push("detail");
    }
  }
};
</script>