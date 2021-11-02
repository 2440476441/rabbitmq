package com.dxc.routing;

import com.dxc.util.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 权计超
 * Company DXC.technology
 * @ClassName Producer
 * @CreateTime 2021-11-01 14:25
 * @Version 1.0
 * @Description: 生产者
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机
        //参数1：交换机名称
        //参数2：交换机类型   direct：路由模式
        channel.exchangeDeclare("logs","direct");
        //发送消息
        String routingKey = "info";
        channel.basicPublish("logs",routingKey,null,"一拳超人".getBytes());
        channel.close();
        connection.close();
    }
}
