<template>
  <div>
    <div>
      <span style="font-size:20px;font-weight:bold;margin-bottom:10px;margin-right:20px">课件列表</span>
      <!--上传-->
      <a-upload action="//jsonplaceholder.typicode.com/posts/">
        <a-button>
          <a-icon type="upload"/>上传文件
        </a-button>
      </a-upload>
    </div>
    <a-list style="min-height:350px" :loading="loading" itemLayout="horizontal" :dataSource="list">
      <!--加载更多按钮-->
      <div
        v-if="showLoadingMore"
        slot="loadMore"
        :style="{textAlign:'center',marginTop:'12px',marginTop:'12px',height:'32px',lineHeight:'32px'}"
      >
        <a-spin v-if="loadingMore"/>
        <a-button v-else @click="onLoadMore">loading more</a-button>
      </div>
      <!--列表-->
      <a-list-item slot="renderItem" slot-scope="item,index">
        <a slot="actions">预览</a>
        <a slot="actions">下载</a>
        <a v-if="isTeacher" slot="actions">删除</a>
        <a-list-item-meta>
          <span slot="description">{{item.description}}</span>
          <span slot="title">{{item.name}}</span>
        </a-list-item-meta>
        <div>上传时间：{{item.uploadTime}}</div>
      </a-list-item>
    </a-list>
  </div>
</template>


<style>
</style>

<script>
var list = [
  {
    name: "first",
    description: "the first",
    uploadTime: "2018-10-22"
  },
  {
    name: "second",
    description: "the second",
    uploadTime: "2018-10-23"
  },
  {
    name: "third",
    description: "the third",
    uploadTime: "2018-10-24"
  }
];

export default {
  name: "ResoureList",
  data: function() {
    return {
      loading: false,
      loadingMore: false,
      showLoadingMore: true,
      isTeacher: true,
      list
    };
  },
  methods: {
    //上传文件
    handleChange(info) {
      if (info.file.status !== "uploading") {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === "done") {
        this.$message.success(`${info.file.name} file uploaded successfully`);
      } else if (info.file.status === "error") {
        this.$message.error(`${info.file.name} file upload failed.`);
      }
    }
  }
};
</script>
