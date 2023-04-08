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
                    <el-menu-item index="/front/home">首页</el-menu-item>
                    <el-menu-item index="/front/video">视频学习</el-menu-item>
                    <el-menu-item index="/front/Clist">学生列表</el-menu-item>
                    <el-submenu index="2">
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
                    </el-submenu>
                </el-menu>
            </div>
            <div style="width: 200px">
                <div v-if="!corporation.username" style="text-align: right; padding-right: 30px">
                    <el-button @click="$router.push('/Slogin')">登录</el-button>
                    <el-button @click="$router.push('/Sregister')">注册</el-button>
                </div>
                <div v-else>
                    <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
                        <div style="display: inline-block">
                            <img :src="corporation.avatarUrl" alt=""
                                style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
                            <span>{{ corporation.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
                        </div>
                        <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
                            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                                <router-link to="/front/password">修改密码</router-link>
                            </el-dropdown-item>
                            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                                <router-link to="/front/Cperson">个人信息</router-link>
                            </el-dropdown-item>
                            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                                <span style="text-decoration: none" @click="back">退出</span>
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </div>
        </div>

        <div style="width: 1000px; margin: 0 auto">
            <router-view @refreshcorporation="getcorporation" />
        </div>
    </div>
</template>

<script>
export default {
    name: "Front",
    data() {
        return {
            corporation: localStorage.getItem("corporation") ? JSON.parse(localStorage.getItem("corporation")) : {}
        }
    },
    created() {

    },
    methods: {
        back() {
            this.$router.push({ name: 'Selection' });

        },
        getcorporation() {
            let username = localStorage.getItem("corporation") ? JSON.parse(localStorage.getItem("corporation")).username : ""
            if (username) {
                // 从后台获取User数据
                this.request.get("/corporation/username/" + username).then(res => {
                    // 重新赋值后台的最新User数据
                    this.corporation = res.data
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
