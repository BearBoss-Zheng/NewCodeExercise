package com.zjx.day05;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-21 11:13:32
 * @Desc:截取字符串
 * 【描述】
 * 输入一个字符串和一个整数 k ，截取字符串的前k个字符并输出
 * 数据范围：字符串长度满足1≤k≤n
 * 【输入描述】：
 * 1.输入待截取的字符串
 * 2.输入一个正整数k，代表截取的长度
 * 【输出描述】：
 * 截取后的字符串
 */
public class E46_InterceptString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s  = in.nextLine();
        int length = in.nextInt();

        System.out.printf("%s", s.substring(0, length));
        in.close();

    }
}
