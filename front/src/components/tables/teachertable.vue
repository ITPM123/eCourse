<template>
  <div>
    <a-table bordered :dataSource="data" :columns="columns">
      <template slot="operation" slot-scope="text, record">
        <a-popconfirm
          title="确认删除"
          @confirm="()=>onDelete(record,record.key)"
          okText="确认"
          cancelText="取消"
        >
          <a href="javascript:;">删除</a>
        </a-popconfirm>
      </template>
    </a-table>
  </div>
</template>

<script>
import axios from "axios";

const columns = [
  {
    title: "教师工号",
    dataIndex: "code",
    width: "20%"
  },
  {
    title: "教师姓名",
    dataIndex: "realName",
    width: "20%"
  },
  {
    title: "学院",
    dataIndex: "school",
    width: "20%"
  },
  {
    title: "职称",
    dataIndex: "title",
    width: "20%"
  },
  {
    title: "选择操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" }
  }
];

export default {
  mounted() {
    this.init();
  },

  data() {
    return {
      data: [],
      columns
    };
  },
  methods: {
    init() {
      let url = "?page=" + 0;
      axios({
        url: "/teacher/list" + url,
        method: "get"
      }).then(response => {
        console.log("获取列表");
        console.log(response);
        this.data = response.data.data.list;
      });
    },

    onDelete(record, key) {
      const data = [...this.data];
      this.data = data.filter(item => item.key !== key);
      let that = this;
      let url = "?personId=" + record.personId.toString();
      console.log(record.personId.toString());
      console.log("sad");
      axios({
        url: "/teacher/removeTeacher" + url,
        method: "get"
      }).then(response => {
        if (response.data.errCode == 0) {
          console.log(response);
          that.$message.success("删除教师成功");
          that.init();
          // that.$store.commit("changeContent",'teacherList')
        } else {
          console.log("删除教师失败");
        }
      });
    }
  }
};
</script>