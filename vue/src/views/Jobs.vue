<template>
    <div>
        <div style="margin;: 10px 0">

            <el-input style="width: 200px" placeholder="请输入职位名" suffix-icon="el-icon-search" class="ml-5"
                v-model="name"></el-input>
            <el-input style="width: 200px" placeholder="请输入提供体验时间" suffix-icon="el-icon-search" class="ml-5"
                v-model="expTime"></el-input>
            <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-search" class="ml-5"
                v-model="address"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0;">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info"
                icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch">
                <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
        </div>

        <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="id" label="id" width="150"></el-table-column>
            <el-table-column prop="name" label="职位名称" width="150"></el-table-column>
            <el-table-column prop="expTime" label="提供体验时间" width="180"></el-table-column>
                        <el-table-column prop="corporationName" label="公司"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
 
            <el-table-column label="操作" width="300" align="center">
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

        <el-dialog title="职位信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="120px">
                <el-form-item label="公司ID">
                    <el-input v-model="form.corporationId" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="职位名">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="体验时间">
                    <el-input v-model="form.expTime" autocomplete="off"></el-input>
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
import Request from "@/utils/request";
export default {
    name: "Jobs",
    data() {
        return {
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 5,
            name: "",
            time: "",
            form: {},
            dialogFormVisible: false,
            address: ""
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
                    address: this.address,
                    time: this.time
                }
            })
            // console.log(res.data.result)
            this.tableData = res.data.result
            this.total = res.data.total
        },

        save() {
            Request.post("/job/" + (this.form.id ?? ""), this.form).then(res => {
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
            Request.delete("/job/" + id).then(res => {
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
            Request.delete("/student/del/batch", { data: ids }).then(res => {
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

            this.name = ""
                this.expTime = ""
                this.address = ""
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