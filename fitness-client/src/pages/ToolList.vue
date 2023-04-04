<template>
  <div class="equip-list">
    <div class="equip-content">
      <content-list :content-list="data" path="tool-album"></content-list>
      <div class="pagination">
        <el-pagination
          @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="albumDatas.length">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import ContentList from '../components/ContentList'
import { mapGetters } from 'vuex'
import { equipmentList } from '../assets/data/equipment'
import { HttpManager } from '../api/index'
import EquipContent from "../components/EquipContent";

export default {
  name: 'ToolList',
  components: {
    EquipContent,
    ContentList
  },
  data () {
    return {
      pageSize: 15, // 页数
      currentPage: 1, // 当前页
      albumDatas: [] // 歌单
    }
  },
  computed: {
    ...mapGetters([
      'songsList'
    ]),
    // 计算当前表格中的数据
    data () {
      return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  mounted() {
    this.getToolList()
  },
  methods: {
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },
    // 获取工具
    handleChangeView: function (name) {
      this.albumDatas = []
      this.getToolList()

    },
    // 获取全部工具
    getToolList () {
      HttpManager.getTool()
        .then(res => {
          this.currentPage = 1
          this.albumDatas = res
        })
        .catch(err => {
          console.log(err)
        })
    },

  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/equip-list.scss';
</style>
