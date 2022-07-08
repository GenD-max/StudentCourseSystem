<template>
  <div id="courseGrade">
    <h1>
      <router-link style="cursor: pointer" tag="span" to="/teacher/manageGrade">课程信息</router-link>
      > {{ courseInfo.cname }}
      <span class="tag proptype">{{ courseInfo.ctype }}</span>
      <span class="tag status">未结课</span>
    </h1>
    <div id="main">
      <div class="left">
        <el-row>
          <el-col class="card" style="margin-top: 10px; margin-left: 10px;" v-if="SCInfo.length == 0">尚未有学生选该课程</el-col>
          <el-col class="student_card" :md="12" :xl="8" v-for="item in SCInfo" :key="item.id">
            <div class="inner">
              <div class="left">
                <div class="name">{{ item.student.sname }}</div>
                <div class="grade">
                  成绩：{{ item.ggrade }}
                  <span v-if="item.ggrade == null" style="color: #F56C6C">暂无</span>
                </div>
              </div>
              <div class="righ">
                <el-button type="primary" @click="setGrade(item.cid,item.sid,item.tid)">录入成绩</el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="right">
        <ve-pie :data="chartData"></ve-pie>
        <!-- <el-button type="danger" v-if="courseInfo.status == 1" @click="finishedCourse">课程结课</el-button> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form:{
        Sid: "",
        Tid: "",
        Cid: "",
        Ggrade: "",
        Gmodtime: ""
      },
      SCInfo: [],
      courseInfo: {},
      chartData: {
        columns: ["user", "number"],
        rows: [
          { user: "已录入成绩", number: 0 },
          { user: "未录入成绩", number: 0 }
        ]
      }
    };
  },
  methods: {
    setGrade(cid,sid,tid) {
      this.$prompt("请输入学生成绩", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^[0-9]*$/,
        inputErrorMessage: "成绩格式不正确"
      })
        .then(({value}) => {
          var obj = this.form;
          obj.Sid = sid;
          obj.Cid = cid;
          obj.Tid = tid;
          obj.Ggrade = value;
          obj.Gmodtime = new Date().toLocaleDateString();
          var params = this.$qs.stringify(obj);
          // console.log(params)
          this.axios
            .post("teacher/setGrade", params)
            .then(res => {
              if (res.data.code == 200) {
                this.$message({
                  type: "success",
                  message: "成绩录入成功！"
                });
                this.getSCInfo(this.chartDataInit);
              }
            })
            .catch(err => {
              console.log(err);
              this.$message("服务器无法连接");
            });
        })
        .catch((err) => {
          console.log(err);
          this.$message({
            type: "info",
            message: "取消输入"
          });
        });
    },
    getSCInfo(callback) {
      this.axios
        .get("teacher/queryGrade/" + this.$store.state.id + "/" + this.$route.params.cid)
        .then(res => {
          if (res.data.code == 200) {
            this.SCInfo = res.data.data;
            // console.log(this.SCInfo)
            callback();
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接，获取学生信息失败");
        });
    },
    chartDataInit() {
      let have_grade = 0;
      let no_grade = 0;
      for (let i in this.SCInfo) {
        if (this.SCInfo[i].ggrade == null) {
          no_grade++;
        } else {
          have_grade++;
        }
      }
      this.chartData.rows[0].number = have_grade;
      this.chartData.rows[1].number = no_grade;
    }, getCourseInfo() {
      this.axios
        .get("teacher/getOneCourse/" + this.$route.params.cid)
        .then(res => {
          if (res.data.code == 200) {
            this.courseInfo = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("接口异常，无法连接至服务器")
        })
    },
  },
  mounted() {
    this.getCourseInfo();
    this.getSCInfo(this.chartDataInit);
  }
};
</script>

<style lang='scss' scoped>
h1 {
  .tag {
    font-size: 16px;
    color: white;
    font-weight: 500;
    padding: 5px 10px;
    border-radius: 5px;
    position: relative;
    top: -2px;
  }

  .proptype {
    background-color: #409eff;
    margin-right: 10px;
  }

  .status {
    background-color: #f56c6c;
  }
}

#main {
  color: #606266;
  margin: 0 -10px;
  display: flex;
  align-items: flex-start;

  .left {
    width: 100%;

    .student_card {
      padding: 10px;

      .inner {
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
        display: flex;
        justify-content: space-between;
        align-items: center;

        .name {
          font-size: 1.2rem;
          font-weight: bold;
          margin-bottom: 10px;
        }
      }
    }
  }

  .right {
    width: 40%;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;

    .ve-pie {
      height: 380px !important;
    }

    .el-button {
      display: block;
      margin: 0 auto;
      position: relative;
    }
  }
}
</style>