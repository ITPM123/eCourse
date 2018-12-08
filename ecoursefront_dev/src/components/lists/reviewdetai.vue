<template>
    <div>
        <a-card v-model="applicant" title="申报人信息" class="card">
        <div class="block">
            <div class="part">申报人：{{applicant.name}}</div>
            <div class="part">工号：{{applicant.id}}</div>
            <div class="part">职称：{{applicant.rank}}</div>
        </div>
        <div class="block">
            <div class="part">所属学院：{{applicant.institute}}</div>
            <div class="part">工作邮箱：{{applicant.email}}</div>
            <div class="part">联系方式：{{applicant.address}}</div>
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
                <div style="min-width:5rem">授课对象：</div>
                <div>{{course.target}}</div>
            </div>
        </a-card>

        <a-card v-model="detail" title="审核意见" class="card">
            <div class="block">
                <div style="min-width:5rem">是否通过：</div>
                <a-radio-group v-model="detail.isBack" :defaultValue=false>
                    <a-radio :value=false :style="radioStyle">通过</a-radio>
                    <a-radio :value=true :style="radioStyle">
                        驳回
                        <a-textarea style="margin-left:2rem" v-if="detail.isBack" placeholder="请输入驳回理由.." v-model="detail.feedback" />
                    </a-radio>
                </a-radio-group>
            </div>
            <a-popconfirm title="确认提交？" @confirm="confirm" @cancel="cancel" okText="确认" cancelText="取消">
                <a-button style="float:left;margin-top:2rem" type="dashed" htmlType="submit" size="large"  @click="handleSubmit">提交</a-button>
            </a-popconfirm>
        </a-card>
    </div>
</template>


<style>
.card{
    margin: 20px 20px;
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

//单选框样式
var radioStyle={
    display:'block',
    height:'30px',
    lineHeight:'30px'
}

export default {
    name:"reviewdetai",
    data:function(){
        return {
            applicant,
            course,
            detail,
            radioStyle
        }
    },
    methods:{

    }
}
</script>
