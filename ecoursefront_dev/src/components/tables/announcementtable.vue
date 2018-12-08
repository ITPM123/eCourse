<template>
  <div>
    <a-table bordered :dataSource="data" :columns="columns">
      <template slot="operation" slot-scope="text, record">
        <a href="javascript:;" @click="handleView">查看</a>
        <a-divider type="vertical" />
        <a href="javascript:;" @click="handleEdit">编辑</a>
        <a-divider type="vertical" /> 
        <a-popconfirm
          v-if="data.length"
          title="Sure to delete?"
          @confirm="() => onDelete(record.key)">
          <a href="javascript:;">删除</a>
        </a-popconfirm>
      </template>

    </a-table>
  </div>
</template>

<script>
/**测试 */
const data= [{
        key: '0',
        title: 'Edward King 0',
        releaseDate: '32',
        announcer: ' no. 0',
      }, {
        key: '1',
        title: 'Edward King 1',
        releaseDate: '32',
        announcer: ' Park',
      },
      {
        key: '2',
        title: 'Edward King 1',
        releaseDate: '32',
        announcer: 'Lon',
      },
      {
        key: '3',
        title: 'Edward King 1',
        releaseDate: '32',
        announcer: 'London',
      }]


const columns= [{
  title: '标题',
  dataIndex: 'title',
  width: '50%',
  }, {
  title: '发布时间',
  dataIndex: 'releaseDate',
  }, {
  title: '发布者',
  dataIndex: 'announcer',
  }, {
  title: '选择操作',
  dataIndex: 'operation',
  scopedSlots: { customRender: 'operation' },
 }]



export default {
    name:'announcementtable',
  components: {
    
  },
  data () {
    return {
      data,
      columns,
    }
  },
  methods: {
    onDelete (key) {
      const data = [...this.data]
      this.data = data.filter(item => item.key !== key)
    },
    handleView(){

    },
    handleEdit(){

    },
  },
}
</script>