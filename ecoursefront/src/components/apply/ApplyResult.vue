<template>
    <div>
        <div style="font-weight:bold;font-size:2em;margin-top:20px;margin-left:20px">
            申报进度
        </div>
        <!--申报情况-->
        <a-card v-model="detail" title="审批详情" class=card>
            <div class="block">
                <div class="part">申报编号：{{detail.aid}}</div>
                <div class="part">申报时间：{{detail.applyTime}}</div>
                <div class="part">
                    <span>审核状态：</span>
                    <span v-if="detail.hasReviewed">
                        <a-tag v-if="detail.isBack" color="#E74C3C">驳回</a-tag>
                        <a-tag v-else color="#1ABC9C">通过</a-tag>
                    </span>
                    <a-tag v-else color="#3498DB">审核中</a-tag>
                </div>
            </div>
            <div class="block" v-if="detail.hasReviewed">
                <span class="part">审批时间：{{detail.reviewTime}}</span>
                <div v-if="detail.isBack" style="display:flex;flex-direction:row">
                    <span style="min-width:5rem">反馈建议：</span>
                    <div>{{detail.feedback}}</div>
                </div>
            </div>
        </a-card>
        <!--申报人信息-->
        <a-card title="申报人信息" class="card">
            <div class="block">
                <div class="part">
                    <span style="min-width:3rem;margin-top:5px">姓名：</span>
                    <a-input v-model="teacherInfo.name" disabled="true"></a-input>
                </div>
                <div class="part">
                    <span style="min-width:3rem;margin-top:5px">工号：</span>
                    <a-input v-model="teacherInfo.id" disabled="true"></a-input>
                </div>
                <div class="part">
                    <span style="min-width:3rem;margin-top:5px">学院：</span>
                    <a-input v-model="teacherInfo.institute" disabled="true"></a-input>
                </div>
            </div>
            <div class="block">
                <div class="part">
                    <span style="min-width:3rem;margin-top:5px" >职称：</span>
                    <a-input v-model="teacherInfo.rank" disabled="true"></a-input>
                </div>
                <div class="part">
                    <span style="min-width:3rem;margin-top:5px">邮箱：</span>
                    <a-input v-model="teacherInfo.email" disabled="true"></a-input>
                </div>
                <div class="part">
                    <span style="min-width:3rem;margin-top:5px">地址：</span>
                    <a-input v-model="teacherInfo.address" disabled="true"></a-input>
                </div>
            </div>
        </a-card>
        <!--课程信息-->
        <a-card title="课程信息" class="card">
            <div style="display:flex;flex-direction:row">
                <div style="display:felx;flex-direction:column;width:70vh">
                    <div class="block">
                        <div class="part">
                            <span style="min-width:5rem;margin-top:5px">课程名称</span>
                            <a-input style="width:70vh" v-model="courseInfo.name" disabled="true" placeholder="请输入课程名称.."></a-input>
                        </div>
                    </div>
                    <div>
                        <div class="part">
                            <span style="min-width:5rem;margin-top:5px">课程概述</span>
                            <a-textarea placeholder="请输入课程概述.." disabled="true" v-model="courseInfo.summary" :autosize="{minRows:4,maxRows:6}"></a-textarea>
                        </div>
                    </div>
                </div>
                <!--头像-->
                <div class="clearfix" style="margin-left:100px">
                    <img src="../../assets/logo.png"/>
                </div>
            </div>
            <div >
                <div class="part">
                    <span style="min-width:5rem;margin-top:5px">课程大纲</span>
                    <a-textarea placeholder="请输入课程大纲.." disabled="true" v-model="courseInfo.outline" :autosize="{minRows:4,maxRows:6}"></a-textarea>
                </div>
            </div>
            <div >
                <div class="part">
                    <span style="min-width:5rem;margin-top:5px">授课目标</span>
                    <a-textarea placeholder="请输入授课目标" disabled="true" v-model="courseInfo.target" :autosize="{minRows:4,maxRows:6}"></a-textarea>
                </div>
            </div>
        </a-card>
    </div>
</template>

<style>
.card{
    margin: 20px 20px;
}
.part{
    display: flex;
    flex-direction: row;
    margin-right: 2rem;
}


.block{
    display: flex;
    flex-direction: row;
    height: 2rem;
    /* margin-bottom:2rem; */
    
}

/*头像相关*/
.ant-upload-select-picture-card i {
    font-size: 32px;
    color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
}
</style>

<script>

var teacherInfo={
    //共有部分
    name:"张三",
    id:"SCUT0001",
    institute:"软件学院",
    address:"华南理工大学大学城校区",
    email:"scut@outlook.com",
    tags:['机器学习','数据挖掘'],
    signature:"生活就像海洋，只有意志坚强的人才能到达彼岸",

    //教师特有
    rank:"副教授",

}

var courseInfo={
    name:"移动开发导论",
    summary:"《移动应用开发》是以企业工程项目（物联网智慧城市移动端项目）为基础，将项目拆解成10个子项目，项目内容安排由易到难，最终以Android技术知识点为教学项目的形式展现给学习者，通过该项目化教学，学习者可以完整的重构、复原该项目，掌握物联网移动应用开发的常用技术，熟悉项目的开发过程。",
    outline:"1.xxxxxxx \n 2.xxxxxxx \n 3.xxxxxxx",
    target:"全体学生",
}

var detail={
    //申报信息
    aid:"C0001",
    hasReviewed:true,
    applyTime:"2018-10-20",

    //审核信息
    isBack:true,
    feedback: "再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改再改改",
    reviewer:"李四",
    reviewTime:"2018-10-22"
}


export default {
    name:"ApplyResult",
    data:function(){
        return {
            teacherInfo,
            courseInfo,
            detail,
        }
    },
    methods:{
    }
}
</script>