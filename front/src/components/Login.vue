<template>
  <div>
    <a-input v-model="username"/>
    <a-button type="primary" htmlType="submit" @click="handleSubmit">Submit</a-button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  data: function() {
    return {
      username: ""
    };
  },

  methods: {
    handleSubmit: function() {
      axios({
        method: "post",
        url: "/login",
        transformRequest: [
          function(data) {
            let newData = "";
            for (let k in data) {
              newData +=
                encodeURIComponent(k) + "=" + encodeURIComponent(data[k]) + "&";
            }
            return newData;
          }
        ],
        data: {
          username: 5205,
          password: 123456
        }
      }).then(res => {
        console.log(res);
      });
    }
  }
};
</script>

