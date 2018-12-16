<template>
  <div id="otherScene">
    <el-form
      v-model="test"
      ref="test"
      label-position="left"
      label-width="80px"
      style="text-align: left;"
    >
      <el-form-item label="name">
        <el-input v-model="test.taskName" placeholder="please name the task"></el-input>
      </el-form-item>
      <el-form-item label="host">
        <el-input placeholder="required host" v-model="test.host"></el-input>
      </el-form-item>
      <el-form-item label="hatch rate">
        <el-input-number  v-model="test.hatchRate"></el-input-number>
      </el-form-item>
      <el-form-item label="run time(min)">
        <el-input-number v-model="test.runTime"></el-input-number>
      </el-form-item>
      <el-form-item label="users">
        <el-input-number
          v-model="test.users"
          placeholder="请输入用户数"
        ></el-input-number>
      </el-form-item>
      <hr />
      <el-form
        v-for="(item, index) in test.items"
        :key="index"
        :model="item"
        :ref="item"
        label-position="left"
        label-width="80px"
      >
        <el-form-item :label="'method' + (index + 1)">
          <el-select v-model="item.method" placeholder="请选择方式">
            <el-option
              v-for="(way, index) in ways"
              :key="index"
              :label="way"
              :value="way"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label="'api' + (index + 1)">
          <el-input placeholder="required" v-model="item.api"></el-input>
        </el-form-item>
        <el-form-item :label="'weight' + (index + 1)">
          <el-input-number
            v-model="item.weight"
            placeholder="请输入权重"
          ></el-input-number>
        </el-form-item>
        <el-form
          v-for="(para, index) in item.params"
          :key="index"
          :model="para"
          :ref="para"
          label-position="left"
          label-width="80px"
          :inline="true"
          class="demo-form-inline"
        >
          <el-form-item :label="'参数名称' + (index + 1)">
            <el-input
              v-model="para.name"
              placeholder="请输入参数名称"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'参数值' + (index + 1)">
            <el-input
              v-model="para.value"
              placeholder="请输入参数值"
            ></el-input>
          </el-form-item>
          <el-button
            v-if="index === item.params.length - 1"
            type="primary"
            @click="addPara(item.params)"
            round
          >新增参数</el-button
          >
        </el-form>
        <hr />
      </el-form>
      <el-button type="primary" @click="addApi(test.items)" round
      >新增接口</el-button
      >
    </el-form>
    <el-button type="primary" @click="start" round>开始测试</el-button>
  </div>
</template>

<script>
  export default {
    name: "otherScene",
    data() {
      return {
        test: {
          host: "",
          users: 0,
          items: [{ method: "GET", api: "", params: [{ name: "", value: "" }] }],
          recordId: 1,
          taskName:"",
          hatchRate:0,
          runTime:1
        },
        ways: ["GET", "POST", "PUT", "PATCH", "DELETE"],
        account:""
      };
    },
    mounted(){
      this.account=JSON.parse(window.localStorage.getItem("user"))["account"];
    },
    methods: {
      addApi(test) {
        test.push({ method: "GET", api: "", params: [{ name: "", value: "" }] });
      },
      addPara(params) {
        params.push({ name: "", value: "" });
      },
      start() {
        // 开始测试,向后端请求
        let _self=this;
        _self.axios.post('/workload',_self.qs.stringify({clients:_self.test.users,hatchRate:_self.test.hatchRate,host:_self.test.host,runTime:_self.test.runTime,scenarioId:0,taskName:_self.test.taskName})).then(function (res) {
          _self.test.recordId=res.data;
          _self.$router.push({
            path: "result",
            query: { start:(_self.test.runTime*60).toString(),recordId: _self.test.recordId}
          });
        }).catch(function () {
          _self.$message("网络错误");
        })

      }
    }
  };
</script>

<style scoped></style>

