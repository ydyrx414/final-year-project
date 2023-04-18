<template>
    <div>
        <!--    头部-->
        <div style="display: flex; height: 60px; line-height: 60px; border-bottom: 1px solid #eee">
            <div style="width: 300px; display: flex; padding-left: 30px">
                <div style="width: 60px">
                    <img src="" alt="" style="width: 50px; position: relative; top: 5px; right: 0">
                </div>
                <div style="flex: 1">欢迎来到就业体验系统</div>
            </div>
            <div style="flex: 1">

                <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" router>
                    <el-menu-item index="/home">首页</el-menu-item>
                    <el-menu-item index="/article">就业体验相关介绍</el-menu-item>
                    <el-menu-item v-if="user.type === 1" index="/test">职业性格测试</el-menu-item>
                    <el-menu-item v-if="user" index="/jobs">职位列表</el-menu-item>
                    <el-menu-item v-if="user" index="/received"> 消息</el-menu-item>
                </el-menu>
            </div>
            <div style="width: 200px">
                <div v-if="!user" style="text-align: right; padding-right: 30px">
                    <el-button @click="$router.push('/login')">登录</el-button>
                    <el-button @click="$router.push('/register')">注册</el-button>
                </div>
                <div v-else>
                    <el-dropdown trigger="click" @command="onCommand" style="width: 70px;">

                        <el-button type="primary">
                            {{ user.nickname }}
                        </el-button>

                        <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center;">
                            <el-dropdown-item command="detail">
                                个人信息
                            </el-dropdown-item>
                            <el-dropdown-item command="exit">
                                退出
                            </el-dropdown-item>
                        </el-dropdown-menu>

                    </el-dropdown>
                </div>
            </div>
        </div>

        <div style="width: 1000px; margin: 0 auto">
            <router-view @refreshstudent="getstudent" />
        </div>
    </div>
</template>

<script>
import { getLoginResult, setLoginResult } from "../utils/user"
export default {
    name: "Front",
    data() {
        return {
            user: getLoginResult()?.user
        }
    },
    created() {

    },
    methods: {
        onCommand(command) {
            switch (command) {
                case "detail":
                    this.$router.push("/profile")
                    break;
                case "exit":
                    setLoginResult(null)
                    this.$router.push("/login")
                    break;
            }
        },
        getstudent() {
            let username = localStorage.getItem("student") ? JSON.parse(localStorage.getItem("student")).username : ""
            if (username) {
                // 从后台获取User数据
                this.request.get("/student/username/" + username).then(res => {
                    // 重新赋值后台的最新User数据
                    this.student = res.data
                })
            }
        }
    }
}
</script>

<style>
.item {
    display: inline-block;
    width: 100px;

    text-align: center;
    cursor: pointer
}

.item a {
    color: #1E90FF;
}

.item:hover {
    background-color: LightPink;
}
</style>
