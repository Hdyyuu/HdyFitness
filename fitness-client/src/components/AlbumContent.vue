<template>
  <div class="content">
    <video-player
      class="video-player vjs-custom-skin"
      ref="videoPlayer"
      :playsinline="true"
      :options="playerOptions"/>
    <!--点赞-->
    <div class="item">
      <svg :class="{ active: zanIsActive }" class="icon" aria-hidden="true" color="red"  @click="like">
        <use :xlink:href="ZAN"></use>
      </svg>
      <p v-text="like_count"/>
    </div>
    <!--收藏-->
    <div class="item" >
      <svg :class="{ active: shouCangIsActive }" class="icon" aria-hidden="true" color="red" @click="collect">
        <use :xlink:href="SHOUCANG"></use>
      </svg>
      <p v-text="collect_count"/>
    </div>
    <div class="comment">
      <comment :playId="tempList.id"></comment>
    </div>
  </div>
</template>


<script>
import mixin from '../mixins'
import { mapGetters } from 'vuex'
import {BASE_URL} from "../api/config";
import { ICON } from '../assets/icon/index'
import {HttpManager} from "../api";
import Comment from '../components/Comment'

export default {
  name: 'album-content',
  mixins: [mixin],
  props: {
    video: Object
  },
  components:{Comment},
  data () {
    return {
      like_count:null,
      collect_count:null,
      zanIsActive:null,
      shouCangIsActive:null,
      ZAN: ICON.ZAN,
      SHOUCANG:ICON.SHOUCANG,
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
          type: "video/mp4",// 这里的种类支持很多种：基本视频格式、直播、流媒体等，具体可以参看git网址项目
          src: "" // url地址
        }],
        //poster: "", // 你的封面地址
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true  // 全屏按钮
        }
      }
    }
  },
  computed: {
    ...mapGetters([
      'tempList', // 视频ID
      'loginIn',
      'userId'
    ]),

  },

  methods:{
    like(){
      if (this.loginIn) {
        if (this.zanIsActive == false) {
          var params = new URLSearchParams();
          params.append('userID', this.userId);
          params.append('videoID', this.tempList.id);
          console.log(this.tempList.id);
          HttpManager.setLike(params);
          setTimeout(() => {
            this.getLikeCountOfVideo()
          }, 50);
          this.zanIsActive = true;
          console.log(this.zanIsActive)
        }
        else if (this.zanIsActive == true) {
          HttpManager.deleteLike(this.userId, this.tempList.id);
          setTimeout(() => {
            this.getLikeCountOfVideo()
          }, 50);
          this.zanIsActive = false;
          console.log(this.zanIsActive)
        }
      } else {
        this.notify('请先登录', 'warning');
       }
    },
    collect() {
      if (this.loginIn) {
        if (this.shouCangIsActive == false) {
          var params = new URLSearchParams();
          params.append('userID', this.userId);
          params.append('videoID', this.tempList.id);
          console.log(this.tempList.id);
          HttpManager.setCollect(params);
          setTimeout(() => {
            this.getCollectCountOfVideo()
          }, 50);
          this.shouCangIsActive = true;
          console.log(this.shouCangIsActive)
        }
        else if (this.shouCangIsActive == true) {
          HttpManager.deleteCollect(this.userId, this.tempList.id);
          setTimeout(() => {
            this.getCollectCountOfVideo()
          }, 50);
          this.shouCangIsActive = false;
          console.log(this.shouCangIsActive)
        }
      } else {
        this.notify('请先登录', 'warning');
      }
    },
    getLikeCountOfVideo(){
      HttpManager.getLike(this.tempList.id).then(res => {this.like_count = res.likeCount});
    },
    getCollectCountOfVideo(){
      HttpManager.getCollect(this.tempList.id).then(res => {this.collect_count = res.collectCount;});
    },
  },

  created() {
    this.playerOptions['sources'][0]['src'] = BASE_URL + this.tempList.url;
    this.getLikeCountOfVideo()
    this.getCollectCountOfVideo()
    HttpManager.existLike(this.userId, this.tempList.id).then(res=>{this.zanIsActive = res})
    HttpManager.existCollect(this.userId, this.tempList.id).then(res=>{this.shouCangIsActive = res})
    },

}
</script>

<style lang="scss" scoped>
@import '../assets/css/album-content.scss';
@import '../assets/css/play-bar.scss';
.icon.active
{
  color:red;
}

</style>

