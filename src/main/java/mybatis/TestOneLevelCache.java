package mybatis;

import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * Created by suneee on 2018/10/8.
 */
public class TestOneLevelCache {

    /**
     *   一级缓存: 也就Session级的缓存(默认开启)
     * @param args
     */
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String statement = "mybatis.AccountMapper.selectById";
        Account account = sqlSession.selectOne(statement, "1");
        System.out.println(account);
        /**
         * 一级缓存默认就会被使用
         */
        account = sqlSession.selectOne(statement, "1");
        System.out.println(account);
        sqlSession.close();

        /**
         * 1. 必须是同一个Session,如果session对象已经close()过了就不可能用了
         */
        sqlSession = MyBatisUtil.getSqlSession();
        account = sqlSession.selectOne(statement, "1");
        System.out.println(account);
        /**
         * 2. 查询条件必须一致
         */
        account = sqlSession.selectOne(statement, "2");
        System.out.println(account);

        /**
         * 3. 没有执行过session.clearCache()清理缓存
         */
        //sqlSession.clearCache();
        account = sqlSession.selectOne(statement, "2");
        System.out.println(account);

        /**
         * 4. 没有执行过增删改的操作(这些操作都会清理缓存)
         */
        sqlSession.update("mybatis.AccountMapper.updateAccount", new Account(3, "yhl", 300d));
        account = sqlSession.selectOne(statement, "2");
        System.out.println(account);
    }
}
