<template>
  <div id="Home">
    <el-container>
      <el-header style="background-color: darkolivegreen">
        <el-row>
          <el-col :span="6" style="text-align: left;font-family: 'Droid Serif';font-size: 28px"><b>APM</b></el-col>
          <el-col :span="6" :offset="18" style="text-align: right">
            <el-dropdown>
              <span class="el-dropdown-link">
                <i class="material-icons"><span>person</span></i
                >{{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item
                  ><span @click="jumpToInfo">个人信息</span></el-dropdown-item
                >
                <el-dropdown-item
                  ><span @click="Modify">修改密码</span></el-dropdown-item
                >
                <el-dropdown-item
                  ><span @click="Signout">退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-container
          ><el-aside>
            <el-menu
              :default-active="operation"
              class="el-menu-vertical-demo"
              text-color="#fff"
              background-color="#545c64"
              active-text-color="#ffd04b"
            >
              <el-submenu index="1" @click="newTest">
                <template slot="title"><i class="el-icon-circle-plus"></i><span>新建测试</span></template>
                <el-menu-item-group>
                  <el-menu-item @click.native="newTest(1)" index="1-1">默认场景</el-menu-item>
                  <el-menu-item @click.native="newTest(0)" index="1-2">自定义场景</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title"
                  ><i class="el-icon-tickets"></i>
                  <span>历史测试</span></template
                >
                <el-menu-item-group>
                  <el-menu-item
                    v-for="(item, index) in history"
                    :key="index"
                    :index="'2-' + (index + 1)" @click.native="result(item)"
                    ><span >{{item["task_name"]}}</span></el-menu-item
                  >
                </el-menu-item-group>
              </el-submenu>
            </el-menu>
          </el-aside>
          <el-main>
            <transition name="el-fade-in"
              ><keep-alive><router-view :key="$route.fullPath"/></keep-alive
            ></transition> </el-main
        ></el-container>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      account: "",
      username: "",
      operation: "1",
      history: [{"task_name":"","report_id":""}]
    };
  },
  mounted(){
    let user=JSON.parse(window.localStorage.getItem("user"));
    this.account=user["account"];
    this.username=user["username"];
    let _self=this;
    _self.axios.get("/history").then(function (res) {
      _self.history=res.data;
    }).catch(function () {
      _self.$alert("网络错误","查询失败",{confirmButtonText:"确定",type:"error"});
    })
  },
  methods: {
    newTest(select) {
      if (select===1) {
        this.$router.push({path: "defaultScene"});
      }
      else{
        this.$router.push({path:"otherScene"})
      }
    },
    jumpToInfo() {
      this.$router.push("/user");
    },
    result(item) {
      debugger;
      this.$router.push({
        path: "result",
        query: { recordId: item["report_id"].toString(),start:"1" }
      });
    },
    Modify() {
      this.$router.push({
        name: "ModifyPassword",
        params: { account: this.account.toString() }
      });
    },
    Signout() {
      window.localStorage.removeItem("user");
      this.axios.get("/user/out");
      this.$router.push({ path: "/" });
    }
  }
};
</script>

<style scoped>
#Home {
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
.el-aside {
  background-color: #545c64;
  text-align: left;
}
  .el-header{
    margin: 0;
    padding: 0;
  }
</style>
