<template>
  <div id="Login">
    <el-col :span="8" :offset="8">
    <el-form>
      <el-form-item>
        <el-input placeholder="Email" v-model="account"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" placeholder="Password" v-model="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="login" type="primary">Login</el-button>
      </el-form-item>
      <el-form-item>
        <span>If you have no account,please<router-link to="/logup">register</router-link></span>
      </el-form-item>
      <!--<el-form-item>-->
        <!--<span>If you forget your password,please click <router-link to="/modifyPassword">here</router-link></span>-->
      <!--</el-form-item>-->
    </el-form>
<Loading v-if="isLoging" marginTop="-30%"></Loading>
    </el-col>
    </div>

</template>

<script>
import Loading from "../components/Loading.vue";
export default {
  name: "Login",
  data() {
    return {
      isLoging: false,
      account: "",
      password: ""
    };
  },
  components: {
    Loading
  },
  methods: {
    // 登录逻辑
    login() {
      if (this.account !== "" && this.password !== "") {
         let _self=this;
        _self.axios.post('/user/login',_self.qs.stringify({account:_self.account,password:_self.password})).then(function (res) {
          if (res.data["state"]===1){
            window.localStorage.setItem("user",JSON.stringify(res.data.data));
            _self.$router.push("/home");
          }
          else{
            _self.$alert(res.data.msg,"登录失败",{confirmButtonText:"确认",type:"error"})
          }
        }).catch(function () {
          _self.$alert("网络错误","登录失败",{confirmButtonText:"确认",type:"error"})
        });
      }
    }
  }
};
</script>

<style scoped>
  #Login {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
    position: relative;
    height: 100%;
    width: 100%;
  }
</style>
