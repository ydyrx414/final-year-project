<template>
    <div class="quiz">
        <h1>职业性格测试</h1>
        <form>
            <div v-for="(question, index) in questions" :key="index">
                <h3>{{ question.title }}</h3>
                <el-radio-group v-model="answers[index]">
                    <el-radio v-for="(option, optionIndex) in question.options" :key="optionIndex" :label="option.value">
                        {{ option.label }}
                    </el-radio>
                </el-radio-group>
            </div>
            <!-- 将 @click.prevent 改为 @click ，因为不需要阻止默认行为 -->
            <el-button type="primary" @click="submit">提交</el-button>
            <el-button type="default" @click="reset">重置</el-button>
            <!-- 在此处添加“保存”按钮，并使用 v-if 条件渲染根据 showSaveButton 变量决定是否显示 -->
            <el-button type="primary" v-if="showSaveButton" @click="save">保存结果</el-button>
        </form>
        <!-- 在此处添加一个 div，用于展示测试结果 -->
        <div v-if="showResults">
            <h2>测试结果</h2>
            <p>{{ getResult() }}</p>
        </div>
    </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { getLoginResult, setLoginResult } from '../utils/user';
import request from '../utils/request';
const user = getLoginResult().user
const form = ref({
    ...user,
    nature: "",

})

const update = async () => {
    const token = getLoginResult().token;
    const latestUserInfo = (await request.get(`/user/${user.id}`)).data
    setLoginResult({
        token,
        user: latestUserInfo
    })
    form.value = {
        ...latestUserInfo,
        nature: "",
    }
  
    const res = await request.get(`/user/${user.id}/as-student`)
    form.value.nature = res.data.nature
    
}
export default {
    data() {
        return {
            questions: [
                {
                    title: "你更喜欢哪种工作环境？",
                    options: [
                        { label: "独处的空间", value: "A" },
                        { label: "开放的工作区", value: "B" },
                        { label: "在实际项目地点的空间", value: "C" },
                        { label: "远程办公", value: "D" },
                    ],
                },
                {
                    title: "你更擅长则呢么解决任务？",
                    options: [
                        { label: "分析和解决问题", value: "A" },
                        { label: "与人合作", value: "B" },
                        { label: "通过网络查找", value: "C" },
                        { label: "自身能力解决", value: "D" },
                    ],
                },
                {
                    title: "在处理事情时，你更倾向于：",
                    options: [
                        { label: "依赖自己的直觉和想法", value: "A" },
                        { label: "听取别人的建议和意见", value: "B" },
                        { label: "遵循规则和流程", value: "C" },
                        { label: "带着创新和新思路尝试", value: "D" }
                    ]
                },
                {
                    title: "当你需要做一个决策时：",
                    options: [
                        { label: "立即做出决策，不假思索", value: "A" },
                        { label: "先仔细分析和考虑", value: "B" },
                        { label: "听取其他人的意见和建议", value: "C" },
                        { label: "尝试不同的解决方案并选择最好的", value: "D" }
                    ]
                },
                {
                    title: "对于面对困难或挑战，你的态度是：",
                    options: [
                        { label: "迎接挑战并努力克服问题", value: "A" },
                        { label: "寻找帮助和支持", value: "B" },
                        { label: "收缩自己，避免风险", value: "C" },
                        { label: "寻求新的方法和解决方案", value: "D" }
                    ]
                },
                {
                    title: "在与他人交往时，你更倾向于：",
                    options: [
                        { label: "保持独立性和距离感", value: "A" },
                        { label: "参与群体活动和社交", value: "B" },
                        { label: "关心他人的情感和感受", value: "C" },
                        { label: "强调理性和合理性", value: "D" }
                    ]
                },
                {
                    title: "当你面临一个挑战时，你会：",
                    options: [
                        { label: "感到兴奋和充满动力", value: "A" },
                        { label: "先考虑后果和风险然后才采取行动", value: "B" },
                        { label: "寻求他人的支持和帮助", value: "C" },
                        { label: "尝试新的方法来解决问题", value: "D" }
                    ]
                },
                {
                    title: "当你与他人合作时，你通常：",
                    options: [
                        { label: "给出自己的想法和建议", value: "A" },
                        { label: "等待别人先提供意见和想法", value: "B" },
                        { label: "避免引起不必要的争论和冲突", value: "C" },
                        { label: "仔细倾听并且尝试理解对方的观点", value: "D" }
                    ]
                },
                {
                    title: "在选择工作时，你认为什么最重要？",
                    options: [
                        { label: "工作内容和挑战性", value: "A" },
                        { label: "薪资和福利", value: "B" },
                        { label: "工作与个人生活之间的平衡", value: "C" },
                        { label: "工作环境和公司文化", value: "D" }
                    ]
                },
                {
                    title: "当你需要做出一个决定时，你会：",
                    options: [
                        { label: "直接做出决定，不考虑太多", value: "A" },
                        { label: "先收集并分析信息然后再做出决定", value: "B" },
                        { label: "征询他人的意见，然后做出决定", value: "C" },
                        { label: "尝试多种解决方案并选择最佳的一个", value: "D" }
                    ]
                },
                {
                    title: "你认为你的工作应该：",
                    options: [
                        { label: "与你的价值观和信仰相符合", value: "A" },
                        { label: "能够提供稳定的收入和职业发展机会", value: "B" },
                        { label: "允许你有时间进行个人爱好和兴趣", value: "C" },
                        { label: "满足你对挑战性和成就感的需求", value: "D" }
                    ]
                }

            ],
            answers: [],
            showResults: false,
            showSaveButton: false, // 添加“保存”按钮的状态变量
        };
    },
    methods: {
        reset() {
            this.answers = [];
            this.showResults = false;
            // 在重置时将 showSaveButton 变量重置为 false
            this.showSaveButton = false;
        },
        submit() {
            if (this.answers.length === this.questions.length) {
                this.showResults = true;
                // 当用户提交测试结果时，将 showSaveButton 变量设置为 true，以显示“保存”按钮
                this.showSaveButton = true;
            }
        },
        getResult() {
            const counts = {
                A: 0,
                B: 0,
                C: 0,
                D: 0
            };

            // 统计用户选择的答案
            for (let i = 0; i < this.answers.length; i++) {
                counts[this.answers[i]]++;
            }

            // 根据选择的答案计算得分
            const scores = {
                "艺术类": counts.A + counts.C,
                "社交类": counts.B + counts.D,
                "实干类": counts.A + counts.B,
                "研究类": counts.C + counts.D
            };

            // 找出最高得分对应的职业性格
            let maxScore = 0;
            let result = "";
            for (const key in scores) {
                if (scores[key] > maxScore) {
                    maxScore = scores[key];
                    result = key;
                }
            }

            // 返回测试结果
            return `${result}`;
        },
        save() {
            form.value.nature = this.getResult();
            request.post(`/user/${user.id}`, form.value)
            alert("测试结果已保存！");
        },

    },
};
</script>
