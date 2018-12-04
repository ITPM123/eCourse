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
            <a-layout style="padding:24px 0;background:#fff">
                <a-layout-sider width="200" style="backgound:#fff">
                    <a-menu mode="inline" :defaultSelectedKeys="['1']" style="height:120vh">
                        <a-menu-item key="1">基础设置</a-menu-item>
                        <a-menu-item key="2">安全设置</a-menu-item>
                    </a-menu>
                </a-layout-sider>
                <a-layout-content :style="{padding:'0 24px',minHeight:'280px'}">
                    <component v-bind:is="whichShow"></component>
                </a-layout-content>
            </a-layout>
        </a-layout-content>
        
        <a-layout-footer style="text-align:center">
            copyright © 2016级最菜的一个组
        </a-layout-footer>
    </a-layout>
</template>

<script>
import BasicSetting from "../components/setting/BasicSetting.vue"
import SecureSetting from "../components/setting/SecureSetting.vue"

export default {
    name:"Setting",
    data:function(){
        return {
            whichShow:"BasicSetting"
        }
    },
    methods:{

    },
    components:{
        BasicSetting,
        SecureSetting
    }
}
</script>