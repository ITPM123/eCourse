<template>
  <a-layout id="main" style="height:100%">
    <a-layout-sider :trigger="null" collapsible v-model="collapsed">
      <div class="logo"/>
      <a-menu
        theme="dark"
        mode="inline"
        :defaultSelectedKeys="['1']"
        :openKeys="openKeys"
        @openChange="onOpenChange"
        @click="handleChange"
      >
        <a-menu-item key="主页">
          <a-icon type="user"/>
          <span>主页</span>
        </a-menu-item>
        <a-menu-item key="个人资料" v-if="isTeacher||isStudent||isTest||isAdmin||isAcademic">
          <a-icon type="video-camera"/>
          <span>个人资料</span>
        </a-menu-item>
        <a-menu-item key="审核管理" v-if="isAcademic||isTest">
          <a-icon type="upload"/>
          <span>审核管理</span>
        </a-menu-item>
        <a-sub-menu key="sub1" v-if="isAdmin||isTest">
          <span slot="title">
            <a-icon type="mail"/>
            <span>教务员管理</span>
          </span>
          <a-menu-item key="教员列表">
            <a-icon type="upload"/>
            <span>教员列表</span>
          </a-menu-item>
          <a-menu-item key="添加教员">
            <a-icon type="upload"/>
            <span>添加教员</span>
          </a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2" v-if="isAcademic||isTest">
          <span slot="title">
            <a-icon type="mail"/>
            <span>教师管理</span>
          </span>
          <a-menu-item key="教师列表">
            <a-icon type="upload"/>
            <span>教师列表</span>
          </a-menu-item>
          <a-menu-item key="添加教师">
            <a-icon type="upload"/>
            <span>添加教师</span>
          </a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3" v-if="isAcademic||isTest">
          <span slot="title">
            <a-icon type="mail"/>
            <span>学生管理</span>
          </span>
          <a-menu-item key="学生列表">
            <a-icon type="upload"/>
            <span>学生列表</span>
          </a-menu-item>
          <a-menu-item key="添加学生">
            <a-icon type="upload"/>
            <span>添加学生</span>
          </a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub4" v-if="isTeacher||isTest">
          <span slot="title">
            <a-icon type="mail"/>
            <span>申报管理</span>
          </span>
          <a-menu-item key="申报列表">
            <a-icon type="upload"/>
            <span>申报列表</span>
          </a-menu-item>
          <a-menu-item key="申报课程">
            <a-icon type="upload"/>
            <span>申报课程</span>
          </a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub5" v-if="!isAdmin||isTest">
          <span slot="title">
            <a-icon type="mail"/>
            <span>教务公告</span>
          </span>
          <a-menu-item key="公告列表">
            <a-icon type="upload"/>
            <span>公告列表</span>
          </a-menu-item>
          <a-menu-item key="发布公告" v-if="isAcademic||isTest">
            <a-icon type="upload"/>
            <span>发布公告</span>
          </a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub6" v-if="isTeacher||isStudent||isTest">
          <span slot="title">
            <a-icon type="mail"/>
            <span>课程管理</span>
          </span>
          <a-menu-item key="我的课程">
            <a-icon type="upload"/>
            <span>我的课程</span>
          </a-menu-item>
          <a-menu-item key="课程信息" v-if="selectedCourse||isTest">
            <a-icon type="upload"/>
            <span>课程信息</span>
          </a-menu-item>
          <a-menu-item key="课程公告" v-if="selectedCourse||isTest">
            <!-- <a-menu-item key="课程公告" v-if="setCourseInfo||isTest"> -->
            <a-icon type="upload"/>
            <span>课程公告</span>
          </a-menu-item>
          <a-menu-item key="课程资源" v-if="selectedCourse||isTest">
            <a-icon type="upload"/>
            <span>课程资源</span>
          </a-menu-item>
          <a-menu-item key="作业管理" v-if="(isTeacher&&selectedCourse)||isTest">
            <a-icon type="upload"/>
            <span>作业管理</span>
          </a-menu-item>
          <a-menu-item key="课程作业" v-if="(isStudent&&selectedCourse)||isTest">
            <a-icon type="upload"/>
            <span>课程作业</span>
          </a-menu-item>
          <a-menu-item key="学生管理" v-if="(isTeacher&&selectedCourse)||isTest">
            <a-icon type="upload"/>
            <span>学生管理</span>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>

    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="()=> {
              collapsed = !collapsed;
              this.openKeys=[]
              }"
        />
        <!-- <a-icon herf="" type="rollback" class="trigger" style="float:right"/>-->
        <span class="trigger" style="float:right">
          <span style="font-size:1rem" @click="handleLogout">注销</span>
        </span>
        <!-- <div :style="{float:'right', padding: '2px'}">
        <a-icon type="bell"/>
        <a-avatar :style="{ margin: '0px 10px 0px 10px'}"  @click="()=> {
              collapsed = !collapsed;
              this.openKeys=[]
              }">USER</a-avatar>
        {{username}}
        </div>-->
      </a-layout-header>
      <a-layout-content
        :style="{ margin: '24px 16px', padding: '2px', background: '#fff', minHeight: '1000px' }"
      >
        <component v-bind:is="getContent"/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import Login from "@/components/forms/login.vue";
