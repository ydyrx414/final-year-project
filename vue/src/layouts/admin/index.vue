<template>
  <div style="height: 100%;">
    <el-container style="height: 100vh">
      <el-aside width="sideWidth + 'px'"
        style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
        <Aside :isCollapse="isCollapse" :logoTexShow="logoTexShow"/>
      </el-aside>

      <el-container>
        <el-header style=" border-bottom: 1px solid #ccc;">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse"/>
        </el-header>

        <!-- 表示当前页面的子路由会在routerview里面展示 -->
        <el-main>
          <router-view/>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from "./Aside.vue";
import Header from "./Header.vue";



export default {
  name: 'Home',
  data() {
    return {
      
      msg: "hello yb",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTexShow: true

    }
  },

  components: {
    Aside,
    Header
  },

  methods: {
    collapse() {  //点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTexShow = false
      } else {  //展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTexShow = true
      }
    },
    
  }
}
</script>
