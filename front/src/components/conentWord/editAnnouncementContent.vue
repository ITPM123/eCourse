<template>
  <a-table :columns="columns" :dataSource="data" bordered>
    <template
      v-for="col in ['id','name', 'department',]"
      :slot="col"
      slot-scope="text, record, index"
    >
      <div v-bind:key="col.id">
        <a-input
          v-if="editable"
          style="margin: -5px 0"
          :value="text"
          @change="e => handleChange(e.target.value, record.key, col)"
        />
        <template v-else>{{text}}</template>
      </div>
    </template>

    <template slot="operation" slot-scope="text, record, index">
      <div class="editable-row-operations">
        <span v-if="editable">
          <a @click="() => save(record.key)">保存</a>
          <a-divider type="vertical"/>
          <a-popconfirm
            title="是否取消编辑?"
            @confirm="() => cancel(record.key)"
            okText="确认"
            cancelText="取消"
          >
            <a>取消</a>
          </a-popconfirm>
        </span>
        <span v-else>
          <a @click="() => edit(record.key)">编辑</a>
          <a-divider type="vertical"/>
          <a-popconfirm
            v-if="data.length"
            title="确认删除?"
            @confirm="() => onDelete(record.key)"
            okText="确认"
            cancelText="取消"
          >
            <a>删除</a>
          </a-popconfirm>
        </span>
      </div>
    </template>
  </a-table>
</template>


<script>
const columns = [
  {
    title: "标题",
    dataIndex: "title",
    width: "25%",
    scopedSlots: { customRender: "title" }
  },
  {
    title: "内容",
    dataIndex: "content",
    width: "25%",
    scopedSlots: { customRender: "content" }
  },
  {
    title: "选择操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" }
  }
];

var data = [
  {
    key: 1,
    title: "sss",
    content: "dddddddddddddddddddddddddddd"
  }
];

export default {
  data() {
    this.cacheData = data.map(item => ({ ...item }));
    return {
      data,
      columns,
      editable: true
    };
  },
  methods: {
    handleChange(value, key, column) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        target[column] = value;
        this.data = newData;
      }
    },
    onDelete(key) {
      const dataSource = [...this.data];
      this.data = dataSource.filter(item => item.key !== key);
    },
    edit(key) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        target.editable = true;
        this.data = newData;
      }
    },
    save(key) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        delete target.editable;
        this.data = newData;
        this.cacheData = newData.map(item => ({ ...item }));
      }
    },
    cancel(key) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        Object.assign(
          target,
          this.cacheData.filter(item => key === item.key)[0]
        );
        delete target.editable;
        this.data = newData;
      }
    }
    /*mounted(){
      axios({
        method:'get',//从后端获取教务员列表
        url:''，
      }).then(res=>{
        this.data=res.data
      }).catch(function(err){
        console.log(err)
    })


      //将新的教师列表更新到后端
      axios({
        method:'posh',
        url:'',
        data:data
      }).then(res=>{
        alert('post code done');
    }).catch(function(err){
        console.log(err)
    });

    }*/
  }
};
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>