package com.zjx.day01;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-02 17:46:17
 * @Desc: 提取不重复的整数
 * 【描述】
 * 输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * 保证输入的整数最后一位不是 0 。
 * 数据范围：
 * 1≤n≤10^8
 * 【输入描述】：
 * 输入一个int型整数
 * 【输出描述】
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 */
public class E9_ExtractUniqueIntegers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        char[] chars = (num+"").toCharArray();
        StringBuilder str = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        for (int i = chars.length-1; i >= 0; i--) {
            if (!set.contains(chars[i])){
                set.add(chars[i]);
                str.append(chars[i]);
            }
        }

        System.out.println(str.toString());
    }

}
