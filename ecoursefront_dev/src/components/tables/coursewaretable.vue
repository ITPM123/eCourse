<template>
    <div>
        <a-table v-if="isTeacher" :columns="teacherColumns" :dataSource="list">
            <span slot="aid" slot-scope="text">{{text}}</span>
            <span slot="visible" slot-scope="text">
                <a-tag v-if=text color="#1ABC9C" :key=0>可见</a-tag>
                <a-tag v-else color="#E74C3C" :key=1>不可见</a-tag>
            </span>
            <span slot="action" slot-scope="record">
                <a @click="handleChange(record.key)">切换可见性</a>
                <a-divider type="vertical"/>
                <a>预览</a>
                <a-divider type="vertical" />
                <a>下载</a>
                <a-divider type="vertical" />
                <a-popconfirm
                v-if="list.length"
                title="确认删除？"
                okText="确认"
                cancelText="取消"
                @confirm="()=>onDelete(record.key)">
                    <a>删除</a>
                </a-popconfirm>
            </span>
        </a-table>
        <a-table v-else :columns="studentColumns" :dataSource="list">
            <!--需要可见列表-->
            <span slot="aid" slot-scope="text">{{text}}</span>
            <span slot="action" slot-scope="text">
                <a slot="actions">预览</a>
                <a-divider type="vertical" />
                <a slot="actions">下载</a>
            </span>
        </a-table>
    </div>
</template>


<style>

</style>

<script>
const teacherColumns=[
    {
        title:'名称',
        dataIndex:'name',
        key:'name'
    },
    {
        title:'可见性',
        dataIndex:'visible',
        key:'visible',
        scopedSlots:{customRender:'visible'}
    },
    {
        title:'操作',
        key:'action',
        scopedSlots:{customRender:'action'}
    }
]

const studentColumns=[
    {
        title:'名称',
        dataIndex:'name',
        key:'name'
    },
    {
        title:'操作',
        key:'action',
        scopedSlots:{customRender:'action'}
    }
]


var list=[
    {
        name:"first",
        visible:true,
        key:'0',
    },
    {
        name:"second",
        visible:true,
        key:'1'
    },
    {
        name:"third",
        visible:false,
        key:'2'
    },
]

export default {
    name:"coursewaretable",
    data:function(){
        return {
            isTeacher:true,
            list,
            teacherColumns,
            studentColumns,
            key:3
        }
    },
    methods:{
        //删除列表项
        onDelete(key){
            const list=[...this.list]
            this.list=list.filter(item=>item.key!==key)
        },
        //更改可见性
        handleChange(key){
            list=[...this.list]
            for(var i=0;i<list.length;i++){
                if(list[i].key==key){
                    if(list[i].visible==true){
                        list[i].visible=false
                    }
                    else{
                        list[i].visible=true
                    }
                    break
                }
            }
            this.list=list
        },

        //上传文件
        handleUpload(info) {
            if (info.file.status !== 'uploading') {
                console.log(info.file, info.fileList);
            }
            if (info.file.status === 'done') {
                this.$message.success(`${info.file.name} file uploaded successfully`);
                const {count,list}=this
                const newData={
                    key:count,
                    name:info.file.name,
                    visible:true
                }
                this.list=[...list,newData]
                this.count=count+1
            } else if (info.file.status === 'error') {
                this.$message.error(`${info.file.name} file upload failed.`);
            }
        },
    }
}
</script>
