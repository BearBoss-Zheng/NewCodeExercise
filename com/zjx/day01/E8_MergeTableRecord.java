package com.zjx.day01;

import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zjx
 * @Date 2024-02-02 16:55:12
 * @Desc：合并表记录
 * 【描述】
 * 数据表记录包含表索引index和数值value（int范围的正整数），
 * 请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
 * 【提示】:
 * 0 <= index <= 11111111
 * 1 <= value <= 100000
 * 【输入描述】：
 * 先输入键值对的个数n（1 <= n <= 500）
 * 接下来n行每行输入成对的index和value值，以空格隔开
 * 【输出描述】：
 * 输出合并后的键值对（多行）
 *
 * 输入: 4
 *      0 1
 *      0 2
 *      1 2
 *      3 4
 * 输出: 0 3
 *      1 2
 *      3 4
 *
 */
public class E8_MergeTableRecord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int line = in.nextInt();

        Map<Integer,Integer> map = new TreeMap<>();

        while (line-- >0){
            int index = in.nextInt();
            int value = in.nextInt();
            if (!map.containsKey(index)){
                map.put(index,value);
            }else {
                map.put(index,map.get(index) + value);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
