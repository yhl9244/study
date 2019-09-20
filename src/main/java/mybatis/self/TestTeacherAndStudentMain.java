package mybatis.self;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestTeacherAndStudentMain {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        TeacherAndStudentMapper mapper = sqlSession.getMapper(TeacherAndStudentMapper.class);
        List<Teacher> manyToMany = mapper.findManyToMany();
        System.out.println(manyToMany.toString());
    }
}
