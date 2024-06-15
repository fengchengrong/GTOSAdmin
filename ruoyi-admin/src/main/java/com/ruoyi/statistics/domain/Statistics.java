package com.ruoyi.statistics.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class Statistics extends BaseEntity {

    private Integer total_user_count;

    private Integer pay_user_count;

    private Double total_pay;

    public Integer getTotal_user_count() {
        return total_user_count;
    }

    public void setTotal_user_count(Integer total_user_count) {
        this.total_user_count = total_user_count;
    }

    public Integer getPay_user_count() {
        return pay_user_count;
    }

    public void setPay_user_count(Integer pay_user_count) {
        this.pay_user_count = pay_user_count;
    }

    public Double getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(Double total_pay) {
        this.total_pay = total_pay;
    }
}
