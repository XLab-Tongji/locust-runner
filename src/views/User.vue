<template>
  <div id="User">
    <el-col :span="12" :offset="6"
      ><el-form v-model="user" label-position="left" label-width="100px">
      <el-form-item label="account">
        <el-input v-model="user['account']" disabled></el-input>
      </el-form-item>
        <el-form-item label="username">
          <el-input v-model="user['username']"></el-input>
        </el-form-item>
        <el-form-item label="company">
          <el-input v-model="user['company']"></el-input>
        </el-form-item>
      <el-form-item label="company_size">
        <el-input v-model="user['company_size']"></el-input>
      </el-form-item>
      <el-form-item label="job">
        <el-input v-model="user['job']"></el-input>
      </el-form-item>
        <el-button type="primary" @click="save">保存</el-button>
      </el-form></el-col
    >
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      user: {}
    };
  },
  mounted(){
    this.user=JSON.parse(window.localStorage.getItem("user"));
  },
  methods: {
    save() {
      let _self=this;
      _self.axios.patch("/user/update",_self.qs.stringify({username:_self.user["username"],job:_self.user["job"],company:_self.user["company"],company_size:_self.user["company_size"]})).then(function (res) {
        if (res.data["state"]===1){
          window.localStorage.setItem("user",JSON.stringify(res.data.data));
          _self.$alert("","修改成功",{confirmButtonText:"确定"});
        }
        else{
          -_self.$alert(res.data["msg"],"修改失败",{confirmButtonText:"确定"});
        }
      })
    }
  }
};
</script>

<style scoped>
#User {
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
