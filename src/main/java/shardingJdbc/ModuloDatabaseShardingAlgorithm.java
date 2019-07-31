package shardingJdbc;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.routing.strategy.NoneKeyShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

    // 分析前提，假设预期分到两个表中[t_order_0,t_order_1]，且执行的SQL为SELECT o.* FROM t_order o where o.order_id=1001 AND o.user_id=10，那么分表列order_id的值为1001
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        // 遍历表名
        for (String s : collection) {
            // 直到表名是以分表列order_id的值1001对2取模的值即1结尾，那么就是命中的表名，即t_order_1
            if (s.endsWith(shardingValue.getValue() % collection.size() + "")) {
                return s;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        // 从这里可知，doInSharding()和doEqualSharding()的区别就是doInSharding()时分表列有多个值（shardingValue.getValues()），例如order_id的值为[1001,1002]，遍历这些值，然后每个值按照doEqualSharding()的逻辑计算表名
        for (Integer aLong : shardingValue.getValues()) {
            for (String s : collection) {
                if (s.endsWith(shardingValue.getValue() % collection.size() + "")) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        // 从这里可知，doBetweenSharding()和doInSharding()的区别就是doBetweenSharding()时分表列的多个值通过shardingValue.getValueRange()得到；而doInSharding()是通过shardingValue.getValues()得到；
        Range<Integer> valueRange = shardingValue.getValueRange();
        for (Integer i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String s : collection) {
                if (s.endsWith(shardingValue.getValue() % collection.size() + "")) {
                    result.add(s);
                }
            }
        }
        return result;
    }
}
