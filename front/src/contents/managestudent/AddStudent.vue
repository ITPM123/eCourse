<template>
    <div>
        <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
            添加学生
        </div>
        <a-divider />
        <addstudent/>
        <router-view></router-view>
    </div>
</template>


<script>
import addstudent from '@/components/forms/addstudent.vue'
import axios from "axios"


export default {
    name:"addStudent",
     data:function(){
        return {
            
        }
    },
    components:{
        addstudent
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
                username:1,
                password:123456
                }
                }).then(response=>{
                    console.log(response)
                })
        }
        }
}
</script>
