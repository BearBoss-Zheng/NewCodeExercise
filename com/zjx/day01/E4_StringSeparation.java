package com.zjx.day01;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-02 14:57:13
 * @Desc：字符串分割
 * 【描述】
 * •输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 【输入描述】：
 * 连续输入字符串(每个字符串长度小于等于100)
 * 【输出描述】：
 * 依次输出所有分割后的长度为8的新字符串
 *
 */
public class E4_StringSeparation {
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        StringBuilder stringIn = new StringBuilder(in.nextLine());
        stringIn = new StringBuilder(stringIn.toString().trim());
        //长度距8的倍数还差多少
        int mod =stringIn.length() % 8 ==0 ? 0 : 8 - (stringIn.length() % 8);

        for (int i = 0; i < mod; i++) {
            stringIn.append("0");
        }


        int times = stringIn.length()/8;

        for (int i = 0; i < times; i++) {
            System.out.println(stringIn.substring(i*8,(i+1)*8));
        }

    }
}
