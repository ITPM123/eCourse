<template>
    <div class="content">
        <div class="part">
            <span style="min-width:4rem;margin-top:5px">原密码：</span>
            <a-input style="width:50vh" placeholder="请输入原密码.." v-model="oldPassword"></a-input>
        </div>
        <div class="part"> 
            <span style="min-width:4rem;margin-top:5px">新密码：</span>
            <a-input style="width:50vh" placeholder="请输入新密码" v-model="newPassword"></a-input>
        </div>
        <a-button style="width:465px;margin:0 35px 0 65px" type="primary" :size="large">修改</a-button>
    </div>
</template>


<style>
.content{
    display: flex;
    flex-direction: column;
    padding:50px 120px;
}
.part{
    display:flex;
    flex-direction:row;
    margin-bottom: 50px;
}
</style>

<script>

var oldPassword=null;
var newPassword=null;

export default {
    name:"SecureSetting",
    data:function(){
        return {
            oldPassword,
            newPassword
        }
    },
    methods:{

    }
}
</script>