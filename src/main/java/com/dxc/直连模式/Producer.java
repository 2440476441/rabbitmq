package com.dxc.直连模式;

import com.dxc.util.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 权计超
 * Company DXC.technology
 * @ClassName publicher
 * @CreateTime 2021-10-28 14:55
 * @Version 1.0
 * @Description: 消息生产者
 */
public class Producer {
    @Test
    //生产消息
    public void sendMessage() throws IOException, TimeoutException {
        Connection connection = RabbitUtil.getConnection();
        //获取连接通道
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        /**
         * 参数1：队列名称 如队列不存在自动创建
         * 参数2：用来定义队列特性是否要持久化 true持久化队列   false不持久化
         * 参数3；是否独占队列 true独占队列   false不独占队列
         * 参数4：是否在消费完成后自动删除队列   ture自动删除  false不自动删除
         * */
        channel.queueDeclare("hello",false,false,false,null);
        //发布消息
        /**
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：传递消息额外设置
         * 参数4：消息的具体内容
         * */
        channel.basicPublish("","hello",null,"天王盖地虎".getBytes());
        //关闭通道
        channel.close();
        //关闭连接
        connection.close();
    }
}
