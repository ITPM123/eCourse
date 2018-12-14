<template>
    <div>
        <a-table :columns="columns" :dataSource="list">
            <span slot="aid" slot-scope="text">{{text}}</span>
            <span slot="hasReviewed" slot-scope="text">
                <a-tag v-if=text color="#1ABC9C" :key=1>已审核</a-tag>
                <a-tag v-else color="#E74C3C" :key=0>未审核</a-tag>
            </span>
            <span slot="action" slot-scope="record">
                <a v-if="record.hasReviewed" href="javascript:;"  @click="clickHandle1">查看</a>
                <a v-else href="javascript:;" @click="clickHandle2">审核</a>
            </span>
        </a-table>
    </div>
</template>


<script>
const columns=[
    {
        title:'申报编号',
        dataIndex:'aid',
        key:'aid',
    },
    {
        title:'姓名',
        dataIndex:'name',
        key:'name'
    },
    {
        title:'审核状态',
        dataIndex:'hasReviewed',
        key:'hasReviewed',
        scopedSlots:{customRender:'hasReviewed'}
    },
    {
        title:'申报时间',
        dataIndex:'time',
        key:'time'
    },
    {
        title:'操作',
        key:'action',
        scopedSlots:{customRender:'action'}
    }
]

const list=[
    {
        aid:"C0001",
        name:"张三",
        hasReviewed:true,
        courseName:"移动开发导论",
        time:"2018-10-20",
    },
    {
        aid:"C0002",
        name:"李四",
        hasReviewed:true,
        courseName:"安卓开发",
        time:"2018-10-20",
    },
    {
        aid:"C0003",
        name:"王五",
        hasReviewed:false,
        courseName:"ios开发",
        time:"2018-10-21",
    }
]


export default {
    name:'reviewcourse',
    data:function(){
        return {
            list,
            columns
        }
    },
    methods:{
        clickHandle1(){
            this.$router.push('result')
        },
        clickHandle2(){
            this.$router.push('detail')
        }
    }
}
</script>
