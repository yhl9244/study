package mybatis;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by suneee on 2018/9/29.
 */
@Mapper
public interface AccountMapper {

    Account selectById(String id);

    void updateAccount(Account account);
}
