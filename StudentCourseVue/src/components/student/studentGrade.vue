<template>
  <div id="studentGrade">
    <h1>查看成绩</h1>
    <div class="card semester" >
      <div class="title">第一学期</div>
      <el-row :gutter="20">
        <el-col :xl="6" :lg="8" :md="12" v-for="grade in grades" :key="grade.id">
          <div class="content">
            <div class="course_name">{{grade.course.cname}} | {{grade.course.ccredit}}学分</div>
            <div class="grade">
              分数：{{grade.ggrade}}
              <span v-if="grade.ggrade == null">未发放</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      grades : []
    };
  },
  methods: {
    getData() {
        // TODO: 获取学生成绩
        this.axios
          .get("student/getGrade/" + this.$store.state.id)
          .then(res => {
            if (res.data.code == 200) {
              this.grades = res.data.data;
            }
          })
          .catch(err => {
            console.log(err);
            this.$message("服务器无法连接，成绩获取失败");
          });
    }
  },
  mounted() {
    this.getData();
  }
};
</script>

<style lang='scss' scoped>
.semester {
  .title {
    padding-bottom: 20px;
    margin-bottom: 10px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  }
  .content {
    background-color: #409eff;
    padding: 20px;
    border-radius: 10px;
    color: white;
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
    .course_name {
      font-weight: bold;
      padding-right: 10px;
    }
  }
}
</style>