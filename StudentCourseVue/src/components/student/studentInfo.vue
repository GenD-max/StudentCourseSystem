<template>
    <div id="studentInfo">
        <div class="container">
            <h1>修改个人信息</h1>
            <div class="card semester">
                <el-descriptions title="" :column="2" border>
                    <el-descriptions-item label="学号">{{ studentInfo.sid }}</el-descriptions-item>
                    <el-descriptions-item label="姓名">{{ studentInfo.sname }}</el-descriptions-item>
                    <el-descriptions-item label="性别">{{ studentInfo.ssex }}</el-descriptions-item>
                    <el-descriptions-item label="年龄">{{ studentInfo.sage }}</el-descriptions-item>
                    <el-descriptions-item label="身份证号">{{ studentInfo.sidcard }}</el-descriptions-item>
                    <el-descriptions-item label="年级">{{ studentInfo.sgrade }}</el-descriptions-item>
                    <el-descriptions-item label="专业">{{ studentInfo.sprofession }}</el-descriptions-item>
                    <el-descriptions-item label="班级">{{ studentInfo.sclass }}</el-descriptions-item>
                    <el-descriptions-item label="账号类型">
                        <el-tag size="small">{{ this.$store.state.identity | identity }}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="学院">{{ studentInfo.sinstitution }}</el-descriptions-item>
                    <el-descriptions-item label="密码">{{ studentInfo.spassword }}</el-descriptions-item>
                    <el-descriptions-item label="修改时间">{{ studentInfo.smodtime }}</el-descriptions-item>
                </el-descriptions>
                <el-row style="margin-top:10px">
                    <el-button type="primary" size="" style="float:right" @click="dialogFormVisible = true">修改
                    </el-button>
                </el-row>

                <el-dialog title="修改信息" :visible.sync="dialogFormVisible">
                    <el-form :model="studentInfo" :rules="rules" label-width="80px" :label-position="'left'"
                        ref="studentInfo">
                        <el-form-item label="学号" prop="sid">
                            <el-input v-model="studentInfo.sid" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="姓名" prop="sname">
                            <el-input v-model="studentInfo.sname" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="性别" prop="ssex">
                            <el-radio-group v-model="studentInfo.ssex">
                                <el-radio label="男"></el-radio>
                                <el-radio label="女"></el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="类型">
                            <el-tag size="">{{ this.$store.state.identity | identity }}</el-tag>
                        </el-form-item>
                        <el-form-item label="身份证号" prop="sidcard">
                            <el-input v-model="studentInfo.sidcard"></el-input>
                        </el-form-item>
                        <el-form-item label="入学时间" prop="sgrade">
                            <el-date-picker style="width: 100%;" v-model="studentInfo.sgrade" type="year"
                                placeholder="选择年" value-format="yyyy"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="专业" prop="sprofession">
                            <el-input v-model="studentInfo.sprofession"></el-input>
                        </el-form-item>
                        <el-form-item label="班级" prop="sclass">
                            <el-input v-model="studentInfo.sclass"></el-input>
                        </el-form-item>
                        <el-form-item label="选择学院" prop="sinstitution">
                            <el-select style="width:28%" v-model="studentInfo.sinstitution" placeholder="请选择学院">
                                <el-option v-for="item in Sinstitution" :key="item.id" :label="item" :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="密码" prop="spassword">
                            <el-input v-model="studentInfo.spassword"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false;updateStudentArray()">取 消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false; onSubmit()">确 定</el-button>
                    </div>
                </el-dialog>
            </div>
        </div>
    </div>
</template>

<script>
export default ({
    data() {
        return {
            studentInfo: {

            },
            form: {
                Sid: "",
                Sname: "",
                Ssex: "",
                Sage: "",
                Sidcard: "",
                sgrade: "",
                Sprofession: "",
                Sclass: "",
                Sinstitution: "",
                Spassword: "",
                Smodtime: ""
            },
            dialogTableVisible: false,
            dialogFormVisible: false,
            rules: {
                sname: [{ required: true, message: "请输入姓名", trigger: "blur" }],
                ssex: [{ required: true, message: "请输入性别", trigger: "blur" }],
                sidcard: [{ required: true, message: "请输入身份证号", trigger: "blur" }],
                sprofession: [{ required: true, message: "请输入专业", trigger: "blur" }],
                sclass: [{ required: true, message: "请输入班级", trigger: "blur" }],
                spassword: [{ required: true, message: "请输入密码", trigger: "blur" }],
                sinstitution: [
                    { required: true, message: "请选择学院", trigger: ["blur", "change"] }
                ],
                sgrade: [
                    {
                        required: true,
                        message: "请选择入学时间",
                        trigger: ["blur", "change"]
                    }
                ]
            },
            formLabelWidth: '120px',
            Sinstitution: []
        };
    },
    methods: {
        onSubmit() {
            this.form.Sid = this.studentInfo.sid;
            this.form.Sname = this.studentInfo.sname;
            this.form.Ssex = this.studentInfo.ssex;
            this.form.Sage = this.studentInfo.sage;
            this.form.Sidcard = this.studentInfo.sidcard;
            this.form.Sgrade = this.studentInfo.sgrade;
            this.form.Sprofession = this.studentInfo.sprofession;
            this.form.Sclass = this.studentInfo.sclass;
            this.form.Sinstitution = this.studentInfo.sinstitution;
            this.form.Spassword = this.studentInfo.spassword;
            this.form.Smodtime = new Date().toLocaleString();
            var parmas = this.$qs.stringify(this.form);
            this.$refs.studentInfo.validate(valid => {
                if (valid) {
                    this.axios
                        .post("student/updateStudent", parmas)
                        .then(res => {
                            if (res.data.code == 200) {
                                this.$message.success("修改成功");
                            }
                        })
                        .catch((err) => {
                            console.log(err)
                            this.$message("接口异常，无法连接服务器");
                        }
                        )
                } else {
                    return false;
                }
            })
        },
        updateStudentArray() {
            this.axios
                .get("student/getOneStudent/" + this.$store.state.id)
                .then(res => {
                    if (res.data.code == 200) {
                        this.studentInfo = res.data.data;
                        // console.log(this.studentInfo)
                    }
                })
                .catch(err => {
                    console.log(err);
                    this.$message("无法连接到服务器，请检查接口")
                });
        }
    },
    mounted() {
        this.updateStudentArray();
        this.axios
            .get("queryFaculity")
            .then(res => {
                for (var ele of res.data.data)
                    this.Sinstitution.push(ele.fname)
            })
            .catch(() => {
                this.$message({
                    showClose: true,
                    message: "无法获取学院信息，请检查接口是否正常！",
                    type: 'error'
                })
            })
    }
})
</script>

<style lang="scss" scoped>
.container {
    width: 550px;
}

.semester {
    .title {
        padding-bottom: 20px;
        margin-bottom: 10px;
        // border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    }
}
</style>