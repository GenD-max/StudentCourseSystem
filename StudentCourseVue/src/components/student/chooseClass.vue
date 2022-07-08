<template>
  <div id="chooseClass">
    <h1>学生选课</h1>
    <div class="main">
      <el-row class="course_box card" :gutter="20">
        <div class="title">当前可选课程范围：{{ studentInfo.sinstitution }} | {{ studentInfo.sprofession }}专业课程</div>
        <el-col :md="12" :lg="8" :xl="6" v-for="item in courseData" :key="item.cid">
          <div class="course" @click="choose(item.cid)" :class="{ choose: (item.cid == chooseCourse) }">
            <div class="group">
              <div class="name">{{ item.cname }}</div>
            </div>
            <div class="group">
              <div class="faculityName">{{ item.teacher.tname }}</div>
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
        </el-col>
        <transition>
          <el-button type="success" icon="el-icon-check" circle v-if="flag && chooseCourse != ''" @click="submit">
          </el-button>
        </transition>
      </el-row>
      <div class="table">
        <div class="class_box card" style="overflow: visible">
          <div class="tableTitle">我的课程表</div>
          <div class="header">
            <div class="day">周一</div>
            <div class="day">周二</div>
            <div class="day">周三</div>
            <div class="day">周四</div>
            <div class="day">周五</div>
          </div>
          <div class="choose_card">
            <div class="class" v-for="(item, index) in classTable" :key="index">
              <div class="class_inner" v-if="item.index == ''"></div>
              <div class="ban" :class="{ choosed: true, error: item.error }" v-if="item.index != ''"></div>
            </div>
          </div>

          <transition>
            <div class="card tag" v-if="chooseCourse == ''" style="background:#409EFF">请选择左侧您想要选课的课程</div>
          </transition>

          <transition>
            <div class="card tag" v-if="flag == false" style="background:#F56C6C">该课程与已选课程时间冲突，无法选课</div>
          </transition>

          <transition>
            <div class="card tag" v-if="flag == true && chooseCourse != ''" style="background:#67C23A">本节课为可选状态</div>
          </transition>
        </div>
        <div class="class_box card" style="overflow: visible">
          <div class="tableTitle">所选课程安排表</div>
          <div class="header">
            <div class="day">周一</div>
            <div class="day">周二</div>
            <div class="day">周三</div>
            <div class="day">周四</div>
            <div class="day">周五</div>
          </div>
          <div class="choose_card">
            <div class="class" v-for="(item, index) in chooseCourseClassData" :key="index">
              <div class="class_inner" v-if="item.index == ''"></div>
              <div class="ban" :class="{ choose: item.choose, error: item.error }" v-if="item.index != ''"></div>
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
      obj: {
        cname: "",
        cid: "",
        tid: "",
        monday: "",
        tuesday: "",
        wednesday: "",
        thursday: "",
        friday: ""
      },
      studentInfo: {},
      courseData: [],
      chooseCourseClassData: [],
      classTable: [],
      chooseCourse: "",
      flag: true
    };
  },
  methods: {
    getStudentClass() {
      this.axios
        .get("student/getSchedule/" + this.$store.state.id)
        .then(res => {
          if (res.data.code == 200) {
            this.classTable = this.parseData(res.data.data, false);
            // console.log(this.classTable)
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接");
        });
    },
    getWeekDay(weekday) {
      switch (weekday) {
        case 'monday': return 1;
        case 'tuesday': return 2;
        case 'wednesday': return 3;
        case 'thursday': return 4;
        case 'friday': return 5;
        default: return 0;
      }
    },
    getCourseData() {
      this.axios
        .get("teacher/getPageCourse/1/100")
        .then(res => {
          if (res.data.code == 200) {
            // //去除某个课程无开课老师的情况，逻辑上不需要从此处更改，需要在管理员删除教师后直接清除其所有数据，这里我比较懒，直接改的这一模块
            // var temp = res.data.data;
            // var result = [];
            // for (var i in temp) {
            //   if (temp[i].teacher != null) {
            //     result.push(temp[i]);
            //   }
            // }    
            this.courseData = res.data.data;
            // console.log(this.courseData)
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接，获取学生可选课程失败");
        });
    },
    // parseData:将获取到的classData（学生已选课节）填充数据，转存到classTable中
    parseData(data, isOne) {
      let finalData = [];
      var scheduleList = [];
      for (let i = 0; i < data.length; i++) {
        var schedule = {
          cname: data[i].cname,
          tname: data[i].teacher.tname,
          schedule: {
            monday: data[i].monday == null ? "" : data[i].monday,
            tuesday: data[i].tuesday == null ? "" : data[i].tuesday,
            wednesday: data[i].wednesday == null ? "" : data[i].wednesday,
            thursday: data[i].thursday == null ? "" : data[i].thursday,
            friday: data[i].friday == null ? "" : data[i].friday
          },
          index: "",
          courseweek: data[i].courseweek,
          cclassroom: isOne ? data[i].cclassroom : data[i].course.cclassroom,
          cteachbuilding: isOne ? data[i].cteachbuilding : data[i].course.cteachbuilding,
        };

        for (var k in schedule.schedule) {
          var timeList = schedule.schedule[k].split(",");
          // console.log(schedule.schedule)
          for (var j of timeList) {
            var tmp = {
              cname: data[i].cname,
              tname: data[i].teacher.tname,
              schedule: {
                monday: data[i].monday == null ? "" : data[i].monday,
                tuesday: data[i].tuesday == null ? "" : data[i].tuesday,
                wednesday: data[i].wednesday == null ? "" : data[i].wednesday,
                thursday: data[i].thursday == null ? "" : data[i].thursday,
                friday: data[i].friday == null ? "" : data[i].friday
              },
              index: (this.getWeekDay(k) - 1) * 4 + parseInt(j),
              courseweek: data[i].courseweek,
              cclassroom: isOne ? data[i].cclassroom : data[i].course.cclassroom,
              cteachbuilding: isOne ? data[i].cteachbuilding : data[i].course.cteachbuilding,
            }
            scheduleList.push(tmp);
          }
        }
      }
      //创建20个课程 填满
      for (let i = 0; i < 20; i++) {
        finalData.push({
          cname: "",
          tname: "",
          schedule: {
            monday: "",
            tuesday: "",
            wednesday: "",
            thursday: "",
            friday: "",
          },
          index: "",
          courseweek: "",
          cclassroom: "",
          cteachbuilding: "",
        });
      }
      //把已有的课程放入finaData
      for (var ele of scheduleList) {
        finalData[ele.index - 1] = ele;
      }
      return finalData;
    },
    // 连带执行chooseClassToClassData
    choose(id) {
      this.chooseCourse = id;
      this.axios
        .get("teacher/getOneCourse/" + this.chooseCourse)
        .then(res => {
          if (res.data.code == 200) {
            var r = res.data.data;
            for(var i in this.obj){
              this.obj[i] = r[i] == null ? "" : r[i];
            }
            // console.log(this.obj)
            this.chooseCourseClassData = [r];
            this.chooseClassToClassData();
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接，获取选择课程课节失败");
        });
    },
    chooseClassToClassData() {
      //1. chooseCourseClassData是选择课程的时间安排表
      //2.  调用parseData填充chooseCourseClassData数据
      this.chooseCourseClassData = this.parseData(this.chooseCourseClassData, true);
      this.flag = true;
      // =====初始化课程表=======
      for (var i = 0; i < this.chooseCourseClassData.length; ++i) {
        // 3. 为chooseCourseClassData添加choose属性
        // console.log(i)
        this.chooseCourseClassData[i].choose = true;
        let index = this.chooseCourseClassData[i].index;
        // 5. 将chooseCourseClassData与classData中的数据做比对，如果比对冲突，将this.flag设置为false，并且将冲突的对象添加ferror: true字段
        this.classTable.some(item => {
          if (item.index == index && item.index != "" && index != "") {
            this.chooseCourseClassData[i].error = true;
            this.flag = false;
            return true;
          }
        });
      }
    },
    submit() {
      this.axios
        .post("student/insertCoursePlan?Cname=" + this.obj.cname +
            "&Sid=" + this.$store.state.id + 
            "&Cid=" + this.obj.cid + 
            "&Tid=" + this.obj.tid + 
            "&Monday=" + this.obj.monday + 
            "&Tuesday=" + this.obj.tuesday + 
            "&Wednesday=" + this.obj.wednesday + 
            "&Thursday=" + this.obj.thursday + 
            "&Friday=" + this.obj.friday
          )
        .then(res => {
          if (res.data.code == 200) {
            this.$message({
              showClose: true,
              message: "选课成功",
              type: "success"
            })
            this.$router.push("/student");
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接");
        });
    }
  },
  mounted() {
    this.axios
      .get("student/getOneStudent/" + this.$store.state.id)
      .then((res) => {
        if (res.data.code == 200) {
          this.studentInfo = res.data.data;
          // console.log(this.studentInfo)
        }
      })
      .catch(() => {
        this.$message("服务器连接异常")
      });
    this.getStudentClass(); //连带执行parseData
    this.getCourseData();
  }
};
</script>

<style lang='scss' scoped>
.choose {
  background-color: #67c23a !important;
  border-color: #67c23a !important;
  color: white !important;
}

.choosed {
  background-color: #409eff !important;
  border-color: #409eff !important;
  color: white !important;
}

.error {
  background-color: #f56c6c !important;
  border-color: #f56c6c !important;
  color: white !important;
}

.main {
  position: relative;
  display: flex;

  .course_box {
    flex: 1 1 auto;
    margin-right: 20px !important;
    margin-left: 0px !important;
    padding-left: 10px;
    padding-right: 10px;
    padding-bottom: 10px;

    .title {
      color: white;
      background-color: #409eff;
      margin: -20px -10px;
      margin-bottom: 20px;
      padding: 20px;
      border-radius: 10px 10px 0 0;
    }

    .course {
      margin-bottom: 21px;
      padding: 25px;
      border-radius: 10px;
      box-sizing: border-box;
      border: 3px solid rgba(0, 0, 0, 0.12);
      cursor: pointer;
      font-size: 14px;
      color: #606266;

      .group {
        display: flex;
        line-height: 1.7;

        .name {
          font-size: 20px;
          font-weight: bold;
        }

        .credit {
          margin-right: 10px;
        }
      }
    }

    .el-button {
      width: 50px;
      height: 50px;
      position: absolute;
      bottom: -4px;
      right: 30px;
    }
  }

  .class_box {
    width: 310px;
    height: 267px;

    .tableTitle {
      text-align: center;
      font-weight: bold;
      padding-bottom: 10px;
    }

    .header {
      display: flex;

      .day {
        width: 20%;
        font-size: 14px;
        text-align: center;
      }
    }

    .choose_card {
      display: flex;
      flex-direction: column;
      flex-wrap: wrap;
      height: 220px;

      .class {
        width: 20%;
        height: 55px;
        padding: 10px;
        box-sizing: border-box;

        .class_inner {
          height: 100%;
          border-radius: 5px;
          border: 2px solid rgba(0, 0, 0, 0.2);
        }

        .ban {
          height: 100%;
          border-radius: 5px;
          border: 2px solid rgba(0, 0, 0, 0.1);
          background: rgba(0, 0, 0, 0.2);
        }
      }
    }
  }

  .tag {
    margin: 360px -20px;
    color: white;
  }
}
</style>