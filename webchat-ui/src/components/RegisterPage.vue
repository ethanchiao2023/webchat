<template>
  <div class="register-container">
    <h2>Register</h2>
    
    <!-- 输入表单 -->
    <form autocomplete="off">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username" required><br/><br/>
      
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password" required><br/><br/><br/>
      
      <div style="action-container">
      	<button type="submit" v-on:click="onRegister">Register</button>
      	<div class="action-space"></div>
      	<button type="go-login" v-on:click="onLogin">Login</button>
      </div>
    </form>
    
    <!-- 显示错误信息 -->
    <p v-if="errorMessage !== ''" style="color: red;">{{ errorMessage }}</p>
  </div>
</template>
 

 
<script>
	// import request from '../utils/request';
	export default {
	  data() {
	    return {
	      username: 'bob101', // 存储用户名
	      password: 'Bob123', // 存储密码
	      errorMessage: '' // 存储错误消息
	    };
	  },
	  
	  methods: {

	    async registerRequest() {
	    	try {
	    		const res = await request.post('', {
	    			username: this.username,
				        password: this.password
	    		})
	    		.then(response => {
	    			if (this.username === 'bob101' && this.password === 'Bob123') {
					        // 验证成功后进行相关操作（如重定向到主页）
					        
					        // 清空输入字段
					        this.clearInputs();
				        } else {
					        // 验证失败时设置错误提示信息
					        this.errorMessage = "Invalid username or password";
					    }
	    		})
	    		.catch(error => {

	    		});
	    	} catch(e) {
	    		console.error("LoginRequest Error: ", e);
	    	}
	    },

	    onRegister() {
			registerRequest();
	    },

	    onLogin() {
	    	this.$router.replace("/login");
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
.register-container {
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

form button[type="go-login"] {
	width: 30%;
	background-color: #338FCC;
}
 
form button:hover {
  background-color: #45a049; /* Dark green */
}
</style>