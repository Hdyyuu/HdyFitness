import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
//解决编程式路由往同一地址跳转时会报错的情况
const originalPush = Router.prototype.push;
const originalReplace = Router.prototype.replace;
//push
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch(err => err);
};
//replace
Router.prototype.replace = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalReplace.call(this, location, onResolve, onReject);
  return originalReplace.call(this, location).catch(err => err);
};

const constantRoutes = [
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/404',
    component: () => import('@/pages/404.vue')
  },
  {
    path: '/',
    // name: 'main',
    component: () => import('@/pages/Main'),
    children: [
      {
        path: '/login-in',
        name: 'login-in',
        component: () => import('@/pages/LoginIn')
      },
      {
        path: '/sign-up',
        name: 'sign-up',
        component: () => import('@/pages/SignUp')
      },
      {
        path: '/',
        name: 'home',
        component: () => import('@/pages/Home')
      },
      {
        path: '/equipment-list',
        name: 'equipment-list',
        component: () => import('@/pages/EquipmentList')
      },
      {
        path: '/my-video',
        name: 'my-video',
        component: () => import('@/pages/MyVideo')
      },

      {
        path: '/search',
        name: 'search',
        component: () => import('@/pages/Search')
      },
      {
        path: '/setting',
        name: 'setting',
        component: () => import('@/pages/Setting')
      },
      {
        path: '/video-list-album/:id',
        name: 'video-list',
        component: () => import('@/pages/VideoListAlbum')
      },
      {
        path: '/plan',
        name: 'plan',
        component: () => import('@/pages/Plan')
      },
      {
        path: '/equip-album',
        name: 'equip-album',
        component: () => import('@/pages/EquipAlbum')
      },{
        path: '/pro-album',
        name: 'pro-album',
        component: () => import('@/pages/Pro')
      },
      {
        path: '/tool-album/:id',
        name: 'my-tool',
        component: () => import('@/pages/MyTool')
      },
      {
        path: '/tool-list/',
        name: 'tool-list',
        component: () => import('@/pages/ToolList')
      },
      {
        path: '/chatGPT/',
        name: 'chatGPT',
        component: () => import('@/pages/ChatGPT')
      }
    ]
  }
]

export default new Router({
  mode: 'history', // 去掉url中的#
  base: '/fitness/',
  scrollBehavior: (o, from, savedPosition) => ({ x: 0, y: 0 }),
  routes: constantRoutes
})


