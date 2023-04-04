<template>
  <div class="my-tool">
    <el-upload style="margin-left:10%;margin-top:5%"
               class="avatar-uploader el-upload--text"
               drag
               action="http://localhost:8082/api/uploadTest"
               multiple
               :show-file-list="false"
               :data="{SavePath: this.Path.url,id:tempList.id}"
               :on-success="handleVideoSuccess"
               :before-upload="beforeUploadVideo"
               :on-progress="uploadVideoProcess">
      <i v-if="Plus" class="el-icon-upload"></i>
      <div v-if="Plus" class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <el-progress v-if="videoFlag == true" type="circle" :percentage="videoUploadPercent" style="margin-top:30px;"></el-progress>
      <div class="el-upload__tip" slot="tip">只能上传mp4/flv/avi文件，且不超过300M</div>
    </el-upload>

    <video-player
      class="video-player vjs-custom-skin"
      ref="videoPlayer"
      :playsinline="true"
      :options="playerOptions"/>

  </div>
</template>

<script>

import {BASE_URL} from "../api/config";
import {mapGetters} from "_vuex@3.6.2@vuex";

export default {
  name: "MyTool",
  data () {
    return {
      videoForm: {
        videoId: '',
        videoUrl: ''
      },
      videoFlag: false,
      Plus: true,
      Path: {
        url: 'E:\\Desktop\\hdy-fitness\\fitness-server\\video\\uploadVideo'
      },
      videoUploadPercent: 0,
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
          //src: "http://localhost:8888/video/avi_mp4/935012111cea4626a557bd7b2a3e70c3_shendun.mp4" // url地址
          src:""
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
  computed:{
    ...mapGetters([
      'tempList', // 单个歌单信息
    ]),
  },
  mounted: function () {
  },
  methods: {
    // 视频上传前执行
    beforeUploadVideo (file) {
      const isLt300M = file.size / 1024 / 1024 < 300
      if (['video/mp4', 'video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb'].indexOf(file.type) === -1) {
        this.$message.error('请上传正确的视频格式')
        return false
      }
      if (!isLt300M) {
        this.$message.error('上传视频大小不能超过300MB哦!')
        return false
      }
    },
    // 视频上传过程中执行
    uploadVideoProcess (event, file, fileList) {
      this.Plus = false
      this.videoFlag = true
      this.videoUploadPercent = file.percentage.toFixed(0)
    },
    // 视频上传成功是执行
    handleVideoSuccess (res, file) {
      this.Plus = false
      this.videoUploadPercent = 100
      console.log(res)
      // 如果为200代表视频保存成功
      if (res.resCode === '200') {
        // 接收视频传回来的名称和保存地址
        // 至于怎么使用看你啦~
        this.videoForm.videoId = res.newVideoName
        this.videoForm.videoUrl = res.VideoUrl
        this.$message.success('视频上传成功！')
        this.playerOptions['sources'][0]['src'] = BASE_URL + '/video/avi_mp4/' + this.videoForm.videoId;
        console.log(BASE_URL + '/video/avi_mp4/' + this.videoForm.videoId);
      } else {
        this.$message.error('视频上传失败，请重新上传！')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/my-tool.scss';
</style>
