<template>
  <div id="Logup">
    <el-col :span="8" :offset="8">
      <el-form>
        <el-form-item
          ><el-input type="text" placeholder="Email" v-model="account"
        /></el-form-item>
        <!--<el-button type="primary" @click="sendVerification">发送验证码</el-button>-->
        <!--<input type="text" placeholder="verification" v-model="veri" :class="'log-input' + (veri === '' ? ' log-input-empty' : '')"/>-->
        <el-form-item>
          <el-input type="text" placeholder="Username" v-model="username" />
        </el-form-item>
        <el-form-item>
          <el-input type="password" placeholder="Password" v-model="password"
        /></el-form-item>

        <el-button @click="logup" type="primary">Logup</el-button>
      </el-form>
      <Loading v-if="isLoging" marginTop="-30%"></Loading>
    </el-col>
  </div>
</template>

<script>
import Loading from "../components/Loading.vue";
export default {
  name: "Logup",
  data() {
    return {
      isLoging: false,
      account: "",
      password: "",
      username: ""
    };
  },
  components: {
    Loading
  },
  methods: {
    // 登录逻辑
    logup() {
      let _self = this;
      if (this.account !== "" && this.password !== "" && this.username !== "") {
        _self.axios
          .put(
            "/user/logup", _self.qs.stringify({
              account: _self.account,
              password: _self.password,
              username: _self.username
            }))
          .then(function(res) {
            if (res.data["state"] === 1) {
              _self.$alert("点击确认进入主页", "注册成功", {
                confirmButtonText: "确定",
                type:"success",
                callback: () => {
                  window.localStorage.setItem(
                    "user",
                    JSON.stringify(res.data.data)
                  );
                  _self.$router.push("/home");
                }
              });
            } else {
              _self.$alert(res.data.msg, "注册失败", {
                confirmButtonText: "确认",
                type: "error"
              });
            }
          })
          .catch(function() {
            _self.$alert("注册失败","网络错误",{confirmButtonText:"确定",type:"error"});
          });
      } else {
        _self.$alert("注册失败","账号和密码不能为空",{confirmButtonText:"确定",type:"error"});
      }
    }
  }
};
</script>

<style scoped>
#Logup {
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
