<template>
  <div>
    <a-table bordered :dataSource="data" :columns="columns" :loading="loading">
      <template slot="operation" slot-scope="text, record">
        <a href="javascript:;" @click="()=>handleView(record)">查看</a>
        <a-divider type="vertical" v-if="isAcademic||isTest"/>

        <a-popconfirm
          v-if="data.length&&(isAcademic||isTest)"
          title="确认删除"
          @confirm="() => onDelete(record,record.key)"
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
    title: "标题",
    dataIndex: "title",
    width: "50%"
  },
  {
    title: "发布时间",
    dataIndex: "createTime",
    width: "20%"
  },
  {
    title: "选择操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" }
  }
];

export default {
  name: "announcementtable",

  mounted() {
    this.init();
  },

  data() {
    return {
      data: [],
      loading: false,
      columns
    };
  },

  methods: {
    onDelete(record,key) {
      const data = [...this.data];
      this.data = data.filter(item => item.key !== key);

      let that = this;
      let url = "?schoolAnnouncementId=" + record.schoolAnnouncementId.toString();
      axios({
        url: "/schoolAnnouncement/delete" + url,
        method: "get"
      }).then(response => {
        if (response.data.errCode == 0) {
          console.log("删除教务公告");
          that.$message.success("删除教务公告");
          console.log(response);
          that.init();
        } else {
        }
      });
    },

    handleView(record) {
      console.log("查看教务公告");
      console.log(record);
      this.$store.commit("setSchoolA", record);
      this.$store.commit("changeContent", "annConent");
    },

    handleEdit() {
      this.$store.commit("changeContent", "AnnouncementEdit");
    },

    init() {
      let that = this;
      let url = "?page=" + "0";
      axios({
        url: "/schoolAnnouncement/list" + url,
        method: "get"
      }).then(response => {
        if (response.data.errCode == 0) {
          console.log(response);
          that.data = response.data.data.list;
        } else {
          console.log("获取教务公告失败");
        }
      });
    }
  },

  computed: {
    isAdmin() {
      if (this.$store.state.userInfo.role == 3) {
        return true;
      } else {
        return false;
      }
    },
    isAcademic() {
      if (this.$store.state.userInfo.role == 0) {
        return true;
      } else {
        return false;
      }
    },
    isTeacher() {
      if (this.$store.state.userInfo.role == 1) {
        return true;
      } else {
        return false;
      }
    },
    isStudent() {
      if (this.$store.state.userInfo.role == 2) {
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
    }
  }
};
</script>