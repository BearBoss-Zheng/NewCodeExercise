package com.zjx.day01;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-01-30 22:01:22
 * @Desc：计算某字符出现次数
 * 【描述】
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，
 * 然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
 * 数据范围：
 * 1 ≤ n ≤ 1000
 * 【输入描述】：
 * 第一行输入一个由字母、数字和空格组成的字符串，第二行输入一个字符（保证该字符不为空格）。
 * 【输出描述】：
 * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
 */
public class E2_TimesOfStringAppear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 next 和 nextLine 的区别
        String str = in.nextLine();
        String searchS = in.nextLine();
        str = str.toUpperCase();
        char ch = searchS.toUpperCase().charAt(0);
        int res = 0;

        for(int i = 0; i < str.length() ; i++){
            if(str.charAt(i) == ch){
                res++;
            }
        }

        System.out.println(res);
    }
}
