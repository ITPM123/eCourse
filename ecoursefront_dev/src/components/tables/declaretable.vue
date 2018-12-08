<template>
  <a-table :columns="columns"
    :rowKey="record => record.login.uuid"
    :dataSource="data"
    :pagination="pagination"
    :loading="loading"
    @change="handleTableChange"
  >
    <template slot="name" slot-scope="name">
      <!-- {{name.first}} {{name.last}} -->
      <a @click="viewHandle">{{name.first}}</a>
    </template>

    <!-- /**操作*/ -->
    <template slot="operation" slot-scope="text, record">
        <a-popconfirm
          v-if="data.length"
          title="Sure to delete?"
          @confirm="() => onDelete(record.key)">
          <a href="javascript:;">Delete</a>
        </a-popconfirm>
    </template>

  </a-table>
</template>
<script>
import axios from 'axios';
const columns = [{
  title: '课程名称',
  dataIndex: 'name',
  key:'name',
  sorter: true,
  width: '30%',
  scopedSlots: { customRender: 'name' },
}, {
  title: '审核状态',
  dataIndex: 'gender',
  // key:'gender',
  filters: [
    { text: '已通过', value: 'male' },
    { text: '未通过', value: 'female' },
  ],
  // onFilter: (value, record) => record.address.indexOf(value) === 0,
  width: '20%',
  
}, {
  title: '申报日期',
  dataIndex: 'email',
  // key:'email',
  width: '30%',
},
{
  title: '操作',
  dataIndex: 'operation',
  scopedSlots: { customRender: 'operation' },
}];

export default {
  name:'declaremanage',
  mounted() {
    this.fetch();
  },
  data() {
    return {
      data: [],
      pagination: {},
      loading: false,
      columns,
    }
  },
  methods: {
    handleTableChange (pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        results: pagination.pageSize,
        page: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    },
    created:()=>{
      axios({
      method:"get",
      url:'https://randomuser.me/api',
      data:{
        results:10
      }
    }).then((data)=>{
      console.log("这里是数据：",data)
      const pagination={...this.pagination};
      this.loading=false;
      this.data=data.results;
      this.pagination=pagination
    });
    },
    fetch(params={}){
      this.loading=true;
      axios({
        method:'get',
        url:'https://randomuser.me/api',
        data:{
          results:10,
          ...params,
        },
        type:'json'
      }).then((data)=>{
        console.log("请求数据",data)
        const pagination={...this.pagination};
        pagination.total=200;
        this.loading=false;
        this.data=data.results;
        this.pagination=pagination;
      })
    },
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
      onDelete (key) {
      const data = [...this.data]
      this.data = data.filter(item => item.key!== key)
    },
    viewHandle(){
        this.$router.push('detail')
    }
  },
}
</script>