package com.zjx.day02;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-03 15:44:45
 * @Desc：数字颠倒
 * 【描述】
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 * 【数据范围】：
 * 0≤n≤2^30−1
 * 【输入描述】：
 * 输入一个int整数
 * 【输出描述】：
 * 将这个整数以字符串的形式逆序输出
 */
public class E11_ReverseNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        char[] chars = (num+"").toCharArray();
        for (int i = chars.length-1; i >= 0 ; i--) {
            System.out.print(chars[i]);
        }
    }
}
