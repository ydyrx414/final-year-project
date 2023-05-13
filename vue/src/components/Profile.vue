<template>
  <div>
    <el-form :model="form" style="margin:8px">
      <el-form-item :label="user.type === 1 ? '姓名' : '名称'">
        <el-input v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="电子邮件">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="form.phone"></el-input>
      </el-form-item>

      <el-form-item label="地址" v-if="user.type === 2">
        <el-input v-model="form.address"></el-input>
      </el-form-item>
      <el-form-item label="意向" v-if="user.type === 1">
        <el-input v-model="form.intention"></el-input>
      </el-form-item>
      <el-form-item label="职业性格" v-if="user.type === 1">
        <el-input v-model="form.nature" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="专业" v-if="user.type === 1">
        <el-input v-model="form.subject"></el-input>
      </el-form-item>
    </el-form>
    <el-button @click="save">保存</el-button>
  </div>
</template>
<script setup>
import { onMounted, ref } from 'vue';
import { getLoginResult, setLoginResult } from '../utils/user';
import request from '../utils/request';

const user = getLoginResult().user
const form = ref({
  ...user,
  nature: "",
  address: "",
  intention: "",
  subject: ""
})

const update = async () => {
  const type = user.type;
  const token = getLoginResult().token;
  const latestUserInfo = (await request.get(`/user/${user.id}`)).data
  setLoginResult({
    token,
    user: latestUserInfo
  })
  form.value = {
    ...latestUserInfo,
    nature: "",
    address: "",
    intention: "",
    subject: ""
  }
  if (type === 1) {
    const res = await request.get(`/user/${user.id}/as-student`)
    form.value.nature = res.data.nature
    form.value.subject = res.data.subject
    form.value.intention = res.data.intention
  } else if (type === 2) {
    const res = await request.get(`/user/${user.id}/as-corp`)
    form.value.address = res.data.address
  }
}

// const router = useRouter()
const save = async () => {
  await request.post(`/user/${user.id}`, form.value)
  update()
  window.location.reload()
}
onMounted(update)
</script>
<style></style>