<template>
    <div>
        <a-input v-model="username"/>
        <a-button type='primary' htmlType='submit' @click="handleSubmit">
            Submit
        </a-button>
    </div>

</template>

<script>
    import axios from 'axios'

    export default {
        name: "Login",
        data: function () {
            return {
                username: '',
            }
        },
        methods: {
            handleSubmit: function () {
                axios({
                    method: 'post',
                    url: '/login',
                    data: {
                        username: 'xxx',
                        password: 'ccc'
                    }
                }).then(res=>{
                    this.$router.push({
                        path:'/hello'
                    })
                })
                axios({
                    methods: "get",
                    url: "/hello",
                    headers: {
                        "Access-Token": "5555"
                    }
                }).then((res) => {
                    console.log("hello:" + res)
                })
            }

        },
    }
</script>

<style scoped>

</style>