import home from "../contents/home/Home.vue";
import setProfile from "../contents/setprofile/SetProfile.vue";
import reviewList from "../contents/reviewcourse/ReviewList.vue";
import reviewDetail from "../contents/reviewcourse/ReviewDetail.vue";
import reviewResulter from "../contents/reviewcourse/ReviewResult.vue";
import academicList from "../contents/manageacademic/AcademicList.vue";
import addAcademic from "../contents/manageacademic/AddAcademic.vue";
import teacherList from "../contents/manageteacher/TeacherList.vue";
import addTeacher from "../contents/manageteacher/AddTeacher.vue";
import studentList from "../contents/managestudent/StudentList.vue";
import addStudent from "../contents/managestudent/AddStudent.vue";
import declareList from "../contents/declarecourse/DeclareList.vue";
import declareCourse from "../contents/declarecourse/DeclareCourse.vue";
import declareDetail from "../contents/declarecourse/DeclareDetail.vue";
import announceList from "../contents/schoolannouncement/AnnounceList.vue";
import createAnnounce from "../contents/schoolannouncement/CreateAnnounce.vue";
import myCourse from "../contents/course/MyCourse.vue";
import courseInfo from "../contents/course/CourseInfo.vue";
import courseAnnounceList from "../contents/course/CourseAnnounce.vue";
import courseware from "../contents/course/Courseware.vue";
import homeworkList from "../contents/course/teacherhomework/HomeworkList.vue";
import dataHomework from "../contents/course/teacherhomework/HomeworkData.vue";
import homeworkReviewer from "../contents/course/teacherhomework/HomeworkReview.vue";
import studentHomework from "../contents/course/StudentHomework.vue";
import courseClass from "../contents/course/courseManage/CourseClass.vue";
import classStudent from "../contents/course/courseManage/ClassStudent.vue";
// import classStudent1 from "../contents/course/courseManage/ClassStudent.vue";
import homeworkPublisher from "../contents/course/teacherhomework/publishHomework.vue";
import publishCourseAnn from "../contents/course/publishCourseAnn.vue";
import annConent from "../contents/schoolannouncement/announcementContent.vue";
import AnnouncementEdit from "../contents/schoolannouncement/editAnnouncement.vue";
import readCourseAnn from "../contents/course/readCourseAnnouncement.vue";
import reviewList1 from "@/components/review/ReviewList.vue";
import axios from "axios";

//import courseAnnounceList from "../contents/course/courseannouncement/courseAnnouncementList.vue"
/*key与component name的映射 */

var username = "李四";
var map = {
  登录页: "Login",
  主页: "home",
  个人资料: "setProfile",
  审核管理: "reviewList",
  //有reviewDetail,和reviewResult页
  教员列表: "academicList",
  添加教员: "addAcademic",
  教师列表: "teacherList",
  添加教师: "addTeacher",
  学生列表: "studentList",
  添加学生: "addStudent",
  申报列表: "declareList",
  申报课程: "declareCourse",
  //有declareDetail页
  公告列表: "announceList",
  发布公告: "createAnnounce",
  我的课程: "myCourse",
  课程信息: "courseInfo",
  课程公告: "courseAnnounceList",
  课程资源: "courseware",
  作业管理: "homeworkList",
  //有homeworkData，homeworkReview页
  课程作业: "studentHomework",
  学生管理: "courseClass",
  发布作业: "homeworkPublisher",
  作业数据: "dataHomework",
  学生作业列表: "homeworkReviewer",
  审核详情: "reviewResulter",
  审核课程: "reviewDetail",
  发布课程公告: "publishCourseAnn",
  教务公告详情: "annConent",
  课程班级: "classStudent",
  编辑教务公告: "AnnouncementEdit",
  查看课程公告: "readCourseAnn",
  查看已审核课程: "reviewList1"
};

export default {
  name: "Main",
  mounted() {},
  components: {
    Login,
    home,
    setProfile,
    reviewList,

    // reviewList1,

    reviewDetail,
    reviewResulter,
    academicList,
    addAcademic,
    teacherList,
    addTeacher,
    studentList,
    addStudent,
    declareList,
    declareCourse,
    declareDetail,
    announceList,
    createAnnounce,
    myCourse,
    courseInfo,
    courseAnnounceList,
    courseware,
    homeworkList,
    dataHomework,
    homeworkReviewer,
    studentHomework,

    courseClass,
    classStudent,

    homeworkPublisher,
    publishCourseAnn,
    annConent,
    AnnouncementEdit,
    readCourseAnn,
    reviewList1
  },
  data: function() {
    return {
      content: "home",
      collapsed: false,
      rootSubmenuKeys: ["sub1", "sub2", "sub3", "sub4", "sub5", "sub6"],
      openKeys: [],
      map: map,
      username
    };
  },
  methods: {
    handleLogout() {
      let that = this;
      axios({
        url: "/logout"
      }).then(response => {
        if ((response.data.errCode = 1)) {
          that.$store.state.isLogin = false;
          that.$store.commit.logOut;
          that.$store.commit("changeContent", "home");
          that.$router.push("login");
        }
      });
    },
    onOpenChange(openKeys) {
      /*在打开一个子菜单同时关闭已打开的菜单*/
      const latestOpenKey = openKeys.find(
        key => this.openKeys.indexOf(key) === -1
      );
      if (this.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
        this.openKeys = openKeys;
      } else {
        this.openKeys = latestOpenKey ? [latestOpenKey] : [];
      }
    },
    handleChange(res) {
      //this.content=this.map[res.key]
      var newContent = this.map[res.key];
      this.$store.commit("changeContent", newContent);
    }
  },
  computed: {
    getContent() {
      return this.$store.state.content;
    },
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
    },
    selectedCourse() {
      if (this.$store.state.courseInfo != null) {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>

<style>
#main .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#main .trigger:hover {
  color: #1890ff;
}

#main .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>