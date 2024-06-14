<template>
<div>
  <h1 style="display: block;text-align: center;margin-top: 200px">欢迎进入GTOS后台管理系统</h1>
</div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.7"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">

</style>

