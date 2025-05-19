import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../pages/HomePage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../pages/AboutPage.vue'),
    },
    {
      path: '/user/login',
      name: 'userLogin',
      component: () => import('../pages/UserLoginPage.vue'),
    },
    {
      path: '/user/register',
      name: 'userRegister',
      component: () => import('../pages/UserRegisterPage.vue'),
    },
    {
      path: '/user/me',
      name: 'userMe',
      component: () => import('../pages/UserProfile.vue'),
    },

  ],
})

export default router
