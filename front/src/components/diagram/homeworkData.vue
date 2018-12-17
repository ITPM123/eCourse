<template>
  <a-layout-content style="padding: 0 0px">
    <div
      :style="{ background: '#fff', padding: '0px',width:'600px',height:'600px',margin: '0px 0px 0px 0px',float:'left'}"
    >
      <a-divider orientation="left">提交缺交情况</a-divider>
      <div :style="{margin: '0px 0px 100px 0px'}">
        <dataDram></dataDram>
      </div>
      <a-divider orientation="left">各分数段占比情况</a-divider>
      <div id="main" style="width: 400px;height: 300px;"></div>
    </div>
    <div
      :style="{ background: '#fff', padding: '0px',width:'600px',height:'600px',margin: '0px 5px 0px 0px',float:'right'}"
    >
      <a-divider orientation="left">分数排名</a-divider>
      <a-table :columns="columns" :dataSource="data" @change="onChange"/>
    </div>
  </a-layout-content>
</template>
<script>
import dataDram from "@/components/diagram/dataDram.vue";
import echarts from "echarts";
const columns = [
  {
    title: "排名",
    dataIndex: "stuRank"
    // specify the condition of filtering result
    // here is that finding the name started with `value`
  },
  {
    title: "学生学号",
    dataIndex: "stuNum"
    // specify the condition of filtering result
    // here is that finding the name started with `value`
  },
  {
    title: "学生姓名",
    dataIndex: "stuName"
  },
  {
    title: "成绩",
    dataIndex: "stuScore",
    sorter: (a, b) => a.stuScore - b.stuScore
  }
];

const data = [
  {
    key: "1",
    stuRank: "1",
    stuNum: "201630660000",
    stuName: "张三1",
    stuScore: "100"
  },
  {
    key: "2",
    stuRank: "2",
    stuNum: "201630660001",
    stuName: "张三2",
    stuScore: "90"
  },
  {
    key: "3",
    stuRank: "3",
    stuNum: "201630660002",
    stuName: "张三3",
    stuScore: "80"
  },
  {
    key: "4",
    stuRank: "4",
    stuNum: "201630660003",
    stuName: "张三4",
    stuScore: "75"
  }
];

function onChange(pagination, filters, sorter) {
  console.log("params", pagination, filters, sorter);
}
export default {
  name: "homeworkData",
  components: {
    dataDram
  },
  //name: '',
  data() {
    return {
      charts: "",
      opinion: [
        "90分——100分",
        "80分——89分",
        "70分——79分",
        "60分——69分",
        "60分以下"
      ],
      opinionData: [
        { value: 335, name: "90分——100分" },
        { value: 310, name: "80分——89分" },
        { value: 234, name: "70分——79分" },
        { value: 135, name: "60分——69分" },
        { value: 1548, name: "60分以下" }
      ],
      data,
      columns
    };
  },
  methods: {
    onChange,
    drawPie(id) {
      this.charts = echarts.init(document.getElementById(id));
      this.charts.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a}<br/>{b}:{c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "left",
          data: this.opinion
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: ["50%", "70%"],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: "center"
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: "30",
                  fontWeight: "blod"
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: this.opinionData
          }
        ]
      });
    }
  },
  //调用
  mounted() {
    this.$nextTick(function() {
      this.drawPie("main");
    });
  }
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
</style>