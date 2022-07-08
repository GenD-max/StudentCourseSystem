<template>
  <div id="student" class="full_screen">
    <el-container>
      <el-aside width="250.5px">
        <user-info class="user_info"></user-info>
        <el-menu
          class="nav_bar"
          default-active="studentCourse"
          background-color="#1d2b36"
          text-color="#fff"
          active-text-color="#ffd04b"
          @select="select"
        >
          <el-menu-item index="studentCourse">
            <i class="el-icon-menu"></i>
            <span slot="title">学生课表</span>
          </el-menu-item>
          <el-menu-item index="chooseClass">
            <i class="el-icon-date"></i>
            <span slot="title">学生选课</span>
          </el-menu-item>
          <el-menu-item index="studentGrade">
            <i class="el-icon-setting"></i>
            <span slot="title">查看成绩</span>
          </el-menu-item>
          <el-menu-item index="studentInfo">
            <i class="el-icon-edit"></i>
            <span slot="title">修改个人信息</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main style="margin-left: 250px">
        <div class="tobar base_shadow">
          <div class="title">学生在线选课系统</div>
          <el-button class="exit_btn" type="danger" @click="exit">退出</el-button>
        </div>
        <transition>
          <router-view class="router"></router-view>
        </transition>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import userInfo from "../subcomponents/userInfo.vue";
export default {
  data() {
    return {};
  },
  components: {
    "user-info": userInfo
  },
  methods: {
    exit() {
      //清除Cookie
      document.cookie = "=;"
      //清除session
      sessionStorage.clear();
      window.location.href = this.COMMON.login_location;
    },
    select(index) {
      this.$router.push("/student/" + index);
    }
  },
  mounted(){
    if(this.$store.state.identity != 'student'){
      this.$router.push('/')
    }
  }
};
</script>

<style lang='scss' scoped>
.tobar {
  background-color: #fff;
  margin: -20px;
  line-height: 50px;
  padding: 10px;
  padding-left: 20px;
  font-size: 1.2rem;
  color: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: space-between;
  .exit_btn {
    margin-right: 20px;
    height: 40px;
    margin-top: 5px;
  }
}
.router {
  margin-top: 40px;
  color: rgba(0, 0, 0, 0.8);
}
</style>