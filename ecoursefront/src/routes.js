import Login from "./components/Login.vue"
import HelloWorld from "./components/HelloWorld"
let routers=[
    {
        path:"/",
        redirect:"/login"
    },
    {
        path:"/login",
        component:Login,
        name:""
    },
    {
        path:"/hello",
        component: HelloWorld
    }
];
export default routers