package com.zjx.day05;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-21 01:01:48
 * @Desc:名字的漂亮度
 * 【描述】
 * 给出一个字符串，该字符串仅由小写字母组成，定义这个字符串的“漂亮度”是其所有字母“漂亮度”的总和。
 * 每个字母都有一个“漂亮度”，范围在1到26之间。没有任何两个不同字母拥有相同的“漂亮度”。字母忽略大小写。
 * 给出多个字符串，计算每个字符串最大可能的“漂亮度”。
 * 本题含有多组数据。
 * 数据范围：输入的名字长度满足 1≤n≤10000
 * 【输入描述】：
 * 第一行一个整数N，接下来N行每行一个字符串
 * 【输出描述】：
 * 每个字符串可能的最大漂亮程度
 */
public class E45_BeautyOfName {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lines = in.nextInt();
        in.nextLine();
        while (lines > 0) {
            char[] chs = in.nextLine().toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            //记录字符串各字母出现的字数
            for (int i = 0; i < chs.length; i++) {
                if (!map.containsKey(chs[i])) {
                    map.put(chs[i], 1);
                } else {
                    map.put(chs[i], map.get(chs[i]) + 1);
                }
            }

            List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
            //按出现次数进行排序
            list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

            int value = 26;
            int res = 0;
            for (Map.Entry<Character, Integer> e : list) {
                res = res + e.getValue() * value;
                value--;
            }

            System.out.println(res);

            lines--;

        }

    }
}

