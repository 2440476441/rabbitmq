package com.dxc.routing;

import com.dxc.util.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 权计超
 * Company DXC.technology
 * @ClassName Consumer01
 * @CreateTime 2021-11-01 14:39
 * @Version 1.0
 * @Description: 消费者1
 */
public class Consumer01 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        //通道声明交换机以及交换机类型
        channel.exchangeDeclare("logs","direct");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //基于routingKey绑定队列和交换机
        channel.queueBind(queue,"logs","error");
        //获取消费的消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new java.lang.String(body));
            }
        });
    }
}
