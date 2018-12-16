<template>
  <div id="Download">
    <span @click="download"><a href="#">Download result CSV</a></span> <br />
  </div>
</template>

<script>
export default {
  name: "Download",
  props:["recordId"],
  methods:{
    download(){
      this.axios.get("/download?reportId="+this.recordId,{responseType:'blob'}).then(function (res) {
        let url = window.URL.createObjectURL(new Blob([res.data]));
        let link = document.createElement('a');
        link.style.display = 'none';
        link.href = url;
        link.setAttribute('download', 'result.csv');
        document.body.appendChild(link);
        link.click();
      }).catch(function () {
        this.$message("网络错误")
      })
    }
  }
};
</script>

<style scoped>
#Download {
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
a,
a:active {
  color: #2c3e50;
  text-decoration-line: none;
}
</style>
