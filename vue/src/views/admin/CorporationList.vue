<template>
    <div style="height: 100%;">
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入企业名称" suffix-icon="el-icon-search" v-model="nickname"></el-input>
            <el-input style="width: 200px" placeholder="请输入电话" suffix-icon="el-icon-search" class="ml-5"
                v-model="phone"></el-input>
            <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-search" class="ml-5"
                v-model="email"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <el-divider />
        <div style="margin: 10px 0;">
            <!-- <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button> -->
            <!-- <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info"
                icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch">
                <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm> -->
        </div>

        <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="id" label="id" width="150"></el-table-column>
            <el-table-column fixed="left" prop="nickname" label="名称" width="150"></el-table-column>
            <el-table-column prop="username" label="用户名" width="150"></el-table-column>
            <el-table-column prop="address" label="地址" width="120"></el-table-column>
            <el-table-column prop="phone" label="手机号码" width="150"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column fixed="right" label="操作" width="300" align="center">
                <template slot-scope="scope">
                    <el-button type="success" @click="handlEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info"
                        icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <div style="padding: 10px 0">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
                :page-sizes="[5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>

        </div>

        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="120px">
                <el-form-item label="用户名">
                    <el-input v-model="form.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="form.nickname" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="地址">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话号码">
                    <el-input v-model="form.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { setRoutes } from "@/router";
import Request from "@/utils/request";
export default {
    name: "Corporation",
    data() {
        return {
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 5,
            username: "",
            nickname: "",
            job: "",
            expTime: "",
            address: "",
            email: "",
            phone: "",
            dialogFormVisible: false,
            form: {},
            multipleSelection: [],
        }
    },
    created() {
        //请求分页查询数据
        this.load()
    },
    methods: {
        async load() {
            const res = await Request.get("/corporation/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    username: this.username,
                    nickname: this.nickname,
                    job: this.job,
                    expTime: this.expTime,
                    address: this.address,
                    email: this.email,
                    phone: this.phone
                }
            })
                this.tableData = res.data.result
                this.total = res.data.total

        },

        async save() {
            Request.post("/corporation", this.form).then(res => {
                if (res) {
                    this.$notify.success({
                        title: "保存成功"
                    })
                    this.dialogFormVisible = false
                    this.load()
                } else {
                    this.$message.error({
                        title: "保存失败"
                    })
                }
            })
        },

        del(id) {
            Request.delete("user/" + id).then(res => {
                if (res) {
                    this.$message.success("删除成功")
                    this.load()
                } else {
                    this.$message.error("删除失败")
                }
            })
        },


        handleSelectionChange(val) {
            console.log(val)
            this.multipleSelection = val
        },


        delBatch() {
            let ids = this.multipleSelection.map(v => v.id)   //把对象的数组变成纯id数组
            Request.delete("/corporation/del/batch", { data: ids }).then(res => {
                if (res) {
                    this.$message.success("批量删除成功")
                    this.load()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },


        handlEdit(row) {
            this.form = row
            this.dialogFormVisible = true
            this.form = Object.assign({}, row)
        },

        handleAdd() {
            this.dialogFormVisible = true
            this.form = {}
        },

        reset() {

            this.username = "",
                this.nickname = "",
                this.job = "",
                this.expTime = "",
                this.address = "",
                this.email = "",
                this.phone = "",
                this.load()
        },

        handleSizeChange(pageSize) {
            console.log(pageSize)
            this.pageSize = pageSize
            this.load()
        },

        handleCurrentChange(pageNum) {
            console.log(pageNum)
            this.pageNum = pageNum
            this.load()
        }
    }
}

</script>

<style scoped></style>