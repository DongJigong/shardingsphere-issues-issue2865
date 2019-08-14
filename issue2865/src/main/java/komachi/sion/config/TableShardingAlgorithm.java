package komachi.sion.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
public class TableShardingAlgorithm implements ComplexKeysShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        String tableName = complexKeysShardingValue.getLogicTableName();
        Map<String, LinkedList<Object>> columns = complexKeysShardingValue.getColumnNameAndShardingValuesMap();

        Collection<String> table = new LinkedList<>();

        /**
         * schoolId
         * dayTime
         */
        LinkedList<Object> schoolIdColumnValues = columns.get("order_id");
        LinkedList<Object> dayTimeColumnValues = columns.get("month_time");
        for (int i = 0; i < dayTimeColumnValues.size(); i++) {
            StringBuilder realTable = new StringBuilder(tableName);
            String monthYear = (String) dayTimeColumnValues.get(0);
            realTable.append("_").append(schoolIdColumnValues.get(i)).append("_").append(monthYear);
            table.add(realTable.toString());
        }
        log.info("#TableShardingAlgorithm# realTableName:{}", table);
        return table;
    }
}
