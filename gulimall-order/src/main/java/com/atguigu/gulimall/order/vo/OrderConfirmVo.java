package com.atguigu.gulimall.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//订单确认页需要的数据
//@Data
public class OrderConfirmVo {

    //收获地址：ums_member_receive_address
    @Setter
    @Getter
    List<MemberAddressVo> address;

    //所有选中的购物项
    @Setter
    @Getter
    List<OrderItemVo> items;

    //发票记录...

    //优惠券信息...
    @Setter
    @Getter
    Integer integration;

    @Setter
    @Getter
    Map<Long,Boolean> stocks;

    //防重令牌
    @Setter
    @Getter
    String orderToken;

    public Integer getCount() {
        Integer i = 0;
        if(items!=null) {
            for (OrderItemVo item : items) {
                i+=item.getCount();
            }
        }
        return i;
    }

//    BigDecimal total; //订单总额

    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if(items!=null) {
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum=sum.add(multiply);
            }
        }
        return sum;
    }

//    BigDecimal payPrice; //应付价格

    public BigDecimal getPayPrice() {
        return getTotal();
    }
}
