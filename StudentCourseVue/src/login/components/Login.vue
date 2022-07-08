<template>
  <div id="login">
    <div class="title">学生选课系统 | LOGIN</div>
    <hr>
    <el-form :model="loginForm" :rules="rules" label-width="50px" :label-position="'left'" ref="loginForm">
      <el-form-item label="账号" prop="account">
        <el-input v-model="loginForm.account"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="loginForm.password"></el-input>
      </el-form-item>
      <el-form-item label="身份">
        <el-radio-group v-model="loginForm.identity" size="medium">
          <el-radio border label="学生"></el-radio>
          <el-radio border label="教师"></el-radio>
          <el-radio border label="管理员"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-checkbox v-model="rememberPwd" class="rememberPwd">记住密码</el-checkbox>
      <el-form-item class="btn_box">
        <el-button type="primary" @click="loginClick">登 录</el-button>
        <el-button type="success" @click="$router.push('/register')">注 册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
//设置系统标题
document.title = "学生选课系统|登录";
export default {
  data() {
    return {
      loginForm: {
        account: "",
        password: "",
        identity: ""
      },
      rules: {
        account: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      rememberPwd: true
    };
  },
  methods: {
    loginClick() {
      var obj = {
          account: this.loginForm.account,
          password: this.loginForm.password
        };
        var param = this.$qs.stringify(obj)
        // alert(param)
      //表单验证
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          // TODO: 接入登录
          this.axios
            // .post(this.identityCtoE(this.loginForm.identity) + "/login?account=" + this.loginForm.account + "&password=" + this.loginForm.password)
            .post(this.identityCtoE(this.loginForm.identity) + "/login" , param)
            .then(res => {
              console.log(res.data.code)
              if (res.data.code == 200) {
                this.$message({
                  showClose: true,
                  message: '登陆成功,正在跳转...',
                  type: 'success',
                });
                // 记住用户名、密码和登陆状态
                if (this.rememberPwd) {
                  this.setCookie("account", this.loginForm.account);
                  this.setCookie("password", this.loginForm.password);
                } else {
                  this.setCookie("account", "");
                  this.setCookie("password", "");
                }
                // 保存token
                sessionStorage.setItem("token",res.data.message);
                //保存用户基本信息至session vuex状态管理自动同步session内容（见store.js）
                var state = {"id" : this.loginForm.account,"identity" : this.identityCtoE(this.loginForm.identity),"name" : ""};
                sessionStorage.setItem("state", JSON.stringify(state))
                setTimeout(() => {
                  window.location.href = this.COMMON.index_location;
                }, 1000);
              } else {
                this.$message({
                  showClose: true,
                  message: '账号或密码错误!',
                  type: 'error'
                })
              }
            })
            .catch(() => {
              this.$message({
                showClose: true,
                message: '服务器连接失败'
              });
            });
        } else {
          return false;
        }
      });
    },
    setCookie(cname, cvalue) {
      var cookie = cname + "=" + cvalue + "; "
      console.log("cooke:", cookie)
      document.cookie = cookie;
    },
    getCookie(cname) {
      var name = cname + "=";
      // console.log(document.cookie);
      var ca = document.cookie.split(";");
      for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
      }
      return "";
    },
    identityCtoE(str) {
      switch (str) {
        case "学生":
          return "student";
        case "教师":
          return "teacher";
        case "管理员":
          return "admin";
      }
    }
  },
  mounted() {
    this.loginForm.account = this.getCookie("account");
    this.loginForm.password = this.getCookie("password");
  }
};
</script>

<style lang="scss" scoped>

#login {
  .title {
    font-size: 1.5rem;
  }

  .btn_box {
    float: right;

    @media screen and (max-width: 560px) {
      float: initial;
      display: flex;
      justify-content: center;
      margin-left: -50px;
      margin-top: 10px;
    }
  }

  .rememberPwd {
    margin-left: 50px;
    margin-bottom: 20px;
  }
}
</style>

