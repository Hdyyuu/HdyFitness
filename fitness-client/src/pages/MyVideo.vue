<template>
  <div class="my-video">
    <div class="album-slide">
      <div class="album-img">
        <img :src=attachImageUrl(avator) alt="">
      </div>
      <ul class="album-info">
        <li>昵称： {{username}}</li>
        <li>性别： {{userSex}}</li>
        <li>生日： {{birth}}</li>
        <li>故乡： {{location}}</li>
      </ul>
    </div>
    <div class="album-content">
      <div class="album-title">个性签名: {{introduction}}</div>
      <div class="videos-body" style="background-color: white">
        <content-list :contentList="collectList" path="video-list-album"></content-list>
        <div class="pagination">
          <el-pagination
            @current-change="handleCurrentChange"
            background
            layout="total, prev, pager, next"
            :current-page="currentPage"
            :page-size="pageSize"
            :total="collectList.length">
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import mixin from '../mixins'
import AlbumContent from '../components/AlbumContent'
import { mapGetters } from 'vuex'
import { HttpManager } from '../api/index'
import ContentList from "../components/ContentList";

export default {
  name: 'my-video',
  mixins: [mixin],
  components: {
    ContentList
  },
  data () {
    return {
      avator: '',
      username: '',
      userSex: '',
      birth: '',
      location: '',
      introduction: '',
      collection: [], // 收藏
      collectList:[],//视频
      pageSize: 15, // 页数
      currentPage: 1, // 当前页
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'id',
      'listOfSongs' // 存放的音乐
    ])
  },
  mounted () {
    this.getMsg(this.userId)
    this.getCollection(this.userId)
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
    getMsg (id) {
      HttpManager.getUserOfId(id)
        .then(res => {
          this.username = res[0].username
          this.getuserSex(res[0].sex)
          this.birth = this.attachBirth(res[0].birth)
          this.introduction = res[0].introduction
          this.location = res[0].location
          this.avator = res[0].avator
        })
        .catch(err => {
          console.log(err)
        })
    },
    getuserSex (sex) {
      if (sex === 0) {
        this.userSex = '女'
      } else if (sex === 1) {
        this.userSex = '男'
      }
    },
    // 收藏的歌曲ID
    getCollection (userId) {
      HttpManager.getCollectOfUser(userId)
        .then(res => {
          this.collection = res
          // 通过歌曲ID获取歌曲信息
          for (let item of this.collection) {
            this.getCollectVideos(item.videoID)
          }
          this.$store.commit('setListOfSongs', this.collectList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 获取收藏的歌曲
    getCollectVideos (id) {
      HttpManager.getVideoOfId(id)
        .then(res => {
          this.collectList.push(res)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/my-video.scss';
</style>
