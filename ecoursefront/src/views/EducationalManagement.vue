<template>
<a-layout>
    <a-layout-sider class="sider" collapsible=true :trigger=null>
        <div style="height:32px;background:rgba(255,255,255,.2);margin:16px">

        </div>
        <a-menu theme='light' mode='inline' defaultSelectedKeys='{["1"]}' style="height:100vh">
            <a-menu-item key='1'>课程审核</a-menu-item>
            <a-menu-item key='2'>学生管理</a-menu-item>
            <a-menu-item key='3'>教师管理</a-menu-item>
        </a-menu>
    </a-layout-sider>
    <a-layout>
        <a-layout-header class="header">header</a-layout-header>
        <a-layout-content class="content">
            <div style="padding:24;background:#fff;minHeight:360px">
                main content
            </div>
        </a-layout-content>
        <a-layout-footer class="footer">footer</a-layout-footer>
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

    }
    .footer{
        text-align: center
    }
</style>
<script>
export default {
    name:"EducationalManagement",
    data:function(){
        return {

        }
    },
    methods:{

    }
}
</script>

