import Login from "./components/Login.vue"
import HelloWorld from "./components/HelloWorld"
import EducationalManagement from "./views/EducationalManagement"
let routers=[
    {
        path:"/",
        redirect:"/EducationalManagement"
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
        path:'/educationalManagement',
        component: EducationalManagement
    }
];
export default routers