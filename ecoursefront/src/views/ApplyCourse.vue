<template>
    <a-layout>
        <a-layout-header>
            <div style="width:120px;height:31px;background:rgba(255,255,255,.2);margin:16px 24px 16px 0;float:left"></div>
            <a-menu theme='dark' mode="horizontal" :defaultSelectedKeys="['1']" :style="{lineHeight:'64px'}">
                <a-menu-item key='1'>首页</a-menu-item>
                <a-menu-item key='2'>教务公告</a-menu-item>
                <a-menu-item key='3'>我的课程</a-menu-item>
                <a-menu-item key='4'>系统反馈</a-menu-item>
            </a-menu>
        </a-layout-header>
        <a-layout-content style="margin:50px 50px">
            <div :style="{background:'#fff',padding:'24px',minHeight:'280px'}">
            <component v-bind:is="whichShow"></component>
            </div>
        </a-layout-content>
        
        <a-layout-footer style="text-align:center">
            copyright © 2016级最菜的一个组
        </a-layout-footer>
    </a-layout>
</template>

<script>
import ApplyResult from "../components/apply/ApplyResult.vue"
import ApplyProcess from "../components/apply/ApplyProcess.vue"

export default {
    name:"ApplyCourse",
    data:function(){
        return {
            whichShow:"ApplyResult"
        }
    },
    methods:{

    },
    components:{
        ApplyResult,
        ApplyProcess
    }
}
</script>