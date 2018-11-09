package mybatis.TestMybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by suneee on 2018/9/29.
 */
@Mapper
public interface AccounDao {

    @Select("select * from account where id=#{id}")
    public List<Map<String, Object>> query(int id);
}
