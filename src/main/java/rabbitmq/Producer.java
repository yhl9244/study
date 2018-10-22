package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/9/20.
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(ConnectionFactory.DEFAULT_USER);
        connectionFactory.setPassword(ConnectionFactory.DEFAULT_PASS);
        // 设置rabbitmq地址
        connectionFactory.setHost(ConnectionFactory.DEFAULT_HOST);
        // 获取连接
        Connection connection = connectionFactory.newConnection();
        // 获取信道
        Channel channel = connection.createChannel();
        // 声明交换器
        channel.exchangeDeclare("hello-exchange", BuiltinExchangeType.DIRECT,true);
        // 发布消息
        channel.basicPublish("hello-exchange","hello",null, "hello".getBytes());

        // 关闭连接
        channel.close();
        connection.close();

    }

}
