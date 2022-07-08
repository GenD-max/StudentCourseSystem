<template>
  <div id="manageGrade">
    <h1>成绩管理</h1>
    <div class="main">
      <div class="left">
        <div class="title">选择课程录入成绩</div>
        <div class="course_box">
          <div class="col" v-for="item in courseData" :key="item.cid">
            <div class="course" @click="gotoCourseGrade(item.cid)">
              <div class="group">
                  <div class="name">{{ item.cname }}</div>
                </div>
                <div class="group">
                  <div class="faculityName">{{ $store.state.name }}</div>
                </div>
                <div class="group">
                  <div class="credit">{{ item.ccredit }}学分</div>
                  <div class="proptype">{{ item.ctype }}</div>
                </div>
                <div class="group">
                  <div class="credit">{{ item.courseweek }}</div>
                  <div class="proptype">{{ item.cteachbuilding }}{{ item.cclassroom }}</div>
                </div>
                <div class="group">
                  <div class="faculityName">{{ item.cbelongpro }}</div>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      courseData: []
    };
  },
  methods: {
    getTeacherCourse() {
      this.axios
        .get("teacher/getSchedule/" + this.$store.state.id)
        .then(res => {
          if (res.data.code == 200) {
            this.courseData = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接，获取未结课课程失败");
        });
    },
    gotoCourseGrade(id) {
      this.$router.push("/teacher/manageGrade/" + id);
    }
  },
  mounted() {
    this.getTeacherCourse();
  }
};
</script>

<style lang='scss' scoped>
.main {
  display: flex;
  align-items: flex-start;
  .title {
    border-bottom: 1px solid rgba(0, 0, 0, 0.12);
    padding-bottom: 10px;
  }
  .left {
    width: 100%;
    margin-right: 30px;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  }
  .right {
    width: 50%;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    .course {
      background-color: #6c6fc0 !important;
    }
  }
  .course_box {
    display: flex;
    flex-wrap: wrap;
    margin: 10px -10px;
    .col {
      width: 20%;
      padding: 10px;
      box-sizing: border-box;
      .course {
        background-color: #409eff;
        padding: 15px;
        border-radius: 5px;
        color: white;
        cursor: pointer;
        &:hover {
          box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
        }
        .group {
          display: flex;
          line-height: 1.7;
          font-size: 14px;
          .name {
            font-size: 1.2rem;
            font-weight: bold;
            width: 100%;
          }
          .credit {
            margin-right: 10px;
          }
          .semester {
            padding-bottom: 5px;
            margin-bottom: 5px;
            border-bottom: 1px solid white;
            width: 100%;
          }
        }
      }
    }
  }
}
</style>