package com.atguigu.gulimall.order.service.impl;

import com.atguigu.gulimall.order.entity.OrderReturnReasonEntity;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.order.dao.OrderItemDao;
import com.atguigu.gulimall.order.entity.OrderItemEntity;
import com.atguigu.gulimall.order.service.OrderItemService;


@RabbitListener(queues = {"hello-java-queue"})
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * queues:声明需要监听的队列
     */
    @RabbitHandler
    public void receiveMessage(Message message, OrderReturnReasonEntity content, Channel channel) {
        byte[] body = message.getBody();
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println("接收到消息："+message);
        System.out.println("消息内容："+content);
        System.out.println("消息类型："+message.getClass());

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("deliveryTag==>"+deliveryTag);
        //签收货物
        try {
            if (deliveryTag%2==0) {
                //收货
                channel.basicAck(deliveryTag,false);
                System.out.println("签收了货物："+deliveryTag);
            } else {
                //退货： requeue=false 丢弃   requeue=true 发回服务器，服务器重新入队
                channel.basicNack(deliveryTag,false,false);
                System.out.println("没有签收货物："+deliveryTag);
            }
        } catch (Exception e) {

        }
    }

}