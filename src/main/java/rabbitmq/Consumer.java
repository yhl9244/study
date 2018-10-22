package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by suneee on 2018/9/20.
 */
public class Consumer {

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
        final Channel channel = connection.createChannel();
        // 声明交换器
        channel.exchangeDeclare("hello-exchange", BuiltinExchangeType.DIRECT,true);
        // 声明队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定队列
        channel.queueBind(queue,"hello-exchange","hello");

        while(true) {
            //消费消息
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queue, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey);
                    System.out.println("消费的内容类型：" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息体内容：");
                    String bodyStr = new String(body, "UTF-8");
                    System.out.println(bodyStr);

                }
            });
        }
    }
}
