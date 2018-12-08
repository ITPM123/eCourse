<template>
    <div>
        <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
            添加教师
        </div>
        <a-divider />
        <addteacher/>
        <router-view></router-view>
    </div>
</template>


<script>
import addteacher from '@/components/forms/addteacher.vue'
export default {
     data:function(){
        return {
            
        }
    },
    components:{
        addteacher
    }
}
</script>
