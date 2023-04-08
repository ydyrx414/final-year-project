<template>
    <el-card style="width: 500px;">
        <el-form label-width="80px" size="small">
            <el-form-item label="用户名">
                <el-input v-model="form.username" disabled autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="提供职位">
                <el-input v-model="form.job" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="体验时间">
                <el-input v-model="form.worktime" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="save">确 定</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>

export default {
    name: "Person",
    data() {
        return {
            form: {},
            corporation: localStorage.getItem("corporation") ? JSON.parse(localStorage.getItem("corporation")) : {}
        }

    },
    created() {
        this.request.get("/corporation/username/" + this.corporation.username).then(res => {
            if (res.code === '200') {
                this.form = res.data
            }
        })
    },

    methods: {
        save() {
            this.request.post("/corporation", this.form).then(res => {
                if (res) {
                    this.$message.success("保存成功")
                } else {
                    this.$message.error("保存失败")
                }
            })
        },
    }
}
</script>

<style>
.avatar-uploader {
    text-align: center;
    padding-bottom: 10px;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 138px;
    height: 138px;
    line-height: 138px;
    text-align: center;
}

.avatar {
    width: 138px;
    height: 138px;
    display: block;
}
</style>
