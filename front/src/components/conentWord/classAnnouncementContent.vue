<template>
    <div>
        <a-card v-model="course" title="公告信息" class="card">
            <div style="min-width:20rem;min-height:3rem">标题：{{announcement.title}}</div>
            <div class="block">
                <div style="min-width:5rem">内容：</div>
                <div>{{announcement.content}}</div>
            </div>
             <a-icon type="upload" /> 附件:
             <a-button type='primary' @click="clickHandle" style="float:right">返回</a-button>
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
var announcement={
    title:"XXXX",
    content:"然而，看完电影之后，很多观众都会有一个疑惑：电影名字为什么叫“你好，之华”？真正的灵魂女主，明明是成年之后并没有正面出场的之南。电影就是从她的葬礼开始演起，在回忆交织中还原了她的故事。"
}

export default {
    name:"announcementContent",
    data:function(){
        return {
          announcement,
        }
    },
    methods:{
         clickHandle(){
         this.$store.commit('changeContent',"courseAnnounceList")
      },
    }
}
</script>
