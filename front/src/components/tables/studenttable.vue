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
    title: "学生学号",
    dataIndex: "code",
    width: "15%"
  },
  {
    title: "学生姓名",
    dataIndex: "realName",
    width: "15%"
  },
  {
    title: "学院",
    dataIndex: "school",
    width: "15%"
  },
  {
    title: "专业",
    dataIndex: "major",
    width: "20%"
  },
  {
    title: "年级",
    dataIndex: "grade",
    width: "10%"
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
        url: "/student/list" + url,
        method: "get"
      }).then(response => {
        console.log("获取学生列表");
        console.log(response);
        this.data = response.data.data.list;
      });
    },

    onDelete(record, key) {
      const data = [...this.data];
      this.data = data.filter(item => item.key !== key);

      let url = "?personId=" + record.personId.toString();
      console.log(record.personId.toString());
      console.log("sad");
      axios({
        url: "/acdemicDean/removeAcdemicDean" + url,
        method: "get"
      }).then(response => {
        console.log(response);
      });
    }
  }
};
</script>