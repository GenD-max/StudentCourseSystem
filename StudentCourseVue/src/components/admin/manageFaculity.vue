<template>
  <div id="managefaculity">
    <h1>管理院系</h1>
    <div class="add_box">
      <div class="title">添加院系</div>
      <el-input
        style="margin-top: 20px;"
        placeholder="输入院系名称"
        v-model="faculityName"
        class="input-with-select"
      >
        <el-button
          slot="append"
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="submit"
        >添加</el-button>
      </el-input>
    </div>
    <el-row class="faculity_box" :gutter="20">
      <el-col :xl="8" :md="12" :xs="24" v-for="item in faculityData" :key="item.id">
        <div class="list light_shadow">{{item.fname}}</div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      faculityData: [],
      faculityName: ""
    };
  },
  methods: {
    getfaculity() {
      this.axios
        .get("queryFaculity")
        .then(res => {
          if (res.data.code == 200) {
            this.faculityData = res.data.data;
            // console.log(this.faculityData)
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接");
        });
    },
    submit() {
      if (this.faculityName != "") {
        this.$confirm(`您确定要添加“${this.faculityName}”到系统吗？`, "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            this.addfaculity();
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "操作已取消"
            });
          });
      } else {
        this.$message.error("请填写院系名称");
      }
    },
    addfaculity() {
      this.axios
        .post("insertFaculity?faculityName=" + this.faculityName)
        .then(res => {
          if (res.data.code == 200) {
            this.$message.success("添加成功");
            this.faculityName = "";
            this.getfaculity();
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接，院系添加失败");
        });
    }
  },
  mounted() {
    this.getfaculity();
  }
};
</script>

<style lang='scss' scoped>
.add_box {
  background-color: #fff;
  padding: 40px 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  overflow: hidden;
  .title {
    margin: -40px -20px;
    margin-bottom: 0;
    color: white;
    background-color: #409eff;
    padding: 20px;
  }
}
.faculity_box {
  .list {
    background-color: #409eff;
    margin-top: 20px;
    padding: 20px;
    border-color: white;
    border-radius: 10px;
    color: white;
    font-size: 1.2rem;
  }
}
</style>