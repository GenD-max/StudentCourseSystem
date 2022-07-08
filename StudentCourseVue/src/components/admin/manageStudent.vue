<template>
  <div id="manageStudent">
    <h1>管理学生</h1>
    <div class="container">
      <div class="left">
        <div class="card student" v-for="(item, index) in studentData" :key="item.id">
          <div class="info">
            <div class="name">学号：{{ item.sid }}</div>
            <div class="name">学院：{{ item.sinstitution }}</div>
            <div class="account">身份证号：{{ item.sidcard }}</div>
            <div class="account">年龄：{{ item.sage }}</div>
            <div class="account">年级：{{ item.sgrade }}级</div>
            <div class="account">专业：{{ item.sprofession }}</div>
            <div class="account">班级：{{ item.sclass }}</div>
            <el-button type="danger" size="small" style="background: #7266ba; border-color: #7266ba"
              @click="deleteUser(item.sid, index)">删除</el-button>
          </div>
          <div class="avant"
            :style="[item.sphotourl != null ? { 'background': 'url(' + require('../../assets/photo/' + item.sphotourl) + ') center center /90% no-repeat' } : '']">
            <div class="imgName">
              姓名：{{ item.sname }}
            </div>
          </div>
        </div>
        <el-button type="primary" v-show="this.studentData.length < this.total" style="float: right" @click="getMore">
          加载更多</el-button>
      </div>
      <div class="right">
        <ve-pie :data="chartData"></ve-pie>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      studentData: [],
      chartData: {
        columns: ["用户", "数量"],
        rows: [{ 用户: "已展示用户", 数量: 0 }, { 用户: "未展示用户", 数量: 0 }]
      },
      total: 0,
      page: 1
    };
  },
  methods: {
    getStudentData(callback) {
      this.axios
        .get("student/getPageStudent/" + this.page + "/3")
        .then(res => {
          if (res.data.code == 200) {
            this.total = this.$store.state.studentNum;
            // console.log(this.$store.state.studentNum)
            //...相当于把state的内容和data合并
            this.studentData = [
              ...this.studentData,
              ...res.data.data
            ];
            // console.log(this.studentData)
            callback();
          }
        })
        .catch(err => {
          console.log(err);
          // this.$message("服务器无法连接");
        });
    },
    getMore() {
      this.page++;
      this.getStudentData(() => {
        this.chartData.rows[0].数量 = this.studentData.length;
        this.chartData.rows[1].数量 = this.total - this.studentData.length;
      });
    },
    deleteUser(id, index) {
      this.$confirm("您确定要删除该学生吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.axios
            .post("student/deleteStudent?Sid=" + id)
            .then(res => {
              if (res.data.code == 200) {
                this.total--;
                this.studentData.splice(index, 1);
                this.chartData.rows[0].数量 = this.studentData.length;
                this.chartData.rows[1].数量 = this.total - this.studentData.length;
                this.$message({
                  showClose: true,
                  message: "删除成功！",
                  type: "success"
                });
              }
            })
            .catch(() => {
              // console.log(err);
              this.$message("服务器无法连接");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "操作已取消"
          });
        });
    }
  },
  mounted() {
    this.getStudentData(() => {
      this.chartData.rows[0].数量 = this.studentData.length;
      this.chartData.rows[1].数量 = this.total - this.studentData.length;
    });
    this.axios
      .get("getNumInfo")
      .then(res => {
        if (res.data.code == 200) {
          //将数量放入vuex
          var state = JSON.parse(sessionStorage.getItem("state"));
          //...相当于args 可以将数组或者JSON的元素合并为一个
          state = {
            ...state,
            ...res.data.data
          }
          // console.log(state)
          sessionStorage.setItem("state", JSON.stringify(state))
        }
      })
      .catch(err => {
        console.log(err);
        this.$message("服务器无法连接");
      });
  }
};
</script>

<style lang='scss' scoped>
.container {
  display: flex;

  .left {
    width: 70%;

    .student {
      display: flex;
      justify-content: space-between;
      padding: 20px 40px;

      .avant {
        // background: url(../../assets/student1.png);
        // margin-top: 125px;
        background-color: #c4c8c9a2;
        width: 170px;
        height: 170px;
        border-radius: 100px;
        line-height: 100px;
        text-align: center;
        font-weight: bold;
        color: rgba(0, 0, 0, 0.5);
        font-size: 1.4rem;

        .imgName {
          margin-top: 145px;
        }
      }

      .info {
        .name {
          margin-right: 40px;
          float: left;
          color: #99dadc;
          font-size: 1.2rem;
          margin-bottom: 10px;
          font-weight: bold;
        }

        .account {

          color: #969799;
          margin-bottom: 10px;
        }
      }
    }
  }

  .right {
    width: 30%;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    height: 350px;
    margin-left: 20px;
  }
}
</style>