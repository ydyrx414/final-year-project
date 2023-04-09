<template>
    <div>
        <div v-for="article in data">
            <h3>
                {{ article.title }}
            </h3>
            <p>
                {{ article.content }}
            </p>
        </div>
    </div>
</template>
<script setup>
const data = [
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
    { title: "aaa", content: "abc" },
]
</script>