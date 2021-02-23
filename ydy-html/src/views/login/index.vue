<template>
  <div>
    <app-header v-if="header_show"></app-header>
    <div class="login-wrap" v-show="showLogin">
      <h3>登录</h3>
      <p v-show="showLoginMessage">{{LoginMessage}}</p>
      <input type="text" placeholder="请输入用户名" v-model="username">
      <input type="password" placeholder="请输入密码" v-model="password">
      <button @click="handleLogin">登录</button>
      <span>没有账号？马上注册</span>
    </div>
    <app-footer v-if="footer_show"></app-footer>
  </div>
</template>

<script>
  import Header from '../../components/pages/common/header'
  import Footer from '../../components/pages/common/footer'
  import axios from 'axios'
  export default {
    name: 'app-index',
    data () {
      return {
        header_show: true,
        footer_show: true,
        showLogin: true,
        showRegister: false,
        showLoginMessage: false,
        LoginMessage: '',
        username: 'admin',
        password: '123456',

        newUsername: '',
        newPassword: ''
      }
    },
    //组件引入
    components: {
      'app-header': Header,
      'app-footer': Footer
    },
    methods: {
      //登录方法
      handleLogin() {
        if (this.username === '' || this.password === '') {
          this.LoginMessage = "用户名或密码为空";
          this.showLoginMessage = true;
          return false;
        }
        console.log(this.username + '---' + this.password)
        axios.post('/sys/login',{username : this.username,password : this.password})
          .then(res => {
            console.log(res)
            if (res.status == 200) {
              this.$router.replace({path:'/main'})
            }
          });
      }
    },
  }
</script>
<style>
  body {
    background-image: url('../../assets/background.jpg');
    background-size: 100%;
    background-repeat: no-repeat;
  }
  .login-wrap {
    text-align: center;
  }
  input {
    display: block;
    width: 250px;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    margin-bottom: 10px;
    outline: none;
    border: 1px solid #888;
    padding: 10px;
    box-sizing: border-box;
  }
  p {
    color: white;
  }
  button {
    display: block;
    width: 250px;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    border: none;
    background-color: #41b883;
    color: #fff;
    font-size: 16px;
    margin-bottom: 5px;
  }
  span {
    cursor: pointer;
  }
  span:hover {
    color: #41b883;
  }

</style>
