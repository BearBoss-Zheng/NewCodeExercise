package com.zjx.day04;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-09 00:35:33
 * @Desc: 统计字符
 * 描述
 * 输入一行字符，分别统计出包含英文字母、空格、数字和其它字符的个数。
 * 数据范围：输入的字符串长度满足 1≤n≤1000
 * 输入描述：
 * 输入一行字符串，可以有空格
 * 输出描述：
 * 统计其中英文字符，空格字符，数字字符，其他字符的个数
 */
public class E40_CountChar {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        //0-1：英文字符，空格字符，数字字符，其他字符的个数
        int[] res = new int[4];

        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))){
                res[0]++;
            }else if (Character.isSpaceChar(str.charAt(i))){
                res[1]++;
            }else if (Character.isDigit(str.charAt(i))){
                res[2]++;
            }else {
                res[3]++;
            }
        }

        for (int re : res) {
            System.out.println(re);
        }
    }
}
