<template>
    <div class="wrapper">
        <div
            style="margin: 150px auto; background-color: #fff; width: 350px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注 册</b></div>
            <el-form :model="user" :rules="rules" ref="userForm">
                <el-form-item label="身份">
                    <el-radio-group v-model="user.type">
                        <el-radio label="0">管理员</el-radio>
                        <el-radio label="1">学生</el-radio>
                        <el-radio label="2">企业</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item v-if="user.type != 2" prop="nickName">
                    <el-input placeholder="请输入姓名" size="medium" prefix-icon="el-icon-user"
                        v-model="user.nickName"></el-input>
                </el-form-item>
                <el-form-item v-else prop="nickName">
                    <el-input placeholder="请输入企业名" size="medium" prefix-icon="el-icon-user"
                        v-model="user.nickName"></el-input>
                </el-form-item>

                <el-form-item prop="username">
                    <el-input placeholder="请输入账号" size="medium" prefix-icon="el-icon-user"
                        v-model="user.username"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input placeholder="请输入密码" size="medium" prefix-icon="el-icon-lock" show-password
                        v-model="user.password"></el-input>
                </el-form-item>
                <el-form-item prop="confirmPassword">
                    <el-input placeholder="请确认密码" size="medium" prefix-icon="el-icon-lock" show-password
                        v-model="user.confirmPassword"></el-input>
                </el-form-item>
            </el-form>
            <el-button style="width:100%" type="primary" @click="login">登录</el-button>
            <br />     <br />
            <el-button type="danger" style="width:100%;margin-left: auto;
                    margin-right: auto;
                    display: block;" @click="$router.push('/login')">返回登录</el-button>
                         <br />
            <el-button type="text" style="width:50%;margin-left: auto;
                        margin-right: auto;
                        display: block;" @click="$router.push('/')">返回主页</el-button>
        </div>
    </div>
</template>

<script>
import { setLoginResult } from '../utils/user';

export default {
    name: "Login",
    data() {
        return {
            user: {
                type: "1"
            },
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
                confirmPassword: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
            }
        }
    },
    methods: {
        login() {
            this.$refs['userForm'].validate(async (valid) => {
                if (valid) {  // 表单校验合法
                    if (this.user.password !== this.user.confirmPassword) {
                        this.$message.error("两次输入的密码不一致")
                        return false
                    }
                    const res = await this.request.post("/user/register", this.user)
                    if (res.code === '200') {
                        setLoginResult(res.data)
                        const type = res.data.user.type;
                        switch (type) {
                            case 0:
                                this.$router.push("/admin/home")
                                break;
                            case 1:
                            case 2:
                                this.$router.push("/home")
                                break;
                        }
                        this.$notify.success({
                            title: "注册成功"
                        })
                    } else {
                        this.$notify.error({
                            title:"注册失败",
                            message:res.msg
                        })
                    }
                }
            });
        }
    }
}
</script>

<style>
.wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
    overflow: hidden;
}
</style>
