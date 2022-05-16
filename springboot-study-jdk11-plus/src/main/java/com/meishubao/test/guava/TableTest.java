package com.meishubao.test.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * Table 表结构数据
 *
 * <p>
 *     当试图一次在多个键上建立索引时，您将得到类似 <code>Map&lt;FirstName，Map&lt;LastName，Person&gt;&gt;</code> 的代码，这很难看，而且使用起来很尴尬。
 *     Guava 提供了一个新的集合类型 Table，它支持任何“row”类型和“column”类型的这个用例
 * </p>
 *
 * @author lilu
 */
public class TableTest {

    public static void main(String[] args) {
        Table<Integer, Integer, Double> weightedGraph = HashBasedTable.create();
        weightedGraph.put(1, 2, 4D);
        weightedGraph.put(1, 3, 20D);
        weightedGraph.put(2, 3, 5D);

        Map<Integer, Double> row = weightedGraph.row(1);// returns a Map mapping v2 to 4, v3 to 20
        System.out.println(row);
        Map<Integer, Double> column = weightedGraph.column(2);// returns a Map mapping v1 to 20, v2 to 5
        System.out.println(column);
    }

}
