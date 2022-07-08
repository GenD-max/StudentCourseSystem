<template>
  <div id="manageTeacher">
    <h1>管理教师</h1>
    <div class="container">
      <div class="left">
        <div class="card student" v-for="(item, index) in teacherData" :key="item.id">
          <div class="info">
            <div class="name">姓名：{{ item.tname }}</div>
            <div class="name">职工号：{{ item.tid }}</div>
            <div class="account">性别：{{ item.tsex }}</div>
            <div class="account">职位/职称：{{ item.tposition }}</div>
            <div class="account">联系电话：{{ item.tphone }}</div>
            <div class="account">个人简介：{{ item.tintroduction }}</div>
            <el-button type="danger" size="small" @click="deleteUser(item.tid, index)">删除</el-button>
          </div>
          <div class="avant"
            :style="[item.tphotourl != null ? { 'background': 'url(' + require('../../assets/photo/' + item.tphotourl) + ') center center /90% no-repeat' } : '']">
            <div class="imgName">
              姓名：{{ item.tname }}
            </div>
          </div>
        </div>
        <el-button type="primary" v-show="this.teacherData.length < this.total" style="float: right" @click="getMore">
          加载更多</el-button>
      </div>
      <div class="right">
        <ve-pie :data="chartData"></ve-pie>
        <div class="add_teacher">
          <div class="title">添加教师</div>
          <el-form :model="form" :rules="rules" ref="form" label-width="80px" label-position="left">
            <el-form-item label="教师姓名" prop="tname">
              <el-input v-model="form.tname"></el-input>
            </el-form-item>

            <el-form-item label="工号" prop="tid">
              <el-input v-model="form.tid"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="tpassword">
              <el-input type="password" v-model="form.tpassword"></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="tsex">
              <el-radio-group v-model="form.tsex">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="教师系别" prop="tposition">
              <el-select v-model="form.tposition" placeholder="请选择" style="width:100%">
                <el-option v-for="item in options" :key="item.id" :label="item" :value="item"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm" style="float:right">添 加</el-button>
              <el-button type="info" @click="clearForm" style="float:right; margin-right: 20px;">清 空</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      teacherData: [],
      chartData: {
        columns: ["用户", "数量"],
        rows: [{ 用户: "已展示用户", 数量: 0 }, { 用户: "未展示用户", 数量: 0 }]
      },
      total: 0,
      page: 1,
      form: {
        tid: "",
        tname: "",
        tpassword: "",
        tposition: "",
        tsex: ""
      },
      rules: {
        tid: [
          { required: true, message: "请填写信息", trigger: ["change", "blur"] }
        ],
        tname: [
          { required: true, message: "请填写信息", trigger: ["change", "blur"] }
        ],
        tpassword: [
          { required: true, message: "请填写信息", trigger: ["change", "blur"] }
        ],
        tsex: [
          { required: true, message: "请填写信息", trigger: ["change", "blur"] }
        ],
        tposition: [{ required: true, message: "请填写信息", trigger: "change" }]
      },
      options: ["教授", "副教授", "讲师", "其他"],
      value: ""
    };
  },
  methods: {
    getTeacherData(callback) {
      this.axios
        .get("teacher/getPageTeacher/" + this.page + "/3")
        .then(res => {
          if (res.data.code == 200) {
            this.teacherData = [
              ...this.teacherData,
              ...res.data.data
            ];
            // console.log(this.teacherData)

            //回调函数
            callback();
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接");
        });
    },
    getMore() {
      this.page++;
      this.getTeacherData(() => {
        this.chartData.rows[0].数量 = this.teacherData.length;
        this.chartData.rows[1].数量 = this.total - this.teacherData.length;
      });
    },
    deleteUser(id, index) {
      this.$confirm("您确定要删除该教师吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.axios
            .post("teacher/deleteTeacher?Tid=" + id)
            .then(res => {
              if (res.data.code == 200) {
                this.total--;
                //splice删除原数组 参数一：索引位置 参数二：个数
                this.teacherData.splice(index, 1);
                this.$message({
                  showClose: true,
                  message: "删除成功！",
                  type: 'success'
                });
                this.chartData.rows[0].数量 = this.teacherData.length;
                this.chartData.rows[1].数量 = this.total - this.teacherData.length;
              }
            })
            .catch(err => {
              console.log(err);
              this.$message("服务器无法连接");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "操作已取消"
          });
        });
    },
    addTeacher() {
      console.log(this.form)
      this.$confirm("添加信息确认无误吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.axios
            .post("teacher/insertTeacher?Tid=" + this.form.tid +
              "&Tname=" + this.form.tname +
              "&Tpassword=" + this.form.tpassword +
              "&Tsex=" + this.form.tsex +
              "&Tposition=" + this.form.tposition
            )
            .then(res => {
              if (res.data.code == 200) {
                this.$message({
                  showClose: true,
                  type: "success",
                  message: "添加成功!"
                });
                this.total++;
                this.page = 1;
                this.teacherData.push({
                  ...this.form
                })
                this.clearForm();
                this.teacherData = [];
                //传入一个函数 是回调函数
                this.getTeacherData(() => {
                  this.chartData.rows[0].数量 = this.teacherData.length;
                  this.chartData.rows[1].数量 = this.total - this.teacherData.length;
                });
              } else {
                this.$message.error("该工号已被注册！");
              }
            })
            .catch(err => {
              console.log(err);
              this.$message("服务器无法连接");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "操作已取消"
          });
        });
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.addTeacher();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    clearForm() {
      this.$refs.form.resetFields();
    }
  },
  mounted() {
    this.total = this.$store.state.teacherNum;
    this.getTeacherData(() => {
      this.chartData.rows[0].数量 = this.teacherData.length;
      this.chartData.rows[1].数量 = this.total - this.teacherData.length;
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
        background-color: #c4c8c9a2;
        width: 150px;
        height: 150px;
        border-radius: 100px;
        line-height: 100px;
        text-align: center;
        font-weight: bold;
        color: rgba(0, 0, 0, 0.5);
        font-size: 1.4rem;

        .imgName {
          margin-top: 125px;
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

    .add_teacher {
      background-color: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
      margin: -20px;
      margin-top: -10px;
      overflow: hidden;
      height: 400px;

      .title {
        margin: -20px;
        padding: 20px;
        background-color: #23b7e5;
        color: white;
        margin-bottom: 20px;
      }
    }
  }
}
</style>