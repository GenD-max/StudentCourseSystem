<template>
  <div id="schoolInfo">
    <h1>概览</h1>
    <div class="card student">
      <div class="titel">学生数</div>
      <div class="count">{{ this.data.studentNum }}</div>
    </div>
    <div class="card teacher">
      <div class="titel">教师数</div>
      <div class="count">{{ this.data.teacherNum }}</div>
    </div>
    <div class="card classroom">
      <div class="titel">管理员数</div>
      <div class="count">{{ this.data.adminNum }}</div>
    </div>
    <ve-pie :data="chartData"></ve-pie>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: {
        teacherNum: 99,
        studentNum: 99,
        courseNum: 88,
        adminNum: 10
      },
      chartData: {
        columns: ["用户", "数量"],
        rows: [
          { 用户: "教师用户", 数量: 1393 },
          { 用户: "学生用户", 数量: 3530 },
          { 用户: "管理员用户", 数量: 1 }
        ]
      }
    };
  },
  methods: {
    getSchoolInfo(callback) {
      this.axios
        .get("getNumInfo")
        .then(res => {
          if (res.data.code == 200) {
            this.data = res.data.data;
            //将数量放入vuex
            var state = JSON.parse(sessionStorage.getItem("state"));
            //...相当于args 可以将数组或者JSON的元素合并为一个
            state = {
              ...state,
              ...this.data
            }
            // console.log(state)
            sessionStorage.setItem("state", JSON.stringify(state))
            callback();
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接");
        });
    }
  },
  mounted() {
    this.getSchoolInfo(() => {
      this.chartData.rows[0].数量 = this.data.teacherNum;
      this.chartData.rows[1].数量 = this.data.studentNum;
      this.chartData.rows[2].数量 = this.data.adminNum;
    });
  }
};
</script>

<style lang='scss' scoped>
.card {
  display: flex;
  justify-content: space-between;
  font-size: 1.5rem;
  padding: 40px 40px;
}

.teacher {
  color: #23b7e5;
}

.student {
  background-color: #6c6fc0;
  color: white;
}

.classroom {
  background-color: #23b7e5;
  color: white;
}

.ve-pie {
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  font-size: 1rem !important;
}
</style>