<template>
    <div>
        
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
                <div style="display:flex;flex-direction:column;width:70%">
                    <div>
                        <div class="part">
                            <span style="min-width:5rem;margin-top:5px">课程名称</span>
                            <a-input v-model="courseInfo.name" placeholder="请输入课程名称.."></a-input>
                        </div>
                    </div>
                    <div>
                        <div class="part">
                            <span style="min-width:5rem;margin-top:5px">课程概述</span>
                            <a-textarea placeholder="请输入课程概述.." v-model="courseInfo.summary" :autosize="{minRows:4,maxRows:6}"></a-textarea>
                        </div>
                    </div>
                </div>
                <!--头像list-->
                <div class="clearfix" style="margin-left:100px">
                    <a-upload
                    action="//jsonplaceholder.typicode.com/posts/"
                    listType="picture-card"
                    :fileList="fileList"
                    @preview="handlePreview"
                    @change="handleChange"
                    >
                    <div v-if="fileList.length < 1">
                        <a-icon type="plus" />
                        <div class="ant-upload-text">Upload</div>
                    </div>
                    </a-upload>
                    <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
                    <img alt="example" style="width: 100%" :src="previewImage" />
                    </a-modal>
                </div>
            </div>
            <div >
                <div class="part">
                    <span style="min-width:5rem;margin-top:5px">课程大纲</span>
                    <a-textarea placeholder="请输入课程大纲.." v-model="courseInfo.outline" :autosize="{minRows:4,maxRows:6}"></a-textarea>
                </div>
            </div>
            <div >
                <div class="part">
                    <span style="min-width:5rem;margin-top:5px">授课目标</span>
                    <a-textarea placeholder="请输入授课目标" v-model="courseInfo.target" :autosize="{minRows:4,maxRows:6}"></a-textarea>
                </div>
            </div>
            <a-popconfirm title="确认提交？" @confirm="confirm" @cancel="cancel" okText="确认" cancelText="取消">
                <a-button style="float:left" type="primary" :size="large">提交申报信息</a-button>
            </a-popconfirm>
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
    margin-bottom: 2rem;
}


.block{
    display: flex;
    flex-direction: row;
    height: 2rem;
    margin-bottom:2rem;
    
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
    name:null,
    summary:null,
    outline:null,
    target:null,
}

//头像上传
var fileList=[]

export default {
    name:"declarecourse",
    data:function(){
        return {
            teacherInfo,
            courseInfo,
            
            //头像
            fileList,
            previewVisible:false,
            previewImage:'',
        }
    },
    methods:{
        //上传头像
        handleCancel () {
            this.previewVisible = false
        },
        handlePreview (file) {
            this.previewImage = file.url || file.thumbUrl
            this.previewVisible = true
        },
        handleChange ({ fileList }) {
            this.fileList = fileList
        },

    }
}
</script>