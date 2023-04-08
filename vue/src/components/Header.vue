<template>
  <div style=" line-height: 60px; display: flex;">
    <div style="flex: 1">
      <span :class="collapseBtnClass" style="cursor: pointer font-size: 18px;" @click="collapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px;">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ $route.meta.title }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>


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
</template>

<script>
import { getLoginResult, setLoginResult } from '../utils/user';

export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Function,
  },

  data() {
    return {
      user: getLoginResult().user
    }
  },
  methods: {
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
    }
  }


}

</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.demonstration {
  display: block;
  color: #8492a6;
  font-size: 14px;
  margin-bottom: 20px;
}
</style>