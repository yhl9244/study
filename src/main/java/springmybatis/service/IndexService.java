package springmybatis.service;

import springmybatis.anno.Autowired;
import springmybatis.anno.Compoment;
import springmybatis.dao.IndexDao;

/**
 * Created by suneee on 2018/10/9.
 */
@Compoment
public class IndexService {

    @Autowired
    IndexDao indexDao;

    public void index() {
        System.out.println("IndexService....");
        indexDao.index();
    }
}
