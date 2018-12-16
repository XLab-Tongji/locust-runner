<template>
  <div id="defaultScene">
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
          v-model="test.clients"
          placeholder="请输入用户数"
        ></el-input-number>
      </el-form-item>
      <el-form-item label="scene">
        <el-select v-model="test.scenarioId">
          <el-option v-for="(item,index) in scene" :key="index" :label="item.name" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item><el-button type="primary" @click="start" round>开始测试</el-button></el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "defaultScene",
    data() {
      return {
        test: {
          host: "",
          clients: 0,
          recordId: 1,
          taskName:"",
          hatchRate:0,
          runTime:1,
          scenarioId:1
        },
        scene:[{name:"主页",value:1},{name:"注册",value:2},{name:"浏览",value:3},{name:"添加带购物车",value:4},{name:"支付",value:5}]
      };
    },

    methods: {
      start() {
        // 开始测试,向后端请求
        let _self=this;
        _self.axios.post('/workload',_self.qs.stringify(_self.test)).then(function (res) {
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
