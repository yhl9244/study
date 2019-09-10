package springmybatis.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import springmybatis.entity.City;

import java.util.List;

@CacheNamespace // 开启二级缓存，同一命名空间有效，不同命名空间无效
public interface TestDao {

    @Select("select * from city")
    public List<City> findList();

}
