package com.zjx.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-07 12:22:09
 * @Desc: 单词倒排
 * 【描述】
 * 对字符串中的所有单词进行倒排。
 * 说明：
 * 1、构成单词的字符只有26个大写或小写英文字母；
 * 2、非构成单词的字符均视为单词间隔符；
 * 3、要求倒排后的单词间隔符以一个空格表示；如果原字符串中相邻单词间有多个间隔符时，
 *    倒排转换后也只允许出现一个空格间隔符；
 * 4、每个单词最长20个字母；
 * 数据范围：字符串长度满足1≤n≤10000
 * 【输入描述】：
 * 输入一行，表示用来倒排的句子
 * 【输出描述】：
 * 输出句子的倒排结果
 */
public class E31_ReverseWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            StringBuilder s = new StringBuilder(in.nextLine());
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isLetter(s.charAt(i))){
                    s.replace(i,i+1," ");
                }
            }
            String[] treatmentS = s.toString().split(" ");
            for (int i = treatmentS.length-1;i > 0; i--) {
                System.out.print(treatmentS[i]+" ");
            }
            System.out.println(treatmentS[0]);

        }
    }
}
