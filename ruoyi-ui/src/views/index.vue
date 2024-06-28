<template>
<div>
  <h1 style="display: block;text-align: center;margin-top: 200px">欢迎进入GTOS后台管理系统</h1>
  <h1 style="display: block;text-align: center;margin-top: 100px">今日{{ this.hour }}时主页: https://gtosolver.cn/的访问人数: {{ this.count }} 人</h1>
</div>
</template>

<script>
import {getVisitUserNum} from "@/api/gtos/statistics";
export default {
  name: "Index",
  data() {
    return {
      count: 0,
      hour: '',
      // 版本号
      version: "3.8.7"
    };
  },
  mounted() {
    getVisitUserNum().then((res) =>{
      if(res.msg){
        this.count = Number(res.msg.substring(3)=='null' ? 0 : res.msg.substring(3));
        this.hour = res.msg.substring(0,2);
      }
    })
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

