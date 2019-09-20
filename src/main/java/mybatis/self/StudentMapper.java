package mybatis.self;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> findManyToOne1();

    List<Student> findManyToOne2();
}
