<template>
  <div class="login-container">
    <h2>Login</h2>
    
    <!-- 输入表单 -->
    <form autocomplete="off"> <!-- @submit.prevent=""  -->
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username" required><br/><br/>
      
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required><br/><br/><br/>
      
      <div style="action-container">
      	<button type="submit" v-on:click="goLogin">Login</button>
      	<div class="action-space"></div>
      	<button type="go-register" v-on:click="goRegister">Register</button>
      </div>
    </form>
    
    <!-- 显示错误信息 -->
    <p v-if="errorMessage !== ''" style="color: red;">{{ errorMessage }}</p>
  </div>
</template>
 

 
<script>
	import qs from 'qs'
	import http from '@/utils/request'

	export default {
	  data() {
	    return {
	      username: 'bob101', // 存储用户名
	      password: '111111', // 存储密码
	      errorMessage: '' // 存储错误消息
	    };
	  },
	  
	  methods: {

	    async loginRequest() {
	    	try {
	    		const res = await http.post('/login', {
	    			username: this.username,
	    			password: this.password
	    		})
	    		.then(response => {
	    			console.log(response.data);
	    			// if (response.data) {
					   //      // 验证成功后进行相关操作（如重定向到主页）
					   //      console.log(response.data);
					   //      // 清空输入字段
					   //      this.clearInputs();
				    //     } else {
					   //      // 验证失败时设置错误提示信息
					   //      this.errorMessage = "Invalid username or password";
					   //  }
	    		})
	    		.catch(error => {

	    		});
	    	} catch(e) {
	    		console.error("LoginRequest Error: ", e);
	    	}
	    },
	    goLogin() {
	    	// loginRequest()
	    	console.log("点击了登录按钮");
	    	this.loginRequest();
	    },
	    goRegister() {
	    	console.log("点击了注册按钮");
	    	this.$router.replace('/register')
	    },
	    clearInputs() {
	      this.username = '';
	      this.password = '';
	      this.errorMessage = '';
	    }
	  }
	};
</script>
 
<style scoped>
.login-container {
  max-width: 300px;
  margin: 0 auto;
}
 
h2 {
  text-align: center;
}
 
form label {
  display: block;
}
 
form input[type='text'], form input[type='password'] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box; /* 解决宽度超出父容器问题 */
}

form div {
	display: flex; /* 设置容器为flex布局 */
	justify-content: space-between;
}

form div .action-space {
	width: 14px;
}
 
form button {
  background-color: #4CAF50; /* Green */
  color: white;
  width: 70%;
  /*height: 40px;*/
  padding: 12px;
  border: none;
  border-radius: 4px;
  /*cursor: pointer;*/
}

form button[type="go-register"] {
	width: 30%;
	background-color: #338FCC;
}
 
form button:hover {
  background-color: #45a049; /* Dark green */
}
 
form button[type="go-register"]:hover {
  background-color: #4D8AB3;
}
</style>