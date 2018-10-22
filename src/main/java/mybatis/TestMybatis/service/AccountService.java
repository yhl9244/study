package mybatis.TestMybatis.service;

import mybatis.TestMybatis.dao.AccounDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by suneee on 2018/10/8.
 */
@Service
public class AccountService {

    @Autowired
    AccounDao accounDao;

    public void query(){
        System.out.println(accounDao.query(2));
    }
}
