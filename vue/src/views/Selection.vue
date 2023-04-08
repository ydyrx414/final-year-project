<template>
    <div class="role-selection-page">
        <h1>请选择您的身份</h1>
        <div class="role-selectors">
            <el-radio-group v-model="selectedRole" size="large">
                <el-radio :label="'admin'"><i class="el-icon-user-solid"></i><br />管理员</el-radio>
                <el-radio :label="'student'"><i class="el-icon-s-custom"></i><br />学生</el-radio>
                <el-radio :label="'corporation'"><i class="el-icon-suitcase"></i><br />公司企业</el-radio>
            </el-radio-group>
            <el-button type="primary" @click="login" size="large">登录/注册</el-button>
            <el-button type="primary" @click="back" size="large">返回页面</el-button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'role-selection-page',
    data() {
        return {
            selectedRole: 'student',
        };
    },
    methods: {
        login() {
            switch (this.selectedRole) {
                case 'admin':
                    this.$router.push('/Login')
                    break;
                case 'student':
                    this.$router.push('/SLogin')
                    break;
                case 'corporation':
                    this.$router.push('/CLogin')
                    break;
                default:
                    break;
            }
        },
        back() {
            this.$router.push({ name: 'Front' });

        }
    },
};
</script>

<style scoped>
.role-selection-page {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

h1 {
    font-size: 36px;
    margin-bottom: 40px;
}

.role-selectors {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 50px;
    width: 400px;
}

.el-radio-button {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 120px;
    width: 100%;
    font-size: 20px;
}

.el-radio-button i {
    font-size: 40px;
    margin-bottom: 10px;
}

.el-button {
    font-size: 24px;
    width: 200px;
    height: 60px;
    margin-top: 30px;
}
.role-selection-page {
  background-color: #ffffff; /* 将原来的灰色改为白色 */
  /* 其他样式 */
}



</style>
