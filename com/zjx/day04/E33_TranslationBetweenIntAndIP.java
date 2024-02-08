package com.zjx.day04;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-07 14:55:59
 * @Desc: 整数与IP地址间的转换
 * 【描述】
 * 原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成
 * 一个长整数。
 * 举例：一个ip地址为10.0.3.193
 * 每段数字             相对应的二进制数
 * 10                   00001010
 * 0                    00000000
 * 3                    00000011
 * 193                  11000001
 * 组合起来即为：00001010 00000000 00000011 11000001,
 * 转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了。
 * 数据范围：保证输入的是合法的 IP 序列
 * 【输入描述】：
 * 输入
 * 1 输入IP地址
 * 2 输入10进制型的IP地址
 * 【输出描述】：
 * 输出
 * 1 输出转换成10进制的IP地址
 * 2 输出转换后的IP地址
 */
public class E33_TranslationBetweenIntAndIP {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip = in.nextLine();
        String longNum = in.nextLine();
        System.out.println(IPToInt(ip));
        System.out.println(intToIp(longNum));

        in.close();
    }

    /**
     * 将长整数转成ip
     */
    public static String intToIp(String str){
        StringBuilder num = new StringBuilder();
        String binaryString = Long.toBinaryString(Long.parseLong(str));
        num.append(binaryString);
        int length = 32 - num.length();

        for (int i = 0; i < length; i++) {
            num.insert(0, "0");
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            res.append(Integer.parseInt(num.substring(i*8,(i+1)*8),2)).append(".");
        }

        res.delete(res.length()-1,res.length());

        return res.toString();

    }

    /**
     * 将ip转成长整数
     */
    public static String IPToInt(String str){
        String[] nums = str.split("\\.");
        StringBuilder pool = new StringBuilder();
        for (String num : nums) {
            StringBuilder binaryNum = new StringBuilder(Integer.toBinaryString(Integer.parseInt(num)));
            int length = 8 - binaryNum.length();
            for (int j = 0; j < length; j++) {
                binaryNum.insert(0, "0");
            }
            pool.append(binaryNum);
        }

        return String.valueOf(Long.parseLong(pool.toString(),2));
    }
}
