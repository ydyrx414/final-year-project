<template>
    <el-table :data="data" style="width: 100%">
        <el-table-column prop="studentName" label="学生" width="180">
        </el-table-column>
        <el-table-column prop="corporationName" label="公司" width="180">
        </el-table-column>
        <el-table-column prop="jobName" label="职位" width="180">
        </el-table-column>
        <el-table-column label="创建时间" width="180">
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
            pageSize: 10,
            corporationId: user.type == 2 ? user.id : void 0,
            studentId: user.type == 1 ? user.id : void 0,
        }
    })
    data.value = res.data.result;
}
onMounted(update)
</script>