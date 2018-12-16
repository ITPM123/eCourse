<template>
  <div>
    <a-table bordered :dataSource="data" :columns="columns">
      <a slot="name" slot-scope="text,record" @click="()=>handleView(record)">{{text}}</a>
    </a-table>
  </div>
</template>


<script>
import axios from "axios"

const columns = [{
  title:'课程名称',
  dataIndex:'name',
  key:"name",
  scopedSlots: { customRender: 'name' },
},]

export default {
  mounted(){
    this.init()
  },

  data () {
    return {
      data:[],
      columns,
      onClick: () => {}
    }
  },

  methods: {
    init(){
      let param=new FormData();
      param.append("pageNumber",0);
      param.append("pageSize",10);
      axios({
        url:"/course/getCoursesList",
        method:"post",
        data:param
      }).then(response=>{
        console.log("获取课程列表");
        console.log(response);
        console.log(response.data);
        this.data=response.data;
      })
    },
    /**查看课程信息 */
    handleView(record){
      this.$store.commit('setCourseInfo',record);
      console.log(record);
      // this.$store.commit('setCourseInfo',record);
      this.$store.commit('changeContent','courseInfo');
      console.log(record);
      console.log("跳转到课程信息");
    }
  }
}
</script>