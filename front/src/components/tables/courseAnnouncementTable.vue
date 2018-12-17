<template>
  <div>
    <a-button class="editable-add-btn" @click="clickHandle" v-if="isTeacher||isTest">+发布公告</a-button>
    <a-table bordered :dataSource="data" :columns="columns">
      <template slot="operation" slot-scope="text, record">
        <a href="javascript:;" @click="()=>handleView(record)">查看</a>
        <a-divider type="vertical" v-if="isTeacher||isTest"/>
        <a-popconfirm
          v-if="data.length&&(isTeacher||isTest)"
          title="确认删除"
          @confirm="() => onDelete(record.key)"
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
    dataIndex: "createTime"
  },
  {
    title: "选择操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" }
  }
];

export default {
  name: "courseAnnouncementTable",
  mounted() {
    this.getTable();
  },

  data() {
    return {
      data: [],
      loading: false,
      columns
    };
  },

  methods: {
    clickHandle() {
      this.$store.commit("changeContent", "publishCourseAnn");
    },

    onDelete(key) {
      const data = [...this.data];
      this.data = data.filter(item => item.key !== key);
      axios({
        url: "/courseAnnouncement/delete",
        methods: "get",
        data: {
          courseAnnouncementId: 4
        }
      });
    },

    handleView(record) {
      console.log("查看教务公告");
      console.log(record);
      this.$store.commit("setCourseAnnouncement", record);
      this.$store.commit("changeContent", "readCourseAnn");
    },
    

    /**获取课程公告列表 */
    getTable() {
      let that = this;
      setTimeout(function() {
        let url =
          "?courseId=" + that.$store.state.courseInfo.course_id + "&page=" + 0;
        axios({
          url: "/courseAnnouncement/list" + url,
          method: "get"
        }).then(response => {
          console.log(response);
          that.data = response.data.data.list;
        });
      }, 1000);
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