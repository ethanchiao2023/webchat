import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import LoginPage from '@/components/LoginPage'
import RegisterPage from '@/components/RegisterPage'
import HomePage from '@/components/HomePage'


Vue.use(Router)


const router = new Router({
  routes: [
    {
      path: '/',
      name: 'hello',
      component: HelloWorld
    },
    {
    	path: '/login',
    	name: 'login',
    	component: LoginPage
    },
    {
    	path: '/register',
    	name: 'register',
    	component: RegisterPage
    }
  ]
});


router.beforeEach((to, from, next) => {
	// console.log(store.state.token)
	if (to.name === 'hello') {
		const isLoggedIn = checkIfUserIsLogged(); // 这里是判断用户是否已登录的逻辑，返回 true 或 false
		console.log(isLoggedIn)
		if (!isLoggedIn) {
			next('/login'); // 若没有登录则重定向到登录页面
		} else {
			next(); // 已登录则正常跳转到目标页面
		}
	} else {
		next(); // 其他情况直接放行
	}
});


const checkIfUserIsLogged = () => {
	return true;
}

export default router; 
