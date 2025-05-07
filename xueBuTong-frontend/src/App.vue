<script setup lang="ts">
// import { healthUsingGet } from '@/api/mainController.ts'
import {useLoginUserStore} from "@/stores/useLoginUserStore.ts";
import BasicLayout from '@/layouts/BasicLayout.vue'

const loginUserStore = useLoginUserStore()
loginUserStore.fetchLoginUser()

// healthUsingGet().then((res) => {
//   console.log(res)
// })
</script>

<template>
  <div id="app">
    <BasicLayout />
  </div>
</template>

<style scoped></style>
