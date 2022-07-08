<template>
  <div id="manageCourse">
    <h1>添加课程</h1>
    <div class="main">
      <div class="title">填写课程信息：</div>
      <hr>
      <el-form :model="form" ref="form" :rules="rules" label-position="top">
        <el-form-item label="课程ID" prop="Cid">
          <el-input v-model="form.Cid" placeholder="请填写该课程的ID"></el-input>
        </el-form-item>
        <el-form-item label="课程名称" prop="Cname">
          <el-input v-model="form.Cname" placeholder="请填写选择该课程名称"></el-input>
        </el-form-item>
        <el-form-item label="学分" prop="Ccredit">
          <el-input v-model="form.Ccredit" placeholder="请填写选择该课程所能获得的学分"></el-input>
        </el-form-item>
        <el-form-item label="开课专业" prop="Cbelongpro">
          <el-input v-model="form.Cbelongpro" placeholder="请填写选择该课程所属的专业"></el-input>
        </el-form-item>
        <el-form-item label="周次" prop="Courseweek">
          <el-input v-model="form.Courseweek" placeholder="请填写选择该课程的周次（例如：1-10周）"></el-input>
        </el-form-item>
        <el-form-item label="教学楼" prop="Cteachbuilding">
          <el-input v-model="form.Cteachbuilding" placeholder="请填写选择该课程的教学楼"></el-input>
        </el-form-item>
        <el-form-item label="教室" prop="Cclassroom">
          <el-input v-model="form.Cclassroom" placeholder="请填写选择该课程的教室"></el-input>
        </el-form-item>
        <el-form-item label="课程简介" style="width:50%">
          <el-input type="textarea" :autosize="true" v-model="form.Cintroduction"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="8">
            <el-form-item label="课程教授年级" prop="Csemester">
              <el-select v-model="form.Csemester" placeholder="请选择">
                <el-option v-for="item in Semester" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="课程教授院系" prop="Cbelongcoll">
              <el-select v-model="form.Cbelongcoll" placeholder="请选择">
                <el-option v-for="item in Faculity" :key="item.id" :label="item.fname" :value="item.fname"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="课程类别" prop="Ctype" >
              <el-select v-model="form.Ctype" placeholder="请选择">
                <el-option label="必修" value="必修"></el-option>
                <el-option label="选修" value="选修"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-button type="success" round icon="el-icon-circle-plus" style="margin-top: 20px" @click="submit">添加课程
          </el-button>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        Tid: "",
        Cid: "",
        Cname: "",
        Ccredit: "",
        Cintroduction: "",
        Ctype: "",
        Cbelongcoll: "",
        Cbelongpro: "",
        Csemester: "",
        Courseweek: "",
        Cclassroom: "",
        Cteachbuilding: "",
        Cmodtime: ""
      },
      Faculity: [],
      Semester: [
        {
          value: 1,
          label: "大一上学期"
        },
        {
          value: 2,
          label: "大一下学期"
        },
        {
          value: 3,
          label: "大二上学期"
        },
        {
          value: 4,
          label: "大二下学期"
        },
        {
          value: 5,
          label: "大三上学期"
        },
        {
          value: 6,
          label: "大三下学期"
        },
        {
          value: 7,
          label: "大四上学期"
        },
        {
          value: 8,
          label: "大四下学期"
        }
      ],
      rules: {
        Cid: [
          {required: true,message: "请输入课程ID",trigger: ["blur", "choose"] }
        ],
        Cname: [
          {required: true,message: "请输入课程名称",trigger: ["blur", "choose"] }
        ],
        Ccredit: [
          { required: true, message: "请输入学分", trigger: ["blur", "choose"] }
        ],
        Cbelongpro: [
          {required: true,message: "请输入课程所属专业",trigger: ["blur", "choose"] }
        ],
        Courseweek: [
          {required: true,message: "请输入课程周次",trigger: ["blur", "choose"] }
        ],
        Cteachbuilding: [
          {required: true,message: "请输入课程开课教学楼",trigger: ["blur", "choose"] }
        ],
        Cclassroom: [
          {required: true,message: "请输入课程教室",trigger: ["blur", "choose"] }
        ],
        Csemester: [
          { required: true, message: "请选择教授年级", trigger: ["blur", "choose"] }
        ],
        Cbelongcoll: [
          { required: true, message: "请选择教授院系", trigger: ["blur", "choose"] }
        ],
        Ctype: [
          { required: true, message: "请选择课程类别", trigger: ["blur", "choose"] }
        ]
      },
    };
  },
  methods: {
    submit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.$confirm("您确定要执行此操作吗?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              this.addCourse();
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "操作已取消"
              });
            });
        } else {
          return false;
        }
      });
    },
    addCourse() {
      this.form.Tid = this.$store.state.id;
      this.form.Cmodtime = new Date().toLocaleString();
      var param = this.$qs.stringify(this.form);
      this.axios
        .post("teacher/insertCourse", param)
        .then(res => {
          if (res.data.code == 200) {
            this.$message.success("添加成功，请选择上课时间");
            this.$refs.form.resetFields();
            this.$router.push("/teacher/addClass");
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接，添加课程失败");
        });
    },
    getfaculity() {
      this.axios
        .get("/queryFaculity")
        .then(res => {
          if (res.data.code == 200) {
            this.Faculity = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接，无法获取院系列表");
        });
    }
  },
  mounted() {
    this.getfaculity();
  }
};
</script>

<style lang='scss' scoped>
.main {
  background-color: #fff;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);

  .el-select {
    width: 90%;
  }
}
</style>