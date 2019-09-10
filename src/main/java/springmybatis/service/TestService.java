package springmybatis.service;

import org.springframework.stereotype.Service;
import springmybatis.anno.Autowired;
import springmybatis.dao.TestDao;
import springmybatis.entity.City;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public void query() {
        List<City> list = testDao.findList();
        for (City city : list) {
            System.out.println(city.getName());
        }
    }

    public TestDao getTestDao() {
        return testDao;
    }

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }
}
