package mybatis.self;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ClassRoom {

    private int id;
    private String className;
    private String classAddress;
    // private List<Student> studentList; //添加学生list属性
}