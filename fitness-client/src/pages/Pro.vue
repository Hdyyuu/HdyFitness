<template>
  <div class="equip-album">
    <div class="album-slide">
      <div class="album-img">
        <img :src=attachImageUrl(pro.pic) alt="">
      </div>
      <div class="album-info">
        <h2>简介：</h2>
        <span>
          {{pro.introduction}}
        </span>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">
        <p>{{pro.name}}</p>
      </div>
      <!--歌曲-->
      <div class="equip-body">
        <content-list :contentList="albumDatas" path="video-list-album" >
        </content-list>
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
        <!--        <comment :playId="songListId" :type="1"></comment>-->
      </div>
    </div>
  </div>
</template>

<script>
import mixin from '../mixins'
import AlbumContent from '../components/AlbumContent'
import Comment from '../components/Comment'
import { mapGetters } from 'vuex'
import { HttpManager } from '../api/index'
import EquipContent from "../components/EquipContent";
import ContentList from "../components/ContentList";

export default {
  name: 'pro-album',
  mixins: [mixin],
  components: {
    ContentList,
    EquipContent,
    AlbumContent,
    Comment
  },
  data () {
    return {
      pro:{},
      count: 0, // 点赞数
      videoId: '', // 歌单ID
      value3: 0,
      value5: 0,
      albumDatas:[],
      pageSize: 15, // 页数
      currentPage: 1, // 当前页
    }
  },
  computed: {
    ...mapGetters([
      'loginIn', // 登录标识
      'tempList', // 单个歌单信息
      'userId', // 用户ID
      'avator', // 用户头像
      'video'//存放的视频
    ]),
    // 计算当前表格中的数据
    data () {
      return this.albumDatas
    }
  },
  created () {
    this.pro = this.tempList
    this.getVideo(this.pro.name)
  },
  methods: {
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },

    getVideo(name) {
      HttpManager.getVideoOfName(name)
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
@import '../assets/css/equip-album.scss';
</style>
