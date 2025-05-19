<script setup lang="ts">
import GlobalHeader from '@/components/GlobalHeader.vue'
</script>

<template>
<div id="basicLayout">
  <a-layout style="min-height: 100vh">
    <a-layout-header class="header">
      <GlobalHeader />
    </a-layout-header>

    <a-layout-content class="content">
      <router-view />
    </a-layout-content>

    <a-layout-footer class="footer">
      <a href="https://beian.miit.gov.cn/" target="_blank">浙ICP备2025170317号-1</a>
      <br />
      <img src="../assets/备案图标.png" alt="备案图标" id="beian" />
      <a
        href="https://beian.mps.gov.cn/#/query/webSearch?code=34011102003716"
        rel="noreferrer"
        target="_blank"
        >皖公网安备34011102003716号</a
      >
    </a-layout-footer>
  </a-layout>
</div>
</template>

<style scoped lang="less">
#basicLayout {
  .header {
    padding-inline: 20px;
    background: white;
    color: unset;
    margin-bottom: 16px;
  }

  .content {
    padding: 20px;
    background: linear-gradient(to right, #fefefe, #fff);
    margin-bottom: 28px;
  }

  .footer {
    background: #efefef;
    padding: 16px;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    text-align: center;

    #beian {
      width: 16px;
      margin: 0 5px;
      display: inline;
    }
  }
}
</style>
