<template>
  <div id="userInfo">
    <div class="name">{{name}}</div>
    <div class="identity">
      <span>{{this.$store.state.identity | identity}}</span>
    </div>
    <div class="account">学号/工号：{{this.$store.state.id}}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: ""
    };
  },
  mounted(){
    this.name = JSON.parse(sessionStorage.getItem("state")).name;
  }
};
</script>

<style lang='scss' scoped>
#userInfo {
  width: 250px;
  background: #1d2b36;
  color: white;
  padding: 20px;
  box-sizing: border-box;
  border-bottom: 2px solid #121f28;
  .name {
    background-color: #6C6FC0;
    width: 150px;
    height: 150px;
    border-radius: 150px;
    line-height: 120px;
    text-align: center;
    margin: 0 auto;
    font-size: 1.5rem;
    margin-bottom: 20px;
    // border: 5px solid white;
    box-shadow: 0 5px 20px rgba(0,0,0,0.8);
  }
  .identity {
    text-align: center;
    position: relative;
    top: -80px;
    span {
      text-align: center;
      font-size: 14px;
      display: inline;
      padding: 3px 10px;
      border-radius: 10px;
      font-weight: bold;
    }
  }
  .account {
    color: rgba(211, 211, 211, 0.664);
    font-size: 14px;
    text-align: center;
    margin-bottom: 10px;
  }
}
</style>