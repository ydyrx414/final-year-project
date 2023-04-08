import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import(/* webpackChunkName: "about" */ '../views/Manage.vue'),
    redirect: "/Selection",
    children: [

      {
        path: 'home',
        name: 'Home',
        component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue'),
        meta: {
          title: '主页'
        }
      },
      {
        path: 'user',
        name: 'User',
        component: () => import(/* webpackChunkName: "about" */ '../views/User.vue'),
        meta: {
          title: '系统用户管理'
        }
      },
      {
        path: 'student',
        name: 'Student',
        component: () => import(/* webpackChunkName: "about" */ '../views/Student.vue'),
        meta: {
          title: '学生管理'
        }
      },
      {
        path: 'person',
        name: 'Person',
        component: () => import(/* webpackChunkName: "about" */ '../views/Person.vue')
      },
      {
        path: 'corporation',
        name: 'Corporation',
        component: () => import(/* webpackChunkName: "about" */ '../views/Corporation.vue'),
        meta: {
          title: '公司管理'
        }
      },
      {
        path: 'jobs',
        name: 'Jobs',
        component: () => import(/* webpackChunkName: "about" */ '../views/Jobs.vue'),
        meta: {
          title: '公司职位管理'
        }
      },

    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/Slogin',
    name: 'SLogin',
    component: () => import(/* webpackChunkName: "about" */ '../views/SLogin.vue')
  },
  {
    path: '/Clogin',
    name: 'CLogin',
    component: () => import(/* webpackChunkName: "about" */ '../views/CLogin.vue')
  },
 
  {
    path: '/Selection',
    name: 'Selection',
    component: () => import(/* webpackChunkName: "about" */ '../views/Selection.vue')
  },

  {
    path: '/register',
    name: 'Register',
    component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  },
  {
    path: '/Cregister',
    name: 'CRegister',
    component: () => import(/* webpackChunkName: "about" */ '../views/CRegister.vue')
  },
  {
    path: '/Sregister',
    name: 'SRegister',
    component: () => import(/* webpackChunkName: "about" */ '../views/SRegister.vue')
  },
  
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import(/* webpackChunkName: "about" */ '../views/front/Front'),
    children: [
      {
        path: 'home',
        name: 'FrontHome',
        component: () => import(/* webpackChunkName: "about" */ '../views/front/Home.vue')
      },
      {
        path: 'list',
        name: 'List',
        component: () => import(/* webpackChunkName: "about" */ '../views/front/List.vue')
      },
      {
        path: 'person',
        name: 'Person',
        component: () => import(/* webpackChunkName: "about" */ '../views/front/Person.vue')
      }
    ]
  },
  {
    path: '/front',
    name: 'CFront',
    component: () => import(/* webpackChunkName: "about" */ '../views/front/CFront'),
    children: [
      {
        path: 'Chome',
        name: 'CFrontHome',
        component: () => import(/* webpackChunkName: "about" */ '../views/front/CHome.vue')
      },
      {
        path: 'Clist',
        name: 'CList',
        component: () => import(/* webpackChunkName: "about" */ '../views/front/CList.vue')
      },
      {
        path: 'Cperson',
        name: 'CPerson',
        component: () => import(/* webpackChunkName: "about" */ '../views/front/CPerson.vue')
      }
    ]
  },


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
