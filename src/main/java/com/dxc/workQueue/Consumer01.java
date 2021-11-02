package com.dxc.workQueue;

import com.dxc.util.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 权计超
 * Company DXC.technology
 * @ClassName Consumer
 * @CreateTime 2021-10-29 11:15
 * @Version 1.0
 * @Description: 消费者
 */
public class Consumer01 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接对象
        Connection connection = RabbitUtil.getConnection();
        //创建通道
        final Channel channel = connection.createChannel();
        //绑定通道对象
        channel.queueDeclare("work",false,false,false,null);
        //消费消息
        //参数1：消费那个队列的消息 队列名称
        //参数2：开始消息的自动确认机制
        //参数3：消费时的回调接口
//        channel.basicQos(1);
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            //最后一个参数：消息队列中取出来的消息
            public void handleDelivery(String consumerTag, Envelope envelop, AMQP.BasicProperties properties, byte[] body) throws IOException{
                /**
                 * 手动确认
                 * 参数1：手动确认消息标识
                 * 参数2：false每次确认一个
                 * */
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(body));
//                channel.basicAck(envelop.getDeliveryTag(),false);

            }
        });
    }
}
