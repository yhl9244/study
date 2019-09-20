package mybatis.self;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class TestClassRoomMain {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        ClassRoomMapper mapper = sqlSession.getMapper(ClassRoomMapper.class);
        ClassRoom classRoom1 = mapper.findOneToMany1(1);
        System.out.println(classRoom1.toString());
        ClassRoom classRoom2 = mapper.findOneToMany2(1);
        System.out.println(classRoom2.toString());
    }
}
