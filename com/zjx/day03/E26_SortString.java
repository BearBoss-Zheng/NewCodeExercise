package com.zjx.day03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-06 14:19:23
 * @Desc: 字符串排序
 * 【描述】
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 * 如，输入： Type 输出： epTy
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * 如，输入： BabA 输出： aABb
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 * 如，输入： By?e 输出： Be?y
 * 数据范围：输入的字符串长度满足
 * 1≤n≤1000
 * 【输入描述】：
 * 输入字符串
 * 【输出描述】：
 * 输出字符串
 */
public class E26_SortString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] data = in.nextLine().toCharArray();
        //把所有的非英文字符提取出来
        Map<Integer,Character> nonEnglish = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            if (!Character.isLetter(data[i])) {
                // 这里是非字母的处理逻辑
                nonEnglish.put(i,data[i]);
            }
        }

        //提取所有的英文字母
        char[] english = new char[data.length-nonEnglish.size()];
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            if (Character.isLetter(data[i])){
                english[index++] = data[i];
            }
        }

        //对英文字母进行排序（冒泡）
        for (int i = 0; i < english.length -1; i++) {
            for (int j = 0; j < english.length -1-i; j++) {
                int j1 = Character.toLowerCase(english[j]);
                int j2 = Character.toLowerCase(english[j+1]);
                char temp = 0;
                if (j1 > j2){
                    temp = english[j];
                    english[j] = english[j+1];
                    english[j+1] = temp;
                }
            }
        }

        //填如数据
        char[] resC = new char[data.length];
        index = 0;
        int engIndex = 0;
        while (index < resC.length){
            if (nonEnglish.containsKey(index)){
                resC[index] = nonEnglish.get(index);
            }else {
                resC[index] = english[engIndex];
                engIndex++;
            }
            index++;
        }

        String res = new String(resC);
        System.out.println(res);

    }


}
