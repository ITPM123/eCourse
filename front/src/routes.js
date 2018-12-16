import Login from "./views/Login.vue"
import CreateAnnounce from './components/Createannounce'
import Main from "./views/Main.vue"
import Page404 from "./views/Page404.vue"
let routers=[
    {
        path:"/",
        redirect:"/login"
    },
    {
        path:"/login",
        component:Login,
        name:"login"
    },
    {
        path:'/create',
        component:CreateAnnounce
    },
    {
        path:'/main',
        component:Main,
        name:'main'
    },
    {
        path:'*',
        component:Page404,
    }

];
export default routers