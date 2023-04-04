<template>
  <div class="home">
    <!--轮播图-->
    <el-carousel class="swiper-container" :interval="4000" type="card" height="280px">
      <el-carousel-item v-for="(item, index) in swiperList" :key="index">
        <img :src="item.picImg"/>
      </el-carousel-item>
    </el-carousel>
    <!--热门歌单-->
    <div class="section">
      <div class="section-title">视频分享</div>
      <content-list :contentList="videoList" path="video-list-album"></content-list>
    </div>
    <!--pro-->
    <div class="section">
      <div class="section-title">健身大咖</div>
      <equip-content :equipContent="proList" path="pro-album"></equip-content>
    </div>
  </div>
</template>

<script>
import ContentList from '../components/ContentList'
import { swiperList } from '../assets/data/swiper'
import { HttpManager } from '../api/index'
import EquipContent from "../components/EquipContent";

export default {
  name: 'home',
  components: {
    EquipContent,
    ContentList
  },
  data () {
    return {
      swiperList: swiperList, // 轮播图列表
      proList: [], // 大佬列表
      videoList:[]
    }
  },
  created () {

    // 获取歌手列表
    this.getProList()
    this.getAllVideo()
  },
  methods: {

    getProList () {
      HttpManager.getAllPro()
        .then(res => {
          this.proList = res.sort().slice(0, 10)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getAllVideo() {
      HttpManager.getAllVideo()
        .then(res => {
          console.log(res)
          this.videoList = res.sort().slice(0, 10)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/home.scss';
</style>
