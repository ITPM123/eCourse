<template>
<div>
  <a-list
    itemLayout="vertical"
    :grid="{ gutter: 30, xs: 1, sm: 1, md: 1, lg: 1, xl:1, xxl: 1}"
    size="large"
    :dataSource="listData"
    :locale="local"
  >
    <a-list-item slot="renderItem" slot-scope="item, index">
        <a-card :title="item.title" :bordered="false" style="minHeight:180px">
            <a-list-item-meta :description="item.description">
             <!-- <a slot="title" :href="item.href">{{item.description}}</a> -->
            </a-list-item-meta>
      </a-card>
    </a-list-item>
  </a-list>
  {{coursename}}</div>
</template>


<script>
const listData=[{
  title:'课程名称',
  // description:this.props.coursename  
  },
  {
  title:'课程简介',
  description: "Ant Design, a design language for background applications, is refined by Ant UED Team."
  },
  {
  title:'课程概述',
//   description: "Ant Design, a design language for background applications, is refined by Ant UED Team."
  },
    {
  title:'授课目标',
//   description: "Ant Design, a design language for background applications, is refined by Ant UED Team."
  }
]
// for (let i = 0; i < 4; i++) {
//   listData.push({
//     href: 'https://vuecomponent.github.io/ant-design-vue/',
//     title: `ant design vue part ${i}`,
//     avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
//     description: 'Ant Design, a design language for background applications, is refined by Ant UED Team.',
//     content: 'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
//   })
// }

export default {
  name:'courseinfolist',
  data () {
    return {
      listData,
    }
  },
  methods:{

  },
  props:{
    coursename: String,
    require:true
  }
}
</script>