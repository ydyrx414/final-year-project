import Vue from 'vue'
import VueRouter from 'vue-router'
import AdministratorLayout from '@/layouts/admin/index.vue'
import AdminHome from "../views/admin/Home.vue"
Vue.use(VueRouter)

const routes = [
  {
    path: '/admin',
    component: AdministratorLayout,
    children: [
      {
        path: 'home',
        component: AdminHome,
        meta: {
          title: '主页'
        }
      },
      {
        path: 'profile',
        component: () => import(/* webpackChunkName: "about" */ '../components/Profile.vue')
      },
      {
        path: 'user',
        component: () => import(/* webpackChunkName: "about" */ '../views/admin/UserList.vue'),
        meta: {
          title: '系统用户管理'
        }
      },
      {
        path: 'application',
        component: () => import(/* webpackChunkName: "about" */ '../components/Application.vue'),
        meta: {
          title: '申请管理'
        }
      },
      {
        path: 'student',
        component: () => import(/* webpackChunkName: "about" */ '../views/admin/StudentList.vue'),
        meta: {
          title: '学生管理'
        }
      },
      {
        path: 'corporation',

        component: () => import(/* webpackChunkName: "about" */ '../views/admin/CorporationList.vue'),
        meta: {
          title: '公司管理'
        }
      },
      {
        path: 'jobs',
        component: () => import(/* webpackChunkName: "about" */ '../views/admin/Jobs.vue'),
        meta: {
          title: '公司职位管理'
        }
      },

    ]
  },

  {
    path: '/login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/register',
    component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  }, 
  {
    path: '/article',
    component: () => import(/* webpackChunkName: "about" */ '../views/app/Article.vue')
  },

  {
    path: '/about',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/',
    redirect:"/home",
    component: () => import(/* webpackChunkName: "about" */ '../layouts/AppLayout'),
    children: [
      {
        path: 'home',
        component: () => import(/* webpackChunkName: "about" */ '../views/app/Home.vue')
      },
      {
        path: 'profile',
        component: () => import(/* webpackChunkName: "about" */ '../components/Profile.vue')
      },
      {
        path: 'jobs',
        component: () => import(/* webpackChunkName: "about" */ '../views/app/Jobs.vue')
      },
      {
        path: 'received',
        component: () => import(/* webpackChunkName: "about" */ '../views/app/Received.vue')
      },
      {
        path: 'video',
        component: () => import(/* webpackChunkName: "about" */ '../views/app/Video.vue')
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
