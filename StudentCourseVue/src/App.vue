<template>
  <div id="app" v-loading.fullscreen.lock="loading" element-loading-text="正在验证用户身份">
    <transition>
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: true,
      userInfo: {
        id: "",
        identity: "",
        name: ""
      }
    }
  },
  mounted() {
    this.loading = false;
    this.userInfo.id = JSON.parse(sessionStorage.getItem("state")).id;
    this.userInfo.identity = JSON.parse(sessionStorage.getItem("state")).identity;
    var that = this;
    this.axios
      .get(this.userInfo.identity + "/userInfo?" + "id=" + this.userInfo.id)
      .then(res => {
        if (res.data.code == 200) {
          that.userInfo.name = res.data.data.name;
          sessionStorage.setItem("state", JSON.stringify(that.userInfo))
          //根据用户身份进行转发
          that.$router.push("/" + that.userInfo.identity);
        }
      })
      .catch(() => {
        this.$message({
          showClose: true,
          message: "获取用户基本信息失败！",
          type: "error"
        })
      });
  }
};
</script>

<style lang="scss" scoped>
.card {
  margin: 0 auto;
  width: 900px;
  position: relative;
  top: 100px;
}
</style>

