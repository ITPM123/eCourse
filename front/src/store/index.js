import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  // 声明全局标量，通过this.$store.state访问
  state: {
    isLogin: false, // 登录状态
    content: 'home', // 当前页面
    courseId: null, // 当前选中的课程ID
    // userInfo:{role:-1},
    userInfo: null,
    schoolAnnouncement: {},
    courseAnnouncement: {},
    courseInfo: null,
    homeworkInfo: {},
    reviewResult: null

  },
  // 在getters中声明state中变量的计算函数，缓存计算后的数据，通过this.$store.getters调用
  getters: {

  },
  // 执行同步方法，改变state状态，通过this.$store.commit调用
  mutations: {
    logIn(state) {
      state.isLogin = true
    },
    logOut(state) {
      state.isLogin = false
    },
    changeContent(state, contentName) {
      state.content = contentName
    },
    selectCourse(state, courseId) {
      state.courseId = courseId
    },
    setUserInfo(state, userInfo) {
      state.userInfo = userInfo
    },

    SchoolAnnouncement(state, schoolAnnouncement) {
      state.schoolAnnouncement = schoolAnnouncement
    },
    setSchoolA(state, schoolAnnouncement) {
      state.schoolAnnouncement = schoolAnnouncement
    },
    setCourseAnnouncement(state, courseAnnouncement) {
      state.courseAnnouncement = courseAnnouncement
    },

    setCourseInfo(state, courseInfo) {
      state.courseInfo = courseInfo
    },

    setHomeworkInfo(state, homeworkInfo) {
      state.homeworkInfo = homeworkInfo
    },
    setReviewResult(state, reviewResult) {
      state.reviewResult = reviewResult
    }
  },
  // 以异步方式执行mutations，通过this.$store.dispatch调用
  actions: {

  }
})
