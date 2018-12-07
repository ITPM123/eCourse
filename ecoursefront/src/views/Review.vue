<template>
<a-layout>
    <a-layout-sider class="sider" collapsible=true :trigger=null>
        <div style="height:32px;background:rgba(255,255,255,.2);margin:16px">

        </div>
        <a-menu theme='dark' mode='inline' defaultSelectedKeys='{["1"]}' >
            <a-menu-item key='1'>课程审核</a-menu-item>
            <a-menu-item key='2'>学生管理</a-menu-item>
            <a-menu-item key='3'>教师管理</a-menu-item>
        </a-menu>
    </a-layout-sider>
    <a-layout>
        <a-layout-header class="header">header</a-layout-header>
        <a-layout-content class="content">
            <!-- <div style="padding:24;background:#fff;minHeight:360px">
                main content
            </div> -->
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
    }
    .footer{
        text-align: center
    }
</style>

<script>
import ReviewList from "../components/review/ReviewList.vue"
import ReviewDetail from "../components/review/ReviewDetail.vue"
import ReviewResult from "../components/review/ReviewResult.vue"

export default {
    name:"Review",
    data:function(){
        return {
            whichShow:"ReviewResult"
        }
    },
    methods:{

    },
    components:{
        ReviewList,
        ReviewDetail,
        ReviewResult
    }
}
</script>

