<template>
  <div class="bg">
    <login/>
  </div>
</template>

<script>
import login from "../components/forms/login.vue";
export default {
  name: "Login",
  components: {
    login
  }
};
</script>

<style>
.bg{
  background-image: url('../assets/bg.jpg');
  display: flex;
  /* flex-direction: column; */
  /* align-content: center; */
  /* height:100%;
   */
  background-size: "cover",
}
</style>