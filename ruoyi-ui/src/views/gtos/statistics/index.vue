<template>
<div>
  <div class="user-msg">
    <span>代理号： <strong>{{ userInfo.invite_code }}</strong></span>
    <span v-if="userInfo && userInfo.pay_rate">佣金比例：<strong> {{ parseFloat(userInfo.pay_rate * 100).toFixed(2) }}%</strong></span>
    <span>邀请链接： <strong>https://gtosolver.cn/pc-home/{{ userInfo.invite_code }}</strong></span>
  </div>
  <el-row :gutter="40" class="panel-group" v-if="parseInt(isPermission) === 1">
    <el-col :xs="6" :sm="6" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="peoples" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            总用户数
          </div>
          <count-to :start-val="0" :end-val="userInfo.total_user_count" :duration="1000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="6" :sm="6" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            收益（未体现）
          </div>
          <count-to :start-val="0" :end-val="parseFloat(userInfo.unpay) > 0 ? parseFloat(userInfo.unpay / 100) : 0" :duration="1000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="6" :sm="6" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            本月新增付费金额
          </div>
          <count-to :start-val="0" :end-val="parseFloat(userInfo.month_new_pay) > 0 ? parseFloat(userInfo.month_new_pay / 100) : 0" :duration="1000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="6" :sm="6" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            历史付费金额
          </div>
          <count-to :start-val="0" :end-val="parseFloat(userInfo.total_pay) > 0 ?  parseFloat(userInfo.total_pay / 100) : 0" :duration="1000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
  <el-row :gutter="40" class="panel-group" v-if="parseInt(isPermission) === 2">
    <el-col :xs="8" :sm="8" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="peoples" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            总用户数
          </div>
          <count-to :start-val="0" :end-val="userInfo.total_user_count" :duration="1000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="8" :sm="8" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="peoples" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            支付用户数量
          </div>
          <count-to :start-val="0" :end-val="parseInt(userInfo.pay_user_count)" :duration="1000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="8" :sm="8" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            历史付费金额
          </div>
          <count-to :start-val="0" :end-val="parseFloat(userInfo.total_pay) > 0 ? parseFloat(userInfo.total_pay / 100) : 0" :duration="1000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
  <el-row>
    <el-card style="height:470px;width: 98%;margin: 0 0 12px 12px;">
      <!--        <div style="margin: 15px 15px 30px 15px">-->
      <!--          <span style="font-size: 14px;color: #606266;">年份：</span>-->
      <!--          <el-select size="small" v-model="chartValue" @change="changeChart">-->
      <!--&lt;!&ndash;            <el-option v-for="item in chartArr" :key="item.value" :value="item.value" :label="item.label"></el-option>&ndash;&gt;-->
      <!--          </el-select>-->
      <!--          -->
      <!--        </div>-->
      <!--        <div class="block">-->
      <!--          <span class="demonstration">日期</span>-->
      <!--          <el-date-picker-->
      <!--            v-model="value1"-->
      <!--            type="datetimerange"-->
      <!--            range-separator="至"-->
      <!--            start-placeholder="开始日期"-->
      <!--            end-placeholder="结束日期">-->
      <!--          </el-date-picker>-->
      <!--        </div>-->
      <div class="block">
        <span class="demonstration">日期:</span>&nbsp;
        <el-date-picker
          v-model="value2"
          type="date"
          placeholder="选择日期">
        </el-date-picker>
      </div>
      <div style="margin-top: -15px;height: 430px;" v-if="showChart">
        <ve-line :data="lineData2" ref="chart" :width="lineWidth" :height="lineHeight"></ve-line>
      </div>
      <div style="text-align: center;line-height: 335px;" v-else>
        <span style="color: gray">暂无数据</span>
      </div>
    </el-card>
  </el-row>
</div>
</template>

<script>
import CountTo from 'vue-count-to'
import { getOperate } from '@/api/gtos/statistics'
export default {
  components: {
    CountTo
  },
  data() {
    return {
      used_count: 0,
      moneys: 0,
      isPermission: 2,
      userInfo: {
        "invite_code": "",
        "month_new_pay": 0,
        "pay_rate": '',
        "total_pay": 0,
        "total_user_count": 0,
        "unpay": 0
      },
      showChart: true,
      lineHeight: '430px',
      lineWidth: '100%',
      chartValue: 2,
      chartArr: [{
        label: '2020',
        value: 1
      }, {
        label: '2024',
        value: 2
      }],
      value2: '',
      value1: [new Date(2024, 1, 10, 10, 10), new Date(2024, 6, 11, 10, 10)],
      lineData: {
        columns: ['时间', '注册人数'],
        rows: [
          { '时间': '一月', '注册人数': 263 },
          { '时间': '二月', '注册人数': 356 },
          { '时间': '三月', '注册人数': 457 },
          { '时间': '四月', '注册人数': 365 },
          { '时间': '五月', '注册人数': 689 },
          { '时间': '六月', '注册人数': 235 },
        ]
      },
      lineData2: {
        columns: ['时间', '访问量'],
        rows: [
          { '时间': '0', '访问量': 2 },
          { '时间': '1', '访问量': 4 },
          { '时间': '2', '访问量': 5 },
          { '时间': '3', '访问量': 7 },
          { '时间': '4', '访问量': 3 },
          { '时间': '5', '访问量': 5 },
          { '时间': '6', '访问量': 34 },
          { '时间': '7', '访问量': 45 },
          { '时间': '8', '访问量': 47 },
          { '时间': '9', '访问量': 48 },
          { '时间': '10', '访问量': 57 },
          { '时间': '11', '访问量': 57 },
          { '时间': '12', '访问量': 68 },
          { '时间': '13', '访问量': 80 },
          { '时间': '14', '访问量': 80 },
          { '时间': '15', '访问量': 90 },
          { '时间': '16', '访问量': 212 },
          { '时间': '17', '访问量': 300 },
          { '时间': '18', '访问量': 432 },
          { '时间': '19', '访问量': 123 },
          { '时间': '20', '访问量': 100 },
          { '时间': '21', '访问量': 56 },
          { '时间': '22', '访问量': 32 },
          { '时间': '23', '访问量': 3 },
        ]
      }
    }
  },
  mounted() {
    if (sessionStorage.getItem('isPermission')) {
      this.isPermission = sessionStorage.getItem('isPermission')
    }
    // 获取代理商信息
    getOperate().then((res) => {
      console.log('reszz', res)
      this.userInfo = res.data
    })
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    }
  }
}
</script>

<style lang="scss" scoped>
.user-msg{
  width: 100%;
  overflow: hidden;
  margin-bottom: 20px;
  span{
    margin-right: 30px;
  }
}
.panel-group {
  .card-panel-col {
    margin-bottom: 32px;
  }
  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
