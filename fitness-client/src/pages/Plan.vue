<template>
  <div class="plan">
    <ul class="plan-header">
      <li
        v-for="(item, index) in PlanType"
        :key="index"
        :class="{active: item.name === activeName}"
        @click="handleChangeView(item)">
        {{item.name}}
      </li>
    </ul>
    <content-list :contentList="data" path="video-list-album"></content-list>
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
</template>

<script>
import ContentList from '../components/ContentList'
import { HttpManager } from '../api/index'
import {PlanType} from "../assets/data/plan";

export default {
  name: 'plan',
  components: {
    ContentList
  },
  data () {
    return {
      PlanType: PlanType, // 歌手导航栏类别
      activeName: '胸',
      pageSize: 15, // 页数
      currentPage: 1, // 当前页
      albumDatas: []
    }
  },
  computed: {
    // 计算当前表格中的数据
    data () {
      return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },

  created() {
    this.getVideoType (this.activeName)
  },

  methods: {
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },
    handleChangeView (item) {
      this.activeName = item.name
      this.albumDatas = []
      this.getVideoType(item.name)
    },
    // 通过Type对视频分类
    getVideoType (type) {
      HttpManager.getVideoOfType(type)
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
@import '../assets/css/plan.scss';
</style>
