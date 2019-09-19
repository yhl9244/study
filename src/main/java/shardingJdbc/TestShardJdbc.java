package shardingJdbc;

import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.jdbc.core.datasource.ShardingDataSource;
import com.mysql.jdbc.Driver;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestShardJdbc {

    private static ShardingDataSource getShardingDataSource() throws SQLException {
        // 构造DataSourceRule，即key与数据源的KV对
        DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
        // 建立逻辑表是t_order，实际表是t_order_0，t_order_1的TableRule
        TableRule orderTableRule = TableRule.builder("t_order").actualTables(Arrays.asList("t_order_0", "t_order_1")).dataSourceRule(dataSourceRule).build();
        // 建立逻辑表是t_order_item，实际表是t_order_item_0，t_order_item_1的TableRule
        TableRule orderItemTableRule = TableRule.builder("t_order_item").actualTables(Arrays.asList("t_order_item_0", "t_order_item_1")).dataSourceRule(dataSourceRule).build();
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule,orderItemTableRule))
                // 增加绑定表--绑定表代表一组表，这组表的逻辑表与实际表之间的映射关系是相同的。比如t_order与t_order_item就是这样一组绑定表关系,它们的分库与分表策略是完全相同的,那么可以使用它们的表规则将它们配置成绑定表，绑定表所有路由计算将会只使用主表的策略；
                .bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule))))
                // 指定数据库sharding策略--根据user_id字段的值取模
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
                // 指定表sharding策略--根据order_id字段的值取模
                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm())).build();
        return new ShardingDataSource(shardingRule);
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("ds_jdbc_0", createDataSource("ds_jdbc_0"));
        dataSourceMap.put("ds_jdbc_0", createDataSource("ds_jdbc_1"));
        return null;
    }

    private static DataSource createDataSource(final String dataSourceName) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}


