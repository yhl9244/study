package mybatis.self;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassRoomMapper {

    ClassRoom findOneToMany1(int id);//嵌套结果映射：使用嵌套的结果映射来处理连接结果的重复子集

    ClassRoom findOneToMany2(int id);//嵌套 Select 查询：通过执行另外一个 SQL 映射语句来加载期望的复杂类型。
}
