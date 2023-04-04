<template>
  <div class="song-list-album">
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
            <el-rate v-model="value5" disabled></el-rate>
          </div>
        </div>
        <span>{{value5 * 2}}</span>
        <div>
          <h3>评价：</h3>
          <div @click="pushValue()">
            <el-rate v-model="value3" show-text allow-half></el-rate>
          </div>
        </div>
      </div>
      <!--歌曲-->
      <div class="songs-body">
        <album-content :video="video" >
          <template slot="title">歌单</template>
        </album-content>
<!--        <comment :playId="songListId" :type="1"></comment>-->
      </div>
    </div>
  </div>
</template>

<script>
import mixin from '../../mixins'
import AlbumContent from '../../components/AlbumContent'
import Comment from '../../components/Comment'
import { mapGetters } from 'vuex'
import { HttpManager } from '../../api'

export default {
  name: 'song-list-album',
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
      value3: 0,
      value5: 0
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
    //this.getVideo() // 获取视频
   // this.getRank(this.songListId) // 获取评分
  },
  methods: {
    // 收集歌单里面的歌曲

    // 获取单里的歌曲
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
      HttpManager.getRankOfSongListId(id)
        .then(res => {
          this.value5 = res / 2
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 提交评分
    pushValue () {
      if (this.loginIn) {
        let params = new URLSearchParams()
        params.append('songListId', this.songListId)
        params.append('consumerId', this.userId)
        params.append('score', this.value3 * 2)
        HttpManager.setRank(params)
          .then(res => {
            if (res.code === 1) {
              this.getRank(this.songListId)
              this.notify('评分成功', 'success')
            } else {
              this.notify('评分失败', 'error')
            }
          })
          .catch(err => {
            console.log(err)
          })
      } else {
        this.value3 = null
        this.notify('请先登录', 'warning')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import 'src/assets/css/song-list-album';
</style>
