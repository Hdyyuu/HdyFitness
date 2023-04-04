import { get, post } from './request'

const HttpManager = {
  // =======================> 用户 API
  // 登录
  loginIn: (params) => post(`user/login/status`, params),
  // 注册
  SignUp: (params) => post(`user/add`, params),
  // 更新用户信息
  updateUserMsg: (params) => post(`user/update`, params),
  // 返回指定ID的用户
  getUserOfId: (id) => get(`user/detail?id=${id}`),

  // =======================> 歌单 API
  // 获取全部歌单
  getSongList: () => get('songList'),
  // 获取全部器械
  getEquipment: () => get('equipment'),
  // 获取器械func
  getEquipOfFunc: (func) => get(`equipment/func/detail?func=${func}`),
  // 获取全部工具
  getTool: () => get('tool'),
  // 获取全部pro
  getAllPro: () => get('pro'),
  // 获取歌单类型
  getSongListOfStyle: (style) => get(`songList/style/detail?style=${style}`),
  // 返回标题包含文字的歌单
  getSongListOfLikeTitle: (keywords) => get(`songList/likeTitle/detail?title=${keywords}`),
  // 返回歌单里指定歌单ID的歌曲
  getListSongOfSongId: (songListId) => get(`listSong/detail?songListId=${songListId}`),

  // =======================> 歌手 API
  // 返回所有歌手
  getAllSinger: () => get('singer'),
  // 通过性别对歌手分类
  getSingerOfSex: (sex) => get(`singer/sex/detail?sex=${sex}`),

  // =======================> 收藏 API
  // 返回的指定用户ID的收藏列表
  //getCollectionOfUser: (userId) => get(`collection/detail?userId=${userId}`),
  // 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
  //setCollection: (params) => post(`collection/add`, params),
  //点赞
  setLike: (params) => post(`like/add`, params),
  //取消点赞
  deleteLike: (userID,videoID) => get(`like/delete?userID=${userID}&videoID=${videoID}`),
  //删除评价
  deleteRank: (userID,videoID) => get(`rank/delete?userID=${userID}&videoID=${videoID}`),
  //点赞数
  getLike:(videoID)=>get(`like/count?videoID=${videoID}`),
  //是否点赞
  existLike: (userID,videoID) => get(`like/exist?userID=${userID}&videoID=${videoID}`),
  //是否评价
  isRanked: (userID,videoID) => get(`rank/exist?userID=${userID}&videoID=${videoID}`),
  //收藏
  setCollect: (params) => post(`collect/add`, params),
  //取消收藏
  deleteCollect: (userID,videoID) => get(`collect/delete?userID=${userID}&videoID=${videoID}`),
  //收藏数
  getCollect:(videoID)=>get(`collect/count?videoID=${videoID}`),
  //收藏点赞
  existCollect: (userID,videoID) => get(`collect/exist?userID=${userID}&videoID=${videoID}`),
  // 返回的指定用户ID的收藏列表
  getCollectOfUser: (userID) => get(`collect/detail?userID=${userID}`),


  // =======================> 评分 API
  // 提交评分
  setRank: (params) => post(`rank/add`, params),
  // 获取指定视频的评分
  getRankOfVideoId: (videoID) => get(`rank?videoID=${videoID}`),
  // 获取个人的评分
  getPersonRank: (userID,videoID) => get(`rank/person?userID=${userID}&videoID=${videoID}`),

  // =======================> 评论 API
  // 添加评论
  setComment: (params) => post(`comment/add`, params),
  // 点赞
 // setLike: (params) => post(`comment/like`, params),
  // 返回所有评论
  getAllComment: (id) => get(`comment/video/detail?videoID=${id}`),

  // =======================> 歌曲 API
  // 返回指定歌曲ID的歌曲
  getSongOfId: (id) => get(`song/detail?id=${id}`),
  //返回指定视频ID的视频
  getVideoOfId: (id) => get(`video/detail?id=${id}`),
  // 返回指定歌手ID的歌曲
  getSongOfSingerId: (id) => get(`song/singer/detail?singerId=${id}`),
  // 返回指定歌手名的歌曲
  getSongOfSingerName: (keywords) => get(`song/singerName/detail?name=${keywords}`),
  // 下载音乐
  downloadMusic: (url) => get(url, {}, 'blob'),
  //获取全部视频
  getAllVideo:()=> get('video'),
  // 返回指定Type的视频
  getVideoOfType: (keywords) => get(`video/type/detail?type=${keywords}`),
  // 返回指定name的视频
  getVideoOfName: (keywords) => get(`video/name/detail?name=${keywords}`),
  updateVideoLikeCount: (id,num) => get(`video/like/detail?id=${id}&num=${num}`),
  searchVideo:(keywords) => get(`video/search?keywords=${keywords}`),
  //返回chatGPT回答
  getBotReply:(keywords) => get(`/api/reply?question=${keywords}`),
  //返回chatGPT回答
  getBotReply2:(keywords) => get(`/api/reply2?question=${keywords}`),

}

export { HttpManager }
