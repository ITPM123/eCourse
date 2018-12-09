<template>
    <div>
        <a-input v-model="username"/>
        <a-button type='primary' htmlType='submit' @click="handleSubmit">
            Submit
        </a-button>
    </div>

</template>

<script>
    import axios  from 'axios'
    export default {
        name: "Login",
        data: function () {
            return {
                username: '',
            }
        },

        methods: {
            handleSubmit: function () {


            }

        },
    }
</script>

