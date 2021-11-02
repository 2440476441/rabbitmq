package com.dxc.fanout;

import com.dxc.util.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 权计超
 * Company DXC.technology
 * @ClassName Producer
 * @CreateTime 2021-11-01 11:56
 * @Version 1.0
 * @Description: 生产者
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        //将通道绑定交换机
        //参数1：交换机名称
        //参数2：交换机类型   fanout 广播类型
        channel.exchangeDeclare("regist","fanout");
        //发送消息
        channel.basicPublish("regist","",null,"一条广播消息".getBytes());
        channel.close();
        connection.close();
    }
}