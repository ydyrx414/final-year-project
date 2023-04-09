<template>
  <el-menu :default-openeds="['1', '3']" style="height: 100%; overflow-x: hidden;" background-color="rgb(48,65,86)"
    text-color="#fff" active-text-color="#ffd04b" :collapse-transition="false" :collapse="isCollapse" router>
    <div style="height: 60px; line-height: 60px; text-align: center;">
      <img src="@/assets/logo.png" style="width: 20px; position: relative; top: 5px; margin-right: 5px;">
      <b style="color: white;" v-show="logoTexShow">就业体验系统</b>
    </div>
    <el-menu-item index="/admin/home">
      <template slot="title"><i class="el-icon-house"></i>
        <span slot="title">主页</span>
      </template>
    </el-menu-item>

    <el-submenu index="2">
      <template slot="title"><i class="el-icon-user"></i>
        <span slot="title">系统用户管理</span>
      </template>
      <el-menu-item index="/admin/user">
        <i class="el-icon-s-custom"></i>
        <span slot="title">系统用户名单管理</span>
      </el-menu-item>
    </el-submenu>



    <el-submenu index="3">
      <template slot="title"><i class="el-icon-user"></i>
        <span slot="title">学生管理</span>
      </template>
      <el-menu-item index="/admin/student">
        <i class="el-icon-s-custom"></i>
        <span slot="title">学生名单管理</span>
      </el-menu-item>
      <el-menu-item index="2-1">
        <i class="el-icon-house"></i>
        <span slot="title">学生意向管理</span>
      </el-menu-item>
    </el-submenu>

    <el-submenu index="3">
      <template slot="title"><i class="el-icon-suitcase"></i>
        <span slot="title">企业公司管理</span>
      </template>
      <el-menu-item index="/admin/corporation">
        <i class="el-icon-s-custom"></i>
        <span slot="title">公司名单管理</span>
      </el-menu-item>
      <el-menu-item index="2-1">
        <i class="el-icon-house"></i>
        <span slot="title">公司邀请管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/jobs">
        <i class="el-icon-house"></i>
        <span slot="title">公司职位管理</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTexShow: Boolean
  }
}

</script>

<style></style>