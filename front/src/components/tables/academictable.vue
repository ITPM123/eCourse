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
    title: "教务员工号",
    dataIndex: "code",
    width: "30%"
  },
  {
    title: "教务员姓名",
    dataIndex: "realName",
    width: "30%"
  },
  {
    title: "学院",
    dataIndex: "school",
    width: "30%"
  },
  {
    title: "选择操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" }
  }
];

export default {
  data() {
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
      let url = "?page=" + 0;
      axios({
        url: "/acdemicDean/list" + url,
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
        url: "/acdemicDean/removeAcdemicDean" + url,
        method: "get"
      }).then(response => {
        if (response.data.errCode == 0) {
          console.log("删除教员");
          that.$message.success("删除成功");
          console.log(response);
          that.init();
        } else {
        }
      });
    }
  }
};
</script>