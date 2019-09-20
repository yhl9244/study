package mybatis.self;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestStudentMain {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> manyToOne1 = mapper.findManyToOne1();
        System.out.println(manyToOne1.toString());
        List<Student> manyToOne2 = mapper.findManyToOne2();
        System.out.println(manyToOne2.toString());
    }
}
