package com.atguigu.gulimall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 封装订单提交的数据
 */
@Data
public class OrderSubmitVo {

    private Long addrId; //收货地址的id
    private Integer payType; //支付方式
    //无需提交需要购买的商品，去购物车再遍历一遍
    //优惠、发票信息
    private String orderToken; //防重令牌
    private BigDecimal payPrice; //应付价格（用于验证价格）
    private String node; //订单备注
    //用户相关信息直接去session中获取

}
