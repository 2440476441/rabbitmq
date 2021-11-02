package com.dxc.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 权计超
 * Company DXC.technology
 * @ClassName RabbitUtil
 * @CreateTime 2021-10-28 15:59
 * @Version 1.0
 * @Description: rabbitmq工具类
 */
public class RabbitUtil {
    private RabbitUtil(){};

    private static ConnectionFactory connectionFactory;
    static {
        //创建连接mq的连接工厂对象
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("47.100.42.118");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/rabbit");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("123456");
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        //利用工厂获取连接对象
        try {
            return connectionFactory.newConnection();
        }catch (IOException e){
            System.out.println("获取连接失败：");
            e.printStackTrace();
        }catch (TimeoutException e){
            System.out.println("获取连接失败：");
            e.printStackTrace();
        }
        return null;
    }
}
