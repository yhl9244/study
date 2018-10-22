package activemq.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by suneee on 2018/9/25.
 */
public class Consumer1 {

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
        Destination destination = session.createQueue("queue");

        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        while (true){
            TextMessage message = (TextMessage) consumer.receive();// 间隔1000ms接收消息
            if(message != null){
                System.out.println("收到消息" + message.getText());
            }else{
                System.out.println("消息队列无消息......");
                break;
            }
        }

//        session.close();
//        connection.close();
    }
}
