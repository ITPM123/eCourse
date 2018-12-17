<template>
  <div id="main" style="width: 400px;height: 300px;"></div>
</template>
<script>
import echarts from "echarts";
export default {
  name: "shanData",
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
      ]
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