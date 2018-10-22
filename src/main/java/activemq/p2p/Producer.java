package activemq.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by suneee on 2018/9/25.
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        // 获取ConnectionFactory
        javax.jms.ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        // 获取连接
        Connection connection = factory.createConnection();
        connection.start();
        // 获取Session
        /**
         * 参数1：是否支持事务 参数2：消息确认的方式:如果支持事务则忽略
         *
         * Session.AUTO_ACKNOWLEDGE：当客户成功的从receive方法返回的时候或者从MessageListener.onMessage()
         * 方法成功返回时，会话自动确认客户收到的消息
         *
         * Session.CLIENT_ACKNOWLEDGE：客户通过消息的acknowledge方法确认消息，需要注意这种情况会话
         * ，确认是会话层上进行，确认一个被消费的消息将自动确认所有已被会话消费的消息
         *
         * Session.DUPS_OK_ACKNOWLEDGE：该选择只是会话延迟 确认消息的提交，如果JMS Provider失败
         * 可能会导致重复的消息，如果是重复的消息，那么JMS Provider必须把消息头的JMSRedelivered字段 设置为true
         */
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        // 消息队列  子类有两种: topic(适合发布订阅模式)/queue(适合点对点模式)
        Destination destination = session.createQueue("queue");

        // 创建生产者
        MessageProducer producer = session.createProducer(destination);

        // 发送文本消息
        for (int i = 0; i < 10; i++) {
            TextMessage message = session.createTextMessage("JMS Provider发送消息:" + i);
            System.out.println("JMS Provider发送消息:" + i);
            producer.send(message);
        }

        // 启动事务需要commit提交
        session.commit();
        session.close();
        connection.close();
    }
}
