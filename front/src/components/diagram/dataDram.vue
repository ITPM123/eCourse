<template>
  <div :class="className" :id="id" :style="{height:height,width:width}" ref="myEchart"></div>
</template>
<script>
import echarts from "echarts";
import axios from "axios";
export default {
  name: "dataDram",
  props: {
    className: {
      type: String,
      default: "yourClassName"
    },
    id: {
      type: String,
      default: "yourID"
    },
    width: {
      type: String,
      default: "100px"
    },
    height: {
      type: String,
      default: "150px"
    }
  },
  data() {
    return {
      chart: null
    };
  },
  mounted() {
    this.initChart();
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      var SubmittedStudentHomeworkCount; //已经提交的人数
      var CourseStudentCount; //所有学生人数
      axios({
        method: "get",
        url:
          "localhost:8081/homework/getStudentHomeworkSubmitData" +
          this.homework_id
      }).then(res => {
        this.SubmittedStudentHomeworkCount = res.data;
        this.CourseStudentCount = res.data;
      });
      this.chart = echarts.init(this.$refs.myEchart);
      // 把配置和数据放这里
      this.chart.setOption({
        color: ["#3398DB"],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        yAxis: [
          {
            type: "category",
            data: ["已提交", "未提交"],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        xAxis: [
          {
            type: "value"
          }
        ],
        series: [
          {
            name: "直接访问",
            type: "bar",
            barWidth: "50%",
            data: [10, 20]
          }
        ]
      });
    }
  }
};
</script>