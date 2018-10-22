package mybatis.TestMybatis.dao;

import mybatis.TestMybatis.anno.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by suneee on 2018/9/29.
 */
public interface TestAccounDao {

    @Select("select * from account where id=#{id}")
    public void query(int id);
}
