package mybatis;

import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * Created by suneee on 2018/10/8.
 */
public class TestTwoLevelCache {

    /**
     * 使用两个不同的SqlSession对象去执行相同查询条件的查询，第二次查询时不会再发送SQL语句，而是直接从缓存中取出数据
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String statement = "mybatis.AccountMapper.selectById";
        SqlSession sqlSession1 = MyBatisUtil.getSqlSession();
        SqlSession sqlSession2 = MyBatisUtil.getSqlSession();
        //使用二级缓存时，User类必须实现一个Serializable接口===> User implements Serializable
        Account account = sqlSession1.selectOne(statement, 1);
        sqlSession1.commit();
        System.out.println(account);
        //由于使用的是两个不同的SqlSession对象，所以即使查询条件相同，一级缓存也不会开启使用
        account = sqlSession2.selectOne(statement, 1);
        System.out.println(account);
    }
}
