package mybatis.self;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherAndStudentMapper {

    List<Teacher> findManyToMany();
}