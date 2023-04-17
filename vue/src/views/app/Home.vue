<template>
    <div>
        <div style="margin: 10px 0">
            <el-carousel height="800px" :interval="10000">
                <el-carousel-item v-for="item in imgs" :key="item">
                    <img :src="item" alt="" style="width: 100%">
                </el-carousel-item>
            </el-carousel>
        </div>

        <div style="margin: 10px 0">
            <el-row :gutter="10">
                <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px">

                    <div style="border: 1px solid #ccc; padding-bottom: 10px">
                        <img :src="item.url" alt="" style="width: 100%">
                        <div style="color: #666; padding: 10px">{{ item.name }}</div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
export default {
    name: "FrontHome",
    data() {
        return {
            imgs: [
                'http://www.mohrss.gov.cn/SYrlzyhshbzb/dongtaixinwen/buneiyaowen/rsxw/202206/W020220627532318436375.jpg',
                'http://www.mohrss.gov.cn/SYrlzyhshbzb/dongtaixinwen/buneiyaowen/rsxw/202206/W020220627532318418202.jpg',
                'http://www.mohrss.gov.cn/SYrlzyhshbzb/dongtaixinwen/buneiyaowen/rsxw/202206/W020220627532318406408.jpg'
            ],
            files: []
        }
    },
    created() {
        
    },
    methods: {

    }
}
</script>

<style></style>
