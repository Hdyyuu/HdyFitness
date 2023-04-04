const ICON = {
  BOFANG: '#icon-bofang', // 播放
  ZANTING: '#icon-zanting', // 暂停
  YINLIANG: '#icon-yinliang', // 音量有背景
  YINLIANG1: '#icon-yinliang1', // 音量无背景
  ERJI: '#icon-erji', // 耳机
  SOUSUO: '#icon-sousuo', // 搜索
  XIAZAI: '#icon-xiazai', // 下载
  ZHEDIE: '#icon-jiantou-xia-cuxiantiao', // 折叠
  SHANGYISHOU: '#icon-ziyuanldpi', // 上一首
  XIAYISHOU: '#icon-ziyuanldpi1', // 下一首
  JINGYIN: '#icon-yinliangjingyinheix', // 静音
  LIEBIAO: '#icon-liebiao', // 列表
  XIHUAN: '#icon-xihuan-shi', // 收藏
  ZAN: '#icon-dianzan_kuai', // 赞
  SHOUCANG: '#icon-shoucang'
}

const VIDEO = {
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

export {
  ICON,
  VIDEO
}
