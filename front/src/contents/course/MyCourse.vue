<template>
  <div>
    <index/>
    <div style="font-weight:bold;font-size:2em;margin-bottom:-20px;margin-left:10px;">
        我的课程
    </div>
    <a-divider />
    <coursecard/>
  </div>
</template>

<script>
import index from '@/components/Carousel/index.vue'
import coursecard from '@/components/lists/coursecard.vue'
import axios from "axios"

export default {
  name: 'myCourse',

  components:{
    index,
    coursecard
  },

  mounted(){
    this.init()
  },

  methods: {
    /**初始化 */
   init(){
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
                username:2,
                password:123456
                }
                }).then(res=>{
                    console.log(res)
                })
     },

    /** */
     clickHandle(){
        this.$store.commit('changeContent',"courseInfo")
        // this.$store.commit('selectCourse',courseId)
      }
  }
}
</script>

<style>
</style>
