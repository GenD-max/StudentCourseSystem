<template>
  <div id="addClass" v-loading.fullscreen.lock="loading" element-loading-text="系统正在自动分配合适的上课教室">
    <!-- <div> -->
    <h1>添加上课时间</h1>
    <div class="container">
      <div class="main">
        <div class="left">
          <div class="title">
            <span class="step">STEP. 1</span>选择要添加上课时间的课程：
          </div>
          <div class="left_inner">
            <div v-if="courseData.length == 0" style="padding-left: 10px;">请添加课程</div>
            <div class="inner" v-for="item in courseData" :key="item.id">
              <div class="course" @click="chooseCourse = item.cid; chooseOneCourse(item.cid)"
                :class="{ userchoose: (item.cid == chooseCourse) }">
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
          <transition>
            <el-button type="success" icon="el-icon-check" circle v-if="chooseCourse != '' && chooseData.length != 0"
              @click="submit"></el-button>
          </transition>
        </div>
        <div class="right">
          <div class="title">
            <span class="step">STEP. 2</span>请选择上课时间：
          </div>
          <div class="header">
            <div class="day">周一</div>
            <div class="day">周二</div>
            <div class="day">周三</div>
            <div class="day">周四</div>
            <div class="day">周五</div>
          </div>
          <div class="choose_card">
            <div class="class" v-for="(item, index) in courseTable" :key="index">
              <div class="class_inner" :class="{ userchoose: item.choose }" v-if="item.index == ''"
                @click="choose(item, index);">
              </div>
              <div class="ban" :class="{ choosed: true }" v-if="item.index != ''"></div>
            </div>
          </div>
          <div class="tag">
            <i class="el-icon-info"></i> 请选择上方对应的上课时间，蓝色区块为不可选择上课时间，提交课程后将不能更改。
          </div>
          <div class="choose_list">
            <div class="title">已选时间：</div>
            <div class="no_chooseData" v-if="chooseData.length == 0">请选课</div>
            <div class="list" v-for="(item, index) in chooseData" :key="index">
              <div>{{ item.day | toWeek }}</div>
              <div>第{{ item.time }}节</div>
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
      //教师的课程
      courseData: [],
      // 教师已排的课程表
      courseTable: [],
      // 选择的上课时间
      chooseData: [],
      //选择课程的信息
      chooseCourseData: {},
      chooseCourse: "",
      loading: false
    };
  },
  methods: {
    // parseData:将获取到的classData（学生已选课节）填充数据，转存到classTable中
    parseData(data) {
      let finalData = [];
      var scheduleList = [];
      for (let i = 0; i < data.length; i++) {
        var schedule = {
          cname: data[i].cname,
          schedule: {
            monday: data[i].monday == null ? "" : data[i].monday,
            tuesday: data[i].tuesday == null ? "" : data[i].tuesday,
            wednesday: data[i].wednesday == null ? "" : data[i].wednesday,
            thursday: data[i].thursday == null ? "" : data[i].thursday,
            friday: data[i].friday == null ? "" : data[i].friday
          },
          index: "",
          courseweek: data[i].courseweek,
          cclassroom: data[i].cclassroom,
          cteachbuilding: data[i].cteachbuilding,
        };
        for (var k in schedule.schedule) {
          var timeList = schedule.schedule[k].split(",");
          // console.log(schedule.schedule)
          for (var j of timeList) {
            var tmp = {
              cname: data[i].cname,
              schedule: {
                monday: data[i].monday == null ? "" : data[i].monday,
                tuesday: data[i].tuesday == null ? "" : data[i].tuesday,
                wednesday: data[i].wednesday == null ? "" : data[i].wednesday,
                thursday: data[i].thursday == null ? "" : data[i].thursday,
                friday: data[i].friday == null ? "" : data[i].friday
              },
              index: (this.getWeekDay(k) - 1) * 4 + parseInt(j),
              courseweek: data[i].courseweek,
              cclassroom: data[i].cclassroom,
              cteachbuilding: data[i].cteachbuilding,
            }
            scheduleList.push(tmp);
          }
        }
      }
      //创建20个课程 填满
      for (let i = 0; i < 20; i++) {
        finalData.push({
          cname: "",
          schedule: {
            monday: "",
            tuesday: "",
            wednesday: "",
            thursday: "",
            friday: ""
          },
          index: "",
          courseweek: "",
          cclassroom: "",
          cteachbuilding: "",
          choose: false
        });
      }
      //把已有的课程放入finaData
      for (var ele of scheduleList) {
        finalData[ele.index - 1] = ele;
      }
      return finalData;
    },
    choose(item, index) {
      this.courseTable[index].choose = !this.courseTable[index].choose;
      if (this.courseTable[index].choose) {
        var tableIndex = index + 1;
        //根据索引计算第几节
        var time = tableIndex % 4 == 0 ? 4 : tableIndex % 4;
        //根据索引计算第几天
        var day = Math.floor((tableIndex - time) / 4) + 1;
        var obj = {
          time: time,
          day: day,
          index: index
        };
        console.log(obj)
        this.chooseData.push(obj);
      } else {
        for (let i in this.chooseData) {
          if (this.chooseData[i].index == index) {
            //splice：删除第i个位置之后的i个元素
            this.chooseData.splice(i, 1);
          }
        }
      }
    },
    submit() {
      this.$confirm("您确定要添加选择的上课时间吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.addClass();
        })
        .catch((err) => {
          console.log(err)
          this.$message({
            type: "info",
            message: "操作已取消"
          });
        });
    },
    addClass() {
      this.loading = true;
      //获取当前的
      var obj = {
        Monday: this.chooseCourseData.monday == null ? [] : this.chooseCourseData.monday.split(","),
        Tuesday: this.chooseCourseData.tuesday == null ? [] : this.chooseCourseData.tuesday.split(","),
        Wednesday: this.chooseCourseData.wednesday == null ? [] : this.chooseCourseData.wednesday.split(","),
        Thursday: this.chooseCourseData.thursday == null ? [] : this.chooseCourseData.thursday.split(","),
        Friday: this.chooseCourseData.friday == null ? [] : this.chooseCourseData.friday.split(",")
      };
      var data = this.chooseData;
      for(var i of data){
        switch(i.day){
          case 1 : obj.Monday.push(i.time + "");break;
          case 2 : obj.Tuesday.push(i.time + "");break;
          case 3 : obj.Wednesday.push(i.time + "");break;
          case 4 : obj.Thursday.push(i.time + "");break;
          case 5 : obj.Friday.push(i.time + "");break;
        }
      }
      for(var j in obj){
        obj[j] = obj[j].join(",");
      }
      obj["Cid"] = this.chooseCourseData.cid;
      // console.log(obj)
      var param = this.$qs.stringify(obj)
      // console.log(param)
      this.axios
        .post("teacher/updateCourse",param)
        .then(res => {
          if (res.data.code == 200) {
            setTimeout(() => {
              this.loading = false;
              this.$message.success("添加成功");
              this.$router.push("/teacher");
            }, 2000);
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接");
          this.loading = false;
        });
    },
    getTeacherCourse() {
      this.axios
        .get("teacher/getSchedule/" + this.$store.state.id)
        .then(res => {
          if (res.data.code == 200) {
            this.courseData = res.data.data;
            this.courseTable = this.parseData(res.data.data)
          }
        })
        .catch(err => {
          console.log(err);
          this.$message("服务器无法连接");
        });
    },
    chooseOneCourse(id) {
      //清空已选课程
      this.chooseData = [];
      this.axios
        .get("teacher/getOneCourse/" + id)
        .then(res => {
          if (res.data.code == 200) {
            this.chooseCourseData = res.data.data;
            // this.courseTable = this.parseData([res.data.data]);
            //让选择的课程复原
            for(var i of this.courseTable){
              i.choose = false;
            }
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
    }
  },
  mounted() {
    //加载老师已选课程信息
    this.getTeacherCourse();
  }
};
</script>

<style lang='scss' scoped>
.step {
  color: white;
  padding: 5px 10px;
  margin-right: 5px;
  background-color: #409eff;
  border-radius: 5px;
}

.userchoose {
  background-color: #67c23a;
  border: 2px solid #67c23a;
  color: white !important;
}

.choosed {
  background-color: #409eff !important;
  border-color: #409eff !important;
  color: white !important;
}

.main {
  width: 1250px;
  margin: 40px auto;
  display: flex;
  align-items: flex-start;

  .left {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    width: 100%;
    height: auto;
    position: relative;

    .left_inner {
      display: flex;
      flex-wrap: wrap;
      margin: 0px -10px;
      margin-top: 20px;

      .inner {
        width: 33.3%;
        padding: 10px;
        box-sizing: border-box;

        .course {
          padding: 20px;
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
      }
    }

    .el-button {
      position: absolute;
      right: 20px;
      width: 50px;
      height: 50px;
      font-size: 1.2rem;
      bottom: -25px;
    }
  }

  .right {
    width: 430px;
    margin-left: 20px;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    color: #606266;

    .title {
      margin-bottom: 20px;
      color: #303133;
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
          cursor: pointer;

          &:hover {
            border: 2px solid #67c23a;
          }
        }

        .ban {
          height: 100%;
          border-radius: 5px;
          border: 2px solid rgba(0, 0, 0, 0.1);
          background: rgba(0, 0, 0, 0.2);
          cursor: not-allowed;
        }
      }
    }

    .tag {
      color: white;
      margin: 30px -20px;
      padding: 20px;
      background-color: #409eff;
      font-size: 14px;
      line-height: 1.7;
    }

    .list {
      background-color: #67c23a;
      color: white;
      display: flex;
      padding: 10px 20px;
      border-radius: 5px;
      justify-content: space-between;
      margin-bottom: 10px;
    }
  }
}
</style>