<template>
  <a-table :columns="columns" :dataSource="data" >
  <!-- :rowKey="record => record.login.uuid"
  :pagination="pagination"
  :loading="loading"
  >  -->
    <!-- <span slot="operation" slot-scope="text" class="table-operation"> -->
    <span slot="action" slot-scope="text" class="table-operation">
      <a href="javascript:;" @click="handleView">数据</a>
      <a-divider type="vertical" />
        <a-upload name="file" :multiple="true" action="//jsonplaceholder.typicode.com/posts/" :headers="headers" @change="handleSubmit">
          <a>提交</a>
        </a-upload>
    </span>
    <p slot="expandedRowRender" slot-scope="record" style="margin: 0">{{record.description}}</p>
  </a-table>
</template>



<script>

/**测试数据 */
const data= [
  { key: 1, title: '第一章作业', releaseDate: '2018-12-4', deadline: '2018-12-5', description: '作业内容：操作案例1，2，3' },
  { key: 2, title: '第二章作业', releaseDate: '2018-12-4', deadline: '2018-12-5', description: '作业内容：操作案例1，2，3' },
  { key: 3, title: '第三章作业', releaseDate: '2018-12-4', deadline: '2018-12-5', description: '作业内容：操作案例1，2，3' },
];

// import reqwest from 'reqwest'
const columns = [
  { title: '作业标题', dataIndex: 'title', key: 'title' , width:'40%'},
  { title: '发布日期', dataIndex: 'releaseDate', key: 'releaseDate', width:'20%'},
  { title: '截止日期', dataIndex: 'deadline', key: 'deadline', width:'20%'},
  { title: '选择操作', dataIndex: '', key: 'x', width:'20%',scopedSlots: { customRender: 'action' } },
];

export default({
  name:'homeworktable',
  mounted(){
     this.fetch();
  },
  // data:{

  // },
  
  data() {
    return {
      // data:[],
      data,
      columns,
      pagination:[],
      loading:false,
      headers: {
        authorization: 'authorization-text',
      }
    }
  },
  methods:{
    handleView(){

    },
    handleSubmit(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        this.$message.success(`${info.file.name} file uploaded successfully`);
        // print("a");
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name} file upload failed.`);
      }
    },
    //  handleTableChange (pagination, filters, sorter) {
    //   console.log(pagination);
    //   const pager = { ...this.pagination };
    //   pager.current = pagination.current;
    //   this.pagination = pager;
    //   this.fetch({
    //     results: pagination.pageSize,
    //     page: pagination.current,
    //     sortField: sorter.field,
    //     sortOrder: sorter.order,
    //     ...filters,
    //   });
    // },
    // fetch (params = {}) {
    //   console.log('params:', params);
    //   this.loading = true
    //   reqwest({
    //     url: 'https://randomuser.me/api',
    //     method: 'get',
    //     data: {
    //       results: 10,
    //       ...params,
    //     },
    //     type: 'json',
    //   }).then((data) => {
    //     const pagination = { ...this.pagination };
    //     // Read total count from server
    //     // pagination.total = data.totalCount;
    //     pagination.total = 200;
    //     this.loading = false;
    //     this.data = data.results;
    //     this.pagination = pagination;
    //   });
    // },
  }
})
</script>