import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HelloWorld
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/rest',
    name: 'rest',
    component: () => import('../components/RestAPI.vue')
  }
]

const router = new VueRouter({
  mode: 'history', // HTML5 History API (Warning, underlying server will require extra configuration)
  // mode: 'hash', // Compatibility mode for brtowsers that do not support History API (works withouir config when served with Tomcat)
  base: process.env.BASE_URL,
  routes
})

export default router
