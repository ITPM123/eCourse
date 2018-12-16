<template>
    <div>
        <a-card v-model="applicant" title="申报人信息" class="card">
        <div class="block">
            <div class="part">申报人：{{applicant.realName}}</div>
            <div class="part">工号：{{applicant.code}}</div>
            <div class="part">职称：{{applicant.title}}</div>
        </div>
        <div class="block">
            <div class="part">所属学院：{{applicant.school}}</div>
            <div class="part">工作邮箱：{{applicant.email}}</div>
            <div class="part">联系方式：{{applicant.contact}}</div>
        </div>
        </a-card>

        <a-card v-model="course" title="课程信息" class="card">
            <div style="min-width:20rem;min-height:3rem">课程名称：{{course.course.name}}</div>
            <div class="block">
                <div style="min-width:5rem">课程概述：</div>
                <div>{{course.course.overview}}</div>
            </div>
            <div class="block">
                <div style="min-width:5rem">课程大纲：</div>
                <div>{{course.course.outline}}</div>
            </div>
            <div class="block">
                <div style="min-width:5rem">授课对象：</div>
                <div>{{course.course.teaching_goal}}</div>
            </div>
        </a-card>

        <a-card v-model="detail" title="审核意见" class="card">
           <a-form :form="form">
               <a-form-item>
                    <a-select v-model="result" placeholder='请选择审核意见'>
                         <a-select-option value='true'>通过</a-select-option>
                         <a-select-option value='false'>驳回</a-select-option>
                    </a-select>
                </a-form-item>

               <a-form-item >
                    <a-textarea  v-model="feedback" placeholder='Please input your name'/>
                </a-form-item>

                <a-form-item >
                    <a-button style="float:left;margin-top:2rem" type="dashed" htmlType="submit" size="large"  @click=" handleSubmit">提交</a-button>
                </a-form-item>
            </a-form>
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
import axios from "axios"

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
            course:{
                name:" ",
                outline:" ",
                overview:" ",
                teaching_goal:" ",
                person_id:" "
            },
            applicant:{
                  realName:" ",
                  code:" ",
                  title:" ",
                  school:" ",
                  email:" ",
                  contact:" "
            },
            result:" ",
            feedback: " ",
            radioStyle
        }
    },

    mounted(){
        this.init()
    },

    methods:{
        init(){
            this.course=this.$store.state.courseInfo
            console.log(this.$store.state.courseInfo)
            console.log(this.course)
            let url="?id="+this.$store.state.courseInfo.course.person_id
            axios({
                url:"/information/getInformationById"+url,
                method:"get"
            }).then(response=>{
                console.log(response)
                this.applicant=response.data.data
            })
        },

        handleSubmit(){
            let param=new FormData();
            param.append("feedback",this.feedback);
            param.append("result",this.result);
            param.append("course_id",this.$store.state.courseInfo.course.course_id);
            axios({
                url:"/reviewCourse/addReview",
                method:"post",
                data:param,
            }).then(response=>{
                console.log(response)
                console.log("提交审核信息")
            })
        },
    }
}
</script>
