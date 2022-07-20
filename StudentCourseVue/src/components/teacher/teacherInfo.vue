<template>
    <div id="teacherInfo">
        <div class="container">
            <h1>修改个人信息</h1>
            <div class="card semester">
                <el-descriptions title="" :column="1" border>
                    <el-descriptions-item label="工号">{{ teacherInfo.tid }}</el-descriptions-item>
                    <el-descriptions-item label="姓名">{{ teacherInfo.tname }}</el-descriptions-item>
                    <el-descriptions-item label="性别">{{ teacherInfo.tsex }}</el-descriptions-item>
                    <el-descriptions-item label="职位/职称">{{ teacherInfo.tposition }}</el-descriptions-item>
                    <el-descriptions-item label="账号类型">
                        <el-tag size="small">{{ this.$store.state.identity | identity }}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="密码">{{ teacherInfo.tpassword }}</el-descriptions-item>
                    <el-descriptions-item label="联系方式">{{ teacherInfo.tphone }}</el-descriptions-item>
                    <el-descriptions-item label="邮箱">{{ teacherInfo.temail }}</el-descriptions-item>
                    <el-descriptions-item label="个人简介">{{ teacherInfo.tintroduction }}</el-descriptions-item>
                    <el-descriptions-item label="修改时间">{{ teacherInfo.tmodtime }}</el-descriptions-item>
                </el-descriptions>
                <el-row style="margin-top:10px">
                    <el-button type="primary" size="" style="float:right" @click="dialogFormVisible = true">修改
                    </el-button>
                </el-row>

                <el-dialog title="修改信息" :visible.sync="dialogFormVisible">
                    <el-form :model="teacherInfo" :rules="rules" label-width="80px" :label-position="'left'"
                        ref="teacherInfo">
                        <el-form-item label="工号" prop="tid">
                            <el-input v-model="teacherInfo.tid" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="姓名" prop="tname">
                            <el-input v-model="teacherInfo.tname" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="性别" prop="tsex">
                            <el-radio-group v-model="teacherInfo.tsex">
                                <el-radio label="男"></el-radio>
                                <el-radio label="女"></el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="职位/职称" prop="tposition">
                            <el-input v-model="teacherInfo.tposition" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="类型">
                            <el-tag size="">{{ this.$store.state.identity | identity }}</el-tag>
                        </el-form-item>
                        <el-form-item label="密码" prop="tpassword">
                            <el-input v-model="teacherInfo.tpassword"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式" prop="tphone">
                            <el-input v-model="teacherInfo.tphone"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱" prop="temail">
                            <el-input v-model="teacherInfo.temail"></el-input>
                        </el-form-item>
                        <el-form-item label="个人简介" style="width:50%">
                            <el-input type="textarea" :autosize="true" v-model="teacherInfo.tintroduction"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false;updateTeacherArray()">取 消</el-button>
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
            teacherInfo: {
                tid: "",
                tname: "",
                tsex: "",
                tposition: "",
                tpassword: "",
                tphone: "",
                temail: "",
                tintroduction: "",
                tmodtime: ""
            },
            form: {
                Tid: "",
                Tname: "",
                Tsex: "",
                Tposition: "",
                Tpassword: "",
                Tphone: "",
                Temail: "",
                Tintroduction: "",
                Tmodtime: ""
            },
            dialogTableVisible: false,
            dialogFormVisible: false,
            rules: {
                tsex: [{ required: true, message: "请输入性别", trigger: "blur" }],
                tphone: [{ required: true, message: "请输入联系方式", trigger: "blur" }],
                temail: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
                tpassword: [{ required: true, message: "请输入密码", trigger: "blur" }],
            },
            formLabelWidth: '120px',
        };
    },
    methods: {
        onSubmit() {
            // console.log(new Date().toLocaleString())
            // console.log(this.teacherInfo.sinstitution)
            this.$refs.teacherInfo.validate(valid => {
                if (valid) {
                    var obj = this.form;
                    obj.Tid = this.teacherInfo.tid;
                    obj.Tname = this.teacherInfo.tname;
                    obj.Tsex = this.teacherInfo.tsex;
                    obj.Tposition = this.teacherInfo.tposition;
                    obj.Temail = this.teacherInfo.temail;
                    obj.Tintroduction = this.teacherInfo.tintroduction;
                    obj.Tphone = this.teacherInfo.tphone;
                    obj.Tpassword = this.teacherInfo.tpassword;
                    obj.Tmodtime = new Date().toLocaleString();
                    var params = this.$qs.stringify(obj);
                    console.log(params)
                    this.axios
                        .post("teacher/updateTeacher", params)
                        .then(res => {
                            if (res.data.code == 200) {
                                this.$message.success("修改成功");
                            }
                        })
                        .catch((err) => {
                            console.log(err)
                            this.$message("接口异常，无法连接服务器")
                        }
                        )
                } else {
                    return false;
                }
            })
        },
        updateTeacherArray() {
            this.axios
                .get("teacher/getOneTeacher/" + this.$store.state.id)
                .then(res => {
                    if (res.data.code == 200) {
                        this.teacherInfo = res.data.data;
                        // console.log(this.teacherInfo)
                    }
                })
                .catch(err => {
                    console.log(err);
                    this.$message("无法连接到服务器，请检查接口")
                });
        }
    },
    mounted() {
        this.updateTeacherArray();
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