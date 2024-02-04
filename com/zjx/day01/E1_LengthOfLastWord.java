package com.zjx.day01;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-01-30 16:11:25
 * @Desc：计算字符串最后一个单词的长度
 * 【描述】：
 * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
 * 【输入描述】：
 * 输入一行，代表要计算的字符串，非空，长度小于5000。
 * 【输出描述】：
 * 输出一个整数，表示输入字符串最后一个单词的长度。
 */
public class E1_LengthOfLastWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int res = 0;

        for(int i=str.length()-1 ;i >= 0;i--){
            if(str.charAt(i) == ' '){
                break;
            }else{
                res++;
            }
        }


        System.out.println(res);
    }
}
