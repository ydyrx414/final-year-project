<template>
    <el-table :data="data">
        <el-table-column prop="studentName" label="学生" width="180">
        </el-table-column>
        <el-table-column prop="corporationName" label="公司" width="300">
        </el-table-column>
        <el-table-column fixed="left" prop="jobName" label="职位" width="200">
        </el-table-column>
        <el-table-column label="创建时间" width="200">
            <template #default="scope">
                {{ new Date(scope.row.createdTime).toLocaleString(void 0, {
                    hourCycle: "h23"
                }) }}
            </template>
        </el-table-column>
        <el-table-column label="类型" width="180">
            <template #default="scope">
                <el-tag type="primary" v-if="scope.row.type == 0">
                    学生申请
                </el-tag>
                <el-tag type="success" v-else-if="scope.row.type == 1">
                    企业邀请
                </el-tag>
            </template>
        </el-table-column>

        <el-table-column fixed="right" v-if="user.type === 0" label="操作" width="180">
                <template #default="scope">
                    <el-button @click="_delete(scope.row.id)" type="danger">删除</el-button>
                </template>
            </el-table-column>
    </el-table>
</template>
<script setup>
import { onMounted, ref } from 'vue';
import request from "../utils/request"
import { getLoginResult } from '../utils/user';
const data = ref([])
const user = getLoginResult().user;
const update = async () => {
    const res = await request.get("/application", {
        params: {
            pageNum: 1,
            pageSize: 1000,
            corporationId: user.type == 2 ? user.id : void 0,
            studentId: user.type == 1 ? user.id : void 0,
        }
    })
    data.value = res.data.result;
}
const _delete = async (id)=>{
    await request.delete(`/application/${id}`)
    update()
}
onMounted(update)
</script>