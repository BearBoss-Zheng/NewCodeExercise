package com.zjx.day02;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author zjx
 * @Date 2024-02-03 16:12:36
 * @Desc：字符串排序
 * 【描述】
 * 给定 n 个字符串，请对 n 个字符串按照字典序排列。
 * 数据范围：1≤n≤1000  ，字符串长度满足1≤len≤100
 * 【输入描述】：
 * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
 * 【输出描述】：
 * 数据输出n行，输出结果为按照字典序排列的字符串。
 */
public class E14_SortString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TreeMap<String,Integer> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                char[] c1 = s1.toCharArray();
                char[] c2 = s2.toCharArray();
                int index = 0;
                while (index < c1.length && index < c2.length){
                    if (c1[index] != c2[index]){
                        return c1[index] - c2[index];
                    }
                    index++;
                }

                return c1.length-c2.length;
            }
        });
        int line = in.nextInt();
        while (line > 0){
            String str = in.next();
            if (!map.containsKey(str)){
                map.put(str,1);
            }else {
                map.put(str,map.get(str)+1);
            }
            line--;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                System.out.println(entry.getKey());
            }
        }

    }
}
