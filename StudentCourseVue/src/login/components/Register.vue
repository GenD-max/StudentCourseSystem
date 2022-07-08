<template>
  <div id="register">
    <div class="title">学生选课系统 | REGISTER</div>
    <hr>
    <el-form :model="registerForm" :rules="rules" label-width="80px" :label-position="'left'" ref="registerForm">
      <el-form-item label="学号" prop="Sid">
        <el-input v-model="registerForm.Sid"></el-input>
      </el-form-item>

      <el-form-item label="姓名" prop="Sname">
        <el-input v-model="registerForm.Sname"></el-input>
      </el-form-item>

      <el-form-item label="身份证号" prop="Sidcard">
        <el-input v-model="registerForm.Sidcard"></el-input>
      </el-form-item>

      <el-form-item label="性别" prop="Ssex">
        <el-radio-group v-model="registerForm.Ssex">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="密码" prop="Spassword">
        <el-input type="password" v-model="registerForm.Spassword"></el-input>
      </el-form-item>

      <el-form-item label="重复密码" prop="Spassword2" required>
        <el-input type="password" v-model="registerForm.Spassword2"></el-input>
      </el-form-item>

      <el-row>
        <el-col :span="12">
          <el-form-item label="选择学院" prop="Sinstitution">
            <el-select style="width:95%" v-model="registerForm.Sinstitution" placeholder="请选择学院">
              <el-option v-for="item in Sinstitution" :key="item.id" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="入学时间" prop="Sgrade">
            <el-date-picker style="width: 100%;" v-model="registerForm.Sgrade" type="year" placeholder="选择年"
              value-format="yyyy"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item class="btn_box">
        <el-button type="primary" @click="registerClick">注 册</el-button>
        <el-button type="info" @click="$router.push('/login')">返回登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    var validatePassword2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.registerForm.Spassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        Sid: "",
        Sname: "",
        Sidcard: "",
        Ssex: "",
        Spassword: "",
        Spassword2: "",
        Sinstitution: "",
        Sgrade: "",
        Smodtime: null
      },
      rules: {
        Sid: [{ required: true, message: "请输入账号", trigger: "blur" }],
        Sname: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        Ssex: [{ required: true, message: "请输入性别", trigger: "blur" }],
        Sidcard: [{ required: true, message: "请输入身份证号", trigger: "blur" }],
        Spassword: [{ required: true, message: "请输入密码", trigger: "blur" }],
        Spassword2: [
          { validator: validatePassword2, trigger: ["blur", "change"] }
        ],
        Sinstitution: [
          { required: true, message: "请选择学院", trigger: ["blur", "change"] }
        ],
        Sgrade: [
          {
            required: true,
            message: "请选择入学时间",
            trigger: ["blur", "change"]
          }
        ]
      },
      Sinstitution: [],
    };
  },
  methods: {
    registerClick() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          let obj = {
            Sid: this.registerForm.Sid,
            Spassword: this.registerForm.Spassword,
            Sname: this.registerForm.Sname,
            Ssex: this.registerForm.Ssex,
            Sidcard: this.registerForm.Sidcard,
            faculty: this.registerForm.Sinstitution,
            Sgrade: this.registerForm.Sgrade,
            Smodtime: new Date()
          };
          console.log(obj);
          this.axios
            .post("student/register?Sid=" + this.registerForm.Sid +
              "&Spassword=" + this.registerForm.Spassword +
              "&Sname=" + this.registerForm.Sname +
              "&Ssex=" + this.registerForm.Ssex +
              "&Sidcard=" + this.registerForm.Sidcard +
              "&Sgrade=" + this.registerForm.Sgrade +
              "&Sinstitution=" + this.registerForm.Sinstitution)
            .then(res => {
              console.log(res.data);
              if (res.data.code == 200) {
                this.$message({
                  showClose: true,
                  message: "注册成功，请登录",
                  type: 'success'
                });
                this.$router.push("/login");
              } else {
                this.$message({
                  showClose: true,
                  message: "该学号已被占用，注册失败",
                  type: 'error'
                });
              }
            })
            .catch(() => {
              this.$message({
                showClose: true,
                message: "无法连接服务器",
              });
            });
        } else {
          return false;
        }
      });
    }
  },
  mounted() {
    this.axios
      .get("queryFaculity")
      .then(res => {
        for(var ele of res.data.data)
        this.Sinstitution.push(ele.fname)
      })
      .catch(() => {
        this.$message({
          showClose: true,
          message: "无法获取学院信息，请检查接口是否正常！",
          type: 'error'
        })
      })
  },
};
</script>


<style lang="scss" scoped>
#register {
  .title {
    font-size: 1.5rem;
  }

  .btn_box {
    float: right;
  }
}
</style>