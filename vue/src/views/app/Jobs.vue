<template>
    <Jobs :view="type" />
</template>
<script setup>
import Jobs from '../../components/Jobs.vue';
import { getLoginResult } from '../../utils/user';
const type = getLoginResult().user.type;
</script>