<template>
    <div>
        <a-input v-model="username"/>
        <a-button type='primary' htmlType='submit' @click="handleSubmit">
            Submit
        </a-button>
    </div>

</template>

<script>
    import * as url from "../api/url.js"
    import Axios from 'axios'
    export default {
        name:"Login",
        data:function(){
            return {
                username:'',
            }
        },
        methods:{
            handleSubmit:function () {

               Axios.post(url.baseUrl+"/login",{
                   username:this.username,
                   password:"sss"
               },).then((res)=>{
                   this.$router.push({
                       path:"/hello"
                   })

               })


            }
        },
    }
</script>

<style scoped>

</style>