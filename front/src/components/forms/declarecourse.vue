<template>
  <div>
    <a-card title="课程信息" class="card">
      <div style="display:flex;flex-direction:row">
        <div style="display:flex;flex-direction:column;width:70%">
          <div>
            <div class="part">
              <span style="min-width:5rem;margin-top:5px">课程名称</span>
              <a-input v-model="name" placeholder="请输入课程名称.."></a-input>
            </div>
          </div>
          <div>
            <div class="part">
              <span style="min-width:5rem;margin-top:5px">课程概述</span>
              <a-textarea
                placeholder="请输入课程概述.."
                v-model="overview"
                :autosize="{minRows:4,maxRows:6}"
              ></a-textarea>
            </div>
          </div>
        </div>
      </div>
      <div>
        <div class="part">
          <span style="min-width:5rem;margin-top:5px">课程大纲</span>
          <a-textarea placeholder="请输入课程大纲.." v-model="outline" :autosize="{minRows:4,maxRows:6}"></a-textarea>
        </div>
      </div>
      <div>
        <div class="part">
          <span style="min-width:5rem;margin-top:5px">授课目标</span>
          <a-textarea
            placeholder="请输入授课目标"
            v-model="teaching_goal"
            :autosize="{minRows:4,maxRows:6}"
          ></a-textarea>
        </div>
      </div>
      <!-- <a-popconfirm title="确认提交？" @confirm="confirm" @cancel="cancel" okText="确认" cancelText="取消"> -->
      <a-button style="float:left" type="primary" size="large" @click="handleSubmit">提交</a-button>
      <!-- </a-popconfirm> -->
    </a-card>
  </div>
</template>

<style>
.card {
  margin: 20px 20px;
}
.part {
  display: flex;
  flex-direction: row;
  margin-right: 2rem;
  margin-bottom: 2rem;
}

.block {
  display: flex;
  flex-direction: row;
  height: 2rem;
  margin-bottom: 2rem;
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
import axios from "axios";

export default {
  name: "declarecourse",

  data: function() {
    return {
      name: "",
      overview: "",
      outline: "",
      teaching_goal: ""
    };
  },

  methods: {
    /**提交申报信息 */
    handleSubmit() {
      let param = new FormData();
      param.append("name", this.name);
      param.append("credit", 4);
      param.append("outline", this.outline);
      param.append("overview", this.overview);
      param.append("teaching_goal", this.teaching_goal);
      param.append("image", "");
      axios({
        url: "/course/create",
        method: "post",
        data: param
      }).then(response => {
        console.log(response);
        if (response.status == 200) 
        this.$store.commit('changeContent','declareCourse')
      });
    }
  }
};
</script>