<template>
  <a-table :columns="columns" :dataSource="data" bordered>
    <template v-for="col in ['id','name', 'department',]" :slot="col" slot-scope="text, record, index">
        <div v-bind:key="col.id">
        <a-input
          v-if="record.editable"
          style="margin: -5px 0"
          :value="text"
          @change="e => handleChange(e.target.value, record.key, col)"
        />
        <template v-else>{{text}}</template>
      </div>
    </template>

    <template slot="operation" slot-scope="text, record, index">
      <div class='editable-row-operations'>
        <span v-if="record.editable">
          <a @click="() => save(record.key)">保存</a>
          <a-divider type="vertical" />
          <a-popconfirm title='是否取消编辑?' @confirm="() => cancel(record.key)">
            <a>取消</a>
          </a-popconfirm>
        </span>
        <span v-else>
          <a @click="() => edit(record.key)">编辑</a>
          <a-divider type="vertical" />
          <a @click="() => ondelete(record.key)">删除</a>
        </span>
      </div>
    </template>
  </a-table>
</template>


<script>
const columns = [{
  title: '工号',
  dataIndex: 'id',
  width: '25%',
  scopedSlots: { customRender: 'id' },
}, {
  title: '姓名',
  dataIndex: 'name',
  width: '25%',
  scopedSlots: { customRender: 'name' },
}, {
  title: '学院',
  dataIndex: 'department',
  width: '25%',
  scopedSlots: { customRender: 'department' },
},{
  title: '选择操作',
  dataIndex: 'operation',
  scopedSlots: { customRender: 'operation' },
}]

const data = []
for (let i = 0; i < 100; i++) {
  data.push({
    key: i.toString(),
    id:i,
    name: `Edrward ${i}`,
    department:'软件学院',
  })
}

export default {
  data () {
    this.cacheData = data.map(item => ({ ...item }))
    return {
      data,
      columns,
    }
  },
  methods: {
    handleChange (value, key, column) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target[column] = value
        this.data = newData
      }
    },
    ondelete(){

    },
    edit (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target.editable = true
        this.data = newData
      }
    },
    save (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        delete target.editable
        this.data = newData
        this.cacheData = newData.map(item => ({ ...item }))
      }
    },
    cancel (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        Object.assign(target, this.cacheData.filter(item => key === item.key)[0])
        delete target.editable
        this.data = newData
      }
    },
  },
}
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>