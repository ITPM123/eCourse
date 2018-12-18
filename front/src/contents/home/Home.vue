<template>
  <div>
    <index/>
    <a-divider/>
    <div>
      <center>
        <div :style="{fontSize:'40px'}">
          <a-icon type="user" :style="{ margin: '15px 0px 0px 0px'}"/>
          {{person.realName}}
        </div>
        <div :style="{fontSize:'30px'}">
          <a-icon type="ellipsis" :style="{ margin: '15px 0px 0px 0px'}"/>
          {{person.nickname}}
        </div>
        <div :style="{fontSize:'30px'}">
          <a-icon type="environment"/>
          {{person.school}}
        </div>
        <div :style="{fontSize:'30px'}">
          <a-icon type="compass" :style="{margin: '15px 0px 0px 0px'}"/>
          {{person.contact}}
        </div>
        <div :style="{fontSize:'30px'}">
          <a-icon type="mail" :style="{margin: '15px 0px 0px 0px'}"/>
          {{person.email}}
        </div>
        <div :style="{fontSize:'30px'}">
          <a-icon type="message" :style="{margin: '5px 0px 0px 0px'}"/>
          {{person.motto}}
        </div>
      </center>
    </div>
  </div>
</template>

<script>
import index from "@/components/Carousel/index.vue";
import coursecard from "@/components/lists/coursecard.vue";

export default {
  name: "myCourse",

  data() {
    return {
      person: [
        {
          realName: "",
          nickname: "",
          contact: "",
          school: "",
          contact: "",
          email: ""
        }
      ]
    };
  },
  components: {
    index,
    // coursecard
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      console.log("打印信息");
      console.log(this.$store.state.userInfo.realName);
      this.person = this.$store.state.userInfo;
    } 
  }
};
</script>
<style>
.image {
  margin-top: 10px;
  width: 100px;
  height: 100px;
  border-radius: 200px;
}
.image1 {
  width: 1180px;
  height: 300px;
  float: left;
  margin-bottom: 20px;
}
#components-layout-demo-top .logo {
  width: 120px;
  height: 31px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px 24px 16px 0;
  float: left;
}
</style>

