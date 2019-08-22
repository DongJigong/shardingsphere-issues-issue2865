package komachi.sion.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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
            String monthYear = (String) dayTimeColumnValues.get(i);
            DateTime yyyyMM = DateTime.parse(monthYear, DateTimeFormat.forPattern("yyyyMM"));
            int year = yyyyMM.getYear();
            int month = yyyyMM.getMonthOfYear();


            realTable.append("_").append(schoolIdColumnValues.get(0)).append("_").append(year).append("_").append(month);
            table.add(realTable.toString());
        }
        log.info("#TableShardingAlgorithm# realTableName:{}", table);
        return table;
    }

//    @Override
//    public Collection<String> doSharding(final Collection<String> availableTargetNames, final Collection<RouteValue> shardingValues) {
//        Map<String, Collection<Comparable<?>>> columnShardingValues = new HashMap<>(shardingValues.size(), 1);
//        String logicTableName = "";
//        for (RouteValue each : shardingValues) {
//            columnShardingValues.put(each.getColumnName(), ((ListRouteValue) each).getValues());
//            logicTableName = each.getTableName();
//        }
//        Collection<String> shardingResult = shardingAlgorithm.doSharding(availableTargetNames, new ComplexKeysShardingValue(logicTableName, columnShardingValues));
//        Collection<String> result = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
//        result.addAll(shardingResult);
//        return result;
//    }
}
