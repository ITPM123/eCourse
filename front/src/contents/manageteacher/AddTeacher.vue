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
import axios from "axios"


export default {
    name:"addTeacher",
     data:function(){
        return {
            
        }
    },
    components:{
        addteacher
    },
    mounted(){
        this.login();
    },

    methods:{
        login(){
      axios({
        method:"post",
        url:"/login",
        transformRequest:[function (data) {
           let newData = ''
           for (let k in data) {
              newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
              }
              return newData
              }],
              data:{
                username:123123123123,
                password:123456
                }
                }).then(response=>{
                    console.log(response)
                })
        }
        }
}
</script>
