package springmybatis.test;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springmybatis.dao.TestDao;
import springmybatis.service.TestService;

public class ApplicationContext {

    public static void main(String[] args) {
        // 创建数据库连接
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/world?serverTimezone=GMT%2B8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.setLogImpl(Log4jImpl.class);
        configuration.addMapper(TestDao.class);

        // 二级缓存sessionFactory级别
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TestDao testDao = sqlSession.getMapper(TestDao.class);
        // 测试一级缓存(只打印一次SQL) 一级缓存session级别
        TestService testService = new TestService();
        testService.setTestDao(testDao);
        testService.query();
        testService.query();
        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();
        TestDao testDao1 = sqlSession.getMapper(TestDao.class);
        testService.setTestDao(testDao1);
        testService.query();
    }
}
