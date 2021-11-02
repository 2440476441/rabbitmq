package com.dxc.workQueue;

import com.dxc.util.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 权计超
 * Company DXC.technology
 * @ClassName Producer
 * @CreateTime 2021-10-29 14:27
 * @Version 1.0
 * @Description:
 */
public class Producer {
    @Test
    public void product()throws IOException, TimeoutException {
        for (int i=1;i<=100;i++){
            Connection connection = RabbitUtil.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("work",false,false,false,null);
            channel.basicPublish("","work",null,(i+"只🐏").getBytes());
            channel.close();
            connection.close();
        }
    }
}
