<template>
  <div id="Result">
    <el-tabs>
      <el-tab-pane label="Charts"
        ><Charts :record-id="recordId" :start="start"></Charts
      ></el-tab-pane>
      <el-tab-pane label="Download Data"><Download :record-id="recordId"></Download></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Charts from "./Charts";
import Download from "./Download";
export default {
  name: "Result",
  components: { Download,Charts},
  data() {
    return {
      recordId: 0,
      start:0,
    };
  },
  mounted() {
    this.start = this.$route.query.start;
    this.recordId = this.$route.query.recordId;
  }
};
</script>

<style scoped>
#Result {
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
