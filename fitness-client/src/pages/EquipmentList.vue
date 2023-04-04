<template>
  <div class="equip-list">
    <ul class="equip-list-header">
      <li
        v-for="(item, index) in equipmentType"
        :key="index"
        :class="{active: item.name === activeName}"
        @click="handleChangeView(item.name)">
        {{item.name}}
      </li>
    </ul>
    <div class="equip-content">
      <equip-content :equipContent="data" path="equip-album"></equip-content>
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
  name: 'equipment-list',
  components: {
    EquipContent,
    ContentList
  },
  data () {
    return {
      equipmentType: equipmentList, // 歌单导航栏类别
      activeName: '全部器械',
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
  created () {
    this.handleChangeView('全部器械')
  },
  methods: {
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },
    // 获取歌单
    handleChangeView(name) {
      this.activeName = name
      this.albumDatas = []
      if (name === '全部器械') {
        this.getEquipmentList()
      } else {
        this.getEquipOfFunc(name)
        console.log(name)
      }
    },
    // 获取全部器械
    getEquipmentList () {
      HttpManager.getEquipment()
        .then(res => {
          this.currentPage = 1
          this.albumDatas = res
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 通过类别获取歌单
    getEquipOfFunc (func) {
      HttpManager.getEquipOfFunc(func)
        .then(res => {
          this.currentPage = 1
          this.albumDatas = res
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/equip-list.scss';
</style>
