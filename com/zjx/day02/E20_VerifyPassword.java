package com.zjx.day02;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author zjx
 * @Date 2024-02-04 16:55:44
 * @Desc: 密码验证合格程序
 * 【描述】
 * 密码要求:
 * 1.长度超过8位
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 * 数据范围：输入的字符串长度满足
 * 1≤n≤100
 * 【输入描述】：
 * 一组字符串。
 * 【输出描述】：
 * 如果符合要求输出：OK，否则输出NG
 */
public class E20_VerifyPassword {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String pw = in.nextLine();
            boolean flag = true;
            //长度大于8
            if (pw.length() <= 8){
                flag = false;
            }

            //包括大小写字母.数字.其它符号,以上四种至少三种
            int type = 0;
            String lowRegex = ".*[a-z].*";
            String upRegex = ".*[A-Z].*";
            String numRegex = ".*\\d.*";
            String otherRegex = ".*[^a-zA-Z\\d].*";

            if (Pattern.compile(lowRegex).matcher(pw).matches()){
                type++;
            }

            if (Pattern.compile(upRegex).matcher(pw).matches()){
                type++;
            }

            if (Pattern.compile(numRegex).matcher(pw).matches()){
                type++;
            }

            if (Pattern.compile(otherRegex).matcher(pw).matches()){
                type++;
            }

            if (type < 3){
                flag = false;
            }

            //最大公共子串不能大于2
            //这里的核心思想，和kmp算法的加速数组很像
            HashMap<String,Integer> map = new HashMap<>();
            for (int i = 0; i < pw.length() - 2; i++) {
                if (!map.containsKey(pw.substring(i,i+3))){
                    map.put(pw.substring(i,i+3),i);
                }else {
                    //aaaaa--aa和aa，这种没事
                    //abcabc--这种有事
                    if (i-map.get(pw.substring(i,i+3)) > 2){
                        flag = false;
                        break;
                    }else {

                    }
                }
            }

            if (flag){
                System.out.println("OK");
            }else {
                System.out.println("NG");
            }

        }


    }
}
