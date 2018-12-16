<template>
    <div style="margin:10px 10px">
        <div style="font-weight:bold;font-size:2em;margin-bottom:10px;margin-left:10px">
            课件列表
        </div>
        <a-table :columns="columns" :dataSource="list">
            <span slot="aid" slot-scope="text">{{text}}</span>
            <span slot="hasReviewed" slot-scope="text">
                <a-tag v-if=text color="#1ABC9C" :key=1>已审核</a-tag>
                <a-tag v-else color="#E74C3C" :key=0>未审核</a-tag>
            </span>
            <span slot="action" slot-scope="record">
                <a v-if="record.hasReviewed" href="javascript:;">查看</a>
                <a v-else href="javascript:;">审核</a>
            </span>
        </a-table>
    </div>
</template>


<script>
import axios from "axios"

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


export default {
    name:"ReviewList",
    data:function(){
        return {
            list,
            columns
        }
    },
    mounted(){
        this.init()
    },

    methods:{
        init(){
            axios.get('/reviewCourse/unexaminedCourseListCount')
            .then(response=>
            console.log(response));
        }
    }
}
</script>
