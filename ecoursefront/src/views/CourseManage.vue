<template>
<a-layout>
    <a-layout-sider class="sider" collapsible=true :trigger=null>
        <div style="height:32px;background:rgba(255,255,255,.2);margin:16px">

        </div>
        <a-menu theme='dark' mode='inline' defaultSelectedKeys='{["3"]}' >
            <a-menu-item key='1'>课程信息</a-menu-item>
            <a-menu-item key='2'>课程公告</a-menu-item>
            <a-menu-item key='3'>课程资源</a-menu-item>
            <a-menu-item key='4'>课程作业</a-menu-item>
            <a-menu-item key='5'>讨论区</a-menu-item>
            <a-menu-item key='6'>课程评价</a-menu-item>
            <a-menu-item key='7'>课程反馈</a-menu-item>
        </a-menu>
    </a-layout-sider>
    <a-layout>
        <a-layout-header class="header">header</a-layout-header>
        <a-layout-content class="content">
            <component v-bind:is="whichShow"></component>
        </a-layout-content>
        <a-layout-footer class="footer">copyright © 2016级最菜的一个组</a-layout-footer>
    </a-layout>
</a-layout>
</template>

<style>
    .sider{
        width: 256px;
        min-height: 100vh;
        color: black;
        /* background-color: white */
    }
    .header{
        background-color: #ffffff;
        text-align: center;
        padding: 0;
    }
    .content{
        margin:24px 16px 0px;
        background-color: #ffffff;
        padding:20px 20px;
    }
    .footer{
        text-align: center
    }
</style>

<script>
import ResourceList from "../components/resource/ResourceList.vue"

export default {
    name:"CourseManage",
    data:function(){
        return {
            whichShow:"ResourceList"
        }
    },
    methods:{

    },
    components:{
        ResourceList
    }
}
</script>
