import Login from "./components/Login.vue"
import HelloWorld from "./components/HelloWorld"
import Review from "./views/Review.vue"
import Setting from "./views/Setting.vue"
import CourseManage from "./views/CourseManage.vue"
import ApplyCourse from "./views/ApplyCourse.vue"
let routers=[
    {
        path:"/",
        redirect:"/applyCourse"
    },
    {
        path:"/login",
        component:Login,
        name:""
    },
    {
        path:"/hello",
        component: HelloWorld
    },
    {
        path:'/review',
        component: Review
    },
    {
        path:'/setting',
        component:Setting
    },
    {
        path:'/courseManage',
        component:CourseManage
    },
    {
        path:'/applyCourse',
        component:ApplyCourse
    }

];
export default routers