<template>
  <div id="ModifyPassword">
    <el-col :span="8" :offset="8">
      <el-form>
        <el-form-item
          ><el-input
            v-model="veri"
            placeholder="please input the verification you have received"
          ></el-input><span><el-button @click="send" style="border:none;">发送验证码</el-button></span>
        </el-form-item>
        <el-form-item
          ><el-input
            v-model="password"
            placeholder="please input new password"
          ></el-input
        ></el-form-item>
        <el-button type="primary" @click="save">保存</el-button>
      </el-form>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "ModofyPassword",
  data() {
    return {
      veri: "",
      password: "",
      verification: "",
      account: "123@example.com"
    };
  },
  mounted() {
    this.account = this.$route.params.account;
    this.verification = "";
  },
  methods: {
    save() {
      if (this.verification.replace(/(^\s*)|(\s*$)/g, "") !== this.veri.replace(/(^\s*)|(\s*$)/g, "")) {
        this.$message("验证码错误");
      } else {
        let _self=this;
        _self.axios.patch("/user/update/password",_self.qs.stringify({password:_self.password})).then(function (res) {
          if (res.data["state"]===1){
            _self.$message({type:"success",message:"修改成功"});
          }
          else{
            _self.$message({type:"error",message:res.data["msg"]})
          }
        }).catch(function () {
          _self.$message({type:"error",message:"网络错误"})
        })
      }
    },
    send(){
      let _self=this;
      _self.axios.get("/user/verification").then(function (res) {
        if (res.data.state===1){
          _self.verification=res.data["data"];
        }
        else{
          _self.$message({type:"warning",message:res.data["msg"]})
        }
      }).catch(function () {
        _self.$message({type:"error",message:"网络错误"})
      })
    }
  }
};
</script>

<style scoped>
#ModifyPassword {
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
