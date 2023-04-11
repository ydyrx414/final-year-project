<template>
    <div class="wrapper">
        <div
            style="margin: 200px auto; background-color: #fff; width: 350px; height: 280px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>-登录-</b></div>
            <el-form :model="user" :rules="rules" ref="userForm">
                <el-form-item prop="username">
                    <el-input size="medium" prefix-icon="el-icon-user" v-model="user.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input size="medium" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
                </el-form-item>
            </el-form>
            <el-button style="width:100%" type="primary" @click="login">登录</el-button>
            <br /> <br />
            <el-button type="text" style="width:50%;margin-left: auto;
                    margin-right: auto;
                    display: block;" @click="$router.push('/register')">没有账号？注册！</el-button>
            <el-button type="text" style="width:50%;margin-left: auto;
                        margin-right: auto;
                        display: block;" @click="$router.push('/')">返回主页</el-button>
        </div>
    </div>
</template>

<script>
import { setLoginResult } from "../utils/user"
export default {
    name: "Login",
    data() {
        return {
            user: {},
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
            }

        }
    },

    methods: {
        async login() {
            try {
                const valid = await this.$refs['userForm'].validate();
                if (valid) { // 表单校验合法
                    const res = await this.request.post("/user/login", this.user);
                    setLoginResult(res.data)
                    const type = res.data.user.type
                    switch (type) {
                        case 0:
                            this.$router.push("/admin")
                            break;
                        case 1:
                        case 2:
                            this.$router.push("/home")
                            break;
                    }
                    if (res.code === '200') {
                        this.$notify.success({
                            title: "登录成功"
                        })
                    }
                }
            } catch (error) {
                console.error(error); // 输出错误信息以便调试
                this.$notify.error({
                    title: "登录失败,用户名或密码错误"
                })
            }


        }
    }
}
</script>

<style scoped>
.wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
    overflow: hidden;
}
</style>
