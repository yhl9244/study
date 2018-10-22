package activemq.pc;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by suneee on 2018/9/25.
 */
public class Consumer2 {

    public static void main(String[] args) throws Exception {
        // 获取ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        // 获取连接
        Connection connection = factory.createConnection();
        connection.start();
        // 获取Session
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 消息队列  子类有两种: topic(适合发布订阅模式)/queue(适合点对点模式)
        Destination destination = session.createTopic("topic");

        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MyMessageListener());
    }
}
