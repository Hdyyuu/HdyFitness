<template>
  <div class="video-list-album">
    <div class="album-slide">
      <div class="album-img">
        <img :src=attachImageUrl(video1.pic) alt="">
      </div>
      <div class="album-info">
        <h2>简介：</h2>
        <span>
          {{video1.introduction}}
        </span>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">
        <p>{{video1.title}}</p>
      </div>
      <!--评分-->
      <div class="album-score">
        <div>
          <h3>视频评分：</h3>
          <div>
            <el-rate v-model="value1"  disabled></el-rate>
          </div>
        </div>
        <span>{{value1 * 2}}</span>
        <div>
          <h3>评价：</h3>
          <div @click="pushValue()">
            <el-rate v-model="value2" show-text allow-half></el-rate>
          </div>
        </div>
      </div>
      <!--歌曲-->
      <div class="video-body">
        <album-content :video="video1" >
          <template slot="title">歌单</template>
        </album-content>
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

export default {
  name: 'video-list-album',
  mixins: [mixin],
  components: {
    AlbumContent,
    Comment
  },
  data () {
    return {
      video1:{},
      count: 0, // 点赞数
      videoId: '', // 歌单ID
      value1: 0,
      value2:0
    }
  },
  computed: {
    ...mapGetters([
      'loginIn', // 登录标识
      'tempList', // 单个歌单信息
      'userId', // 用户ID
      'avator', // 用户头像
      'video'//存放的视频
    ])
  },
  created () {
    this.videoId = this.tempList.id // 给歌单ID赋值
    this.video1 = this.tempList
    this.getRank(this.videoId) // 获取评分
    this.getPersonRank(this.userId, this.videoId) // 获取个人
  },
  methods: {
    // 收集歌单里面的歌曲

    // 获取视频
    getVideo () {
      HttpManager.getVideoOfId(this.videoId)
        .then(res => {
          this.video1.push(res[0])
          this.$store.commit('setVideo', this.video1)
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 获取评分
    getRank (id) {
      HttpManager.getRankOfVideoId(id)
        .then(res => {
          this.value1 = res / 2
        })
        .catch(err => {
          console.log(err)
        })
    },
    getPersonRank(userID,videoID){
      HttpManager.getPersonRank(userID,videoID).then(res=>{
        this.value2 = res / 2
      })
    },
    // 提交评分
    pushValue () {
      if (this.loginIn) {
        let params = new URLSearchParams()
        params.append('userID', this.userId)
        params.append('videoID', this.videoId)
        params.append('score', this.value2 * 2)
        HttpManager.isRanked(this.userId,this.videoId).then(res=>{
          if(res){
            HttpManager.deleteRank(this.userId,this.videoId)
          }
        })
        setTimeout(()=>{
          HttpManager.setRank(params)
            .then(res => {
              if (res.code === 1) {
                setTimeout(()=>{
                  this.getRank(this.videoId)
                  this.notify('评分成功', 'success')
                },50)

              } else {
                this.notify('评分失败', 'error')
              }
            })
        },50)
      } else {
        this.value2 = null
        this.notify('请先登录', 'warning')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/video-list-album.scss';
</style>
