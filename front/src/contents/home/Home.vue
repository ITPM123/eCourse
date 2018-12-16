<template>
  <div>
    <index/>
    <a-divider />
    <div><center>
        <img class='image' src="@/assets/logo.svg"/>
        <div> <h1 style="font-family:verdana">{{person.name}}</h1></div>
        <div><h2 style="font-family: erdana;color: gray">{{person.sign}}</h2></div>
        <div> <a-icon type="user"/> {{person.profession}}</div>
        <div><a-icon type="compass" :style="{ margin: '15px 0px 0px 0px'}"/>{{person.unit}}</div>
        <div><a-icon type="environment-o" :style="{margin: '15px 0px 0px 0px'}" />{{person.address}}</div>
        <div><a-icon type="mail" :style="{margin: '5px 0px 0px 0px'}" />{{person.email}}</div>
       </center>
      </div>
  </div>
</template>

<script>
var person={
 name:"李四",
 sign:"海纳百川，有容乃大",
 profession:"教务员",
 unit:"华南理工大学软件学院",
 address:"中国-广东-广州",
 email:"1111111@qq.com",
}
import index from '@/components/Carousel/index.vue'
import coursecard from '@/components/lists/coursecard.vue'

export default {
  name: 'myCourse',
    data () {
    return {
      person
    }
  },
  components:{
    index,
    coursecard
  },
  methods: {
     clickHandle(){
        this.$store.commit('changeContent',"courseInfo")
        var courseId='123456'
        this.$store.commit('selectCourse',courseId)
      }
  }
}
</script>
<style>
/* .image{
  margin-Top: 10px; 
  width:100px; 
  height:100px; 
  border-radius:200px; 
}
.image1{
  width:1180px; 
  height:300px; 
  float:left;
  margin-bottom:20px;
}
#components-layout-demo-top .logo {
  width: 120px;
  height: 31px;
  background: rgba(255,255,255,.2);
  margin: 16px 24px 16px 0;
  float: left;
} */
</style>

