package shardingJdbc;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

public class ModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        for (String s : collection) {
            if (s.endsWith(shardingValue.getValue() % collection.size() + "")) {
                return s;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
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
