package com.zjx.day01;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-02 15:26:08
 * @Desc：进制替换
 * 【描述】
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 * 【数据范围】：保证结果在
 * 1≤n≤2^31 −1
 * 【输入描述】：
 * 输入一个十六进制的数值字符串。
 * 【输出描述】：
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 */
public class E5_RadixConversion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            int res = Integer.parseInt(in.nextLine().substring(2),16);
            System.out.println(res);
        }
    }
}
