<template>
    <div>
        <div style="font-weight:bold;font-size:2em;margin-top:20px;margin-left:20px">
            审核详情
        </div>
        <a-card v-model="detail" title="审批详情" class=card>
            <div class="block">
                <div class="part">审批人：{{detail.reviewer}}</div>
                <div class="part">申报编号：{{detail.aid}}</div>
                <div class="part">
                    <span>审核状态：</span>
                    <a-tag v-if="detail.isBack" color="#E74C3C">驳回</a-tag>
                    <a-tag v-else color="#1ABC9C">通过</a-tag>
                </div>
            </div>
            <div class="block">
                <div class="part">审批时间：{{detail.reviewTime}}</div>
                <div class="part">申报时间：{{detail.applyTime}}</div>
            </div>
        </a-card>

        <a-card v-model="applicant" title="申报人信息" class="card">
            <div class="block">
                <div class="part">申报人：{{applicant.name}}</div>
                <div class="part">工号：{{applicant.id}}</div>
                <div class="part">职称：{{applicant.rank}}</div>
            </div>
            <div class="block">
                <div class="part">所属学院：{{applicant.institute}}</div>
                <div class="part">工作邮箱：{{applicant.email}}</div>
                <div class="part">联系地址：{{applicant.address}}</div>
            </div>
        </a-card>

        <a-card v-model="course" title="课程信息" class="card">
            <div style="min-width:20rem;min-height:3rem">课程名称：{{course.name}}</div>
            <div class="block">
                <div style="min-width:5rem">课程概述：</div>
                <div>{{course.summary}}</div>
            </div>
            <div class="block">
                <div style="min-width:5rem">课程大纲：</div>
                <div>{{course.outline}}</div>
            </div>
            <div class="block">
                <div style="min-width:5rem">授课对象</div>
                <div>{{course.target}}</div>
            </div>
        </a-card>
    </div>
</template>

<style>
.card{
    margin: 50px 20px;
}
.part{
    min-width: 15rem;
}
.block{
    display: flex;
    flex-direction: row;
    margin-bottom:2rem;
    
}
</style>

<script>
var applicant={
    //申请人信息
    name:"张三",
    id:"SCUT001",
    rank:"教授",
    institute:"软件学院",
    email:"zhangsan@outlook.com",
    address:"华南理工大学大学城校区B7"
}

var course={
    //课程信息
    name:"移动开发导论",
    summary:"《移动应用开发》是以企业工程项目（物联网智慧城市移动端项目）为基础，将项目拆解成10个子项目，项目内容安排由易到难，最终以Android技术知识点为教学项目的形式展现给学习者，通过该项目化教学，学习者可以完整的重构、复原该项目，掌握物联网移动应用开发的常用技术，熟悉项目的开发过程。",
    outline:"1.xxxxxxx \n 2.xxxxxxx \n 3.xxxxxxx",
    target:"全体学生"
}

var detail={
    //申报信息
    isBack:false,
    feedback: null,
    aid:"C0001",
    hasReviewed:true,
    applyTime:"2018-10-20",
    reviewer:"李四",
    reviewTime:"2018-10-22"
}

var title="审批号："+detail.aid 

export default {
    name:"ReviewResult",
    data:function(){
        return {
            applicant,
            course,
            detail,
            title
        }
    },
    methods:{

    }
}
</script>
