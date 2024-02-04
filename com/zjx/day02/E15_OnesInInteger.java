package com.zjx.day02;


import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-03 21:01:09
 * @Desc：求int型正整数在内存中存储时1的个数
 * 【描述】
 * 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。
 * 数据范围：保证在 32 位整型数字范围内
 * 【输入描述】：
 *  输入一个整数（int类型）
 * 【输出描述】：
 *  这个数转换成2进制后，输出1的个数
 */
public class E15_OnesInInteger {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] num = Integer.toString(in.nextInt(),2).toCharArray();
        int res = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] == '1'){
                res++;
            }
        }

        System.out.println(res);
    }
}
