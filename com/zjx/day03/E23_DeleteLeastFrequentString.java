package com.zjx.day03;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-05 20:07:51
 * @Desc: 删除字符串中出现次数最少的字符
 * 【描述】
 * 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。
 * 输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * 数据范围：输入的字符串长度满足
 * 1≤n≤20  ，保证输入的字符串中仅出现小写字母
 * 【输入描述】：
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 * 【输出描述】：
 * 删除字符串中出现次数最少的字符后的字符串。
 */
public class E23_DeleteLeastFrequentString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Character,Integer> frequent = new HashMap<>();
        String str = in.nextLine();

        //统计每个字符出现次数
        for (int i = 0; i < str.length(); i++) {
            if (!frequent.containsKey(str.charAt(i))){
                frequent.put(str.charAt(i),1);
            }else {
                frequent.put(str.charAt(i),frequent.get(str.charAt(i))+1);
            }
        }

        //记录出现最少次数的字符的位置
        List<Character> record = new ArrayList<>();
        int min = str.length();
        for (int i = 0; i < str.length(); i++) {
            int fre = frequent.get(str.charAt(i));
            char cur = str.charAt(i);
            if (fre < min){
                min = fre;
                record.clear();
                record.add(cur);
            }else if (fre == min){
                if (!record.contains(cur)){
                    record.add(cur);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (!record.contains(cur)) {
                res.append(cur);
            }
        }

        System.out.println(res.toString());
    }
}
