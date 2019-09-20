package mybatis.self;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Student {

    private int id;
    private String name;
    private boolean pass;
    //private ClassRoom classRoom; //添加房间属性
    private List<Teacher> teacherList;//教师列表
}
