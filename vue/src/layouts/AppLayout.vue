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
                    <el-menu-item index="/video">视频学习</el-menu-item>
                    <el-menu-item index="/jobs">职位列表</el-menu-item>
                    <el-menu-item index="/received">
                        消息
                    </el-menu-item>
                    <!-- <el-submenu index="2">
                        <template slot="title">我的内容</template>
                        <el-menu-item index="/front/item1">选项1</el-menu-item>
                        <el-menu-item index="2-2">选项2</el-menu-item>
                        <el-menu-item index="2-3">选项3</el-menu-item>
                        <el-submenu index="2-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="2-4-1">选项1</el-menu-item>
                            <el-menu-item index="2-4-2">选项2</el-menu-item>
                            <el-menu-item index="2-4-3">选项3</el-menu-item>
                        </el-submenu>
                    </el-submenu> -->
                </el-menu>
            </div>
            <div style="width: 200px">
                <div v-if="!user" style="text-align: right; padding-right: 30px">
                    <el-button @click="$router.push('/login')">登录</el-button>
                    <el-button @click="$router.push('/register')">注册</el-button>
                </div>
                <div v-else>
                    <el-dropdown @command="onCommand" style="width: 70px;">

                        <el-button type="primary">
                            {{ user.nickname }}
                        </el-button>

                        <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center;">
                            <el-dropdown-item command="detail">
                                详细信息
                            </el-dropdown-item>
                            <el-dropdown-item command="modify-password">
                                修改密码
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
        back() {
            this.$router.push({ name: 'Selection' });

        },
        onCommand(command) {
            switch (command) {
                case "detail":
                    this.$router.push("/Person")
                    break;
                case "modify-password":
                    this.$router.push("/Person")
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
