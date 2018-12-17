<template>
  <a-table :columns="columns" :dataSource="data" @change="onChange"/>
</template>
<script>
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
  name: "rankData",
  //name: '',
  data() {
    return {
      data,
      columns
    };
  },
  methods: {
    onChange
  }
  //调用
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
</style>