<script setup lang="ts">
import { message } from 'ant-design-vue';

const description = 'This is a description.';

const iKnow = () => {
  message.error('网站很脆弱，没事别乱按');
}
</script>

<template>
  <div id="about">
    <a-result status="404" title="该项目正在努力开发中！！！" sub-title="可联系开发者合作">
      <template #extra>
        <a-button type="primary" @click="iKnow">I Know</a-button>
      </template>
    </a-result>


    <a-steps
      :current="1"
      :items="[
      {
        title: 'Finished',
        description,
      },
      {
        title: 'In Progress',
        description,
        subTitle: 'Left 00:00:08',
      },
      {
        title: 'Waiting',
        description,
      },
    ]"
    ></a-steps>
  </div>
</template>

<style scoped></style>
