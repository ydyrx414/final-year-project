<template>
    <div>
        <div style="margin;: 10px 0">
            <!-- <el-input style="width: 200px" placeholder="公司名" suffix-icon="el-icon-search" v-model="username"></el-input> -->
            <el-input style="width: 200px" placeholder="体验职位" suffix-icon="el-icon-search" class="ml-5"
                v-model="name"></el-input>
            <el-input style="width: 200px" placeholder="体验时间" suffix-icon="el-icon-search" class="ml-5"
                v-model="expTime"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        

        <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="id" label="id" width="150"></el-table-column>
            <el-table-column prop="name" label="职位名" width="150"></el-table-column>
            <el-table-column prop="expTime" label="体验时间" width="150"></el-table-column>
                     <el-table-column prop="address" label="地址" width="120"></el-table-column>
            <el-table-column prop="corporationName" label="公司名"></el-table-column>
            <el-table-column label="操作" width="300" align="center">
                <template slot-scope="scope">
                    <el-button type="success" @click="apply(scope.row)">意向申请 <i class="el-icon-edit"></i></el-button>
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
                <el-form-item label="职位">
                    <el-input v-model="form.job" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="体验时间">
                    <el-input v-model="form.worktime" autocomplete="off"></el-input>
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
            name: "",
            expTime: "",
            address: "",
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
            const res = await Request.get("/job", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    name: this.name,
                    expTime: this.expTime,
                    address: this.address,
                }
            });
            this.tableData = res.data.result
            this.total = res.data.total
        },

        save() {
            Request.post("/corporation", this.form).then(res => {
                if (res) {
                    this.$message.success("保存成功")
                    this.dialogFormVisible = false
                    this.load()
                } else {
                    this.$message.error("保存失败")
                }
            })
        },

        del(id) {
            Request.delete("corporation/" + id).then(res => {
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


        


        apply(row) {
            Request.post("")
        },


        reset() {

            this.name = "",
                this.address = "",
                this.expTime = "",
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