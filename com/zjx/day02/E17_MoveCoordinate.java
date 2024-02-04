package com.zjx.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-04 00:05:44
 * @Desc: 坐标移动
 * 【描述】
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。
 * 从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 * 【输入】：
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
 * 坐标之间以;分隔。
 * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
 * 下面是一个简单的例子 如：
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 * 处理过程：
 * 起点（0,0）
 * +   A10   =  （-10,0）
 * +   S20   =  (-10,-20)
 * +   W10  =  (-10,-10)
 * +   D30  =  (20,-10)
 * +   x    =  无效
 * +   A1A   =  无效
 * +   B10A11   =  无效
 * +  一个空 不影响
 * +   A10  =  (10,-10)
 * 结果 （10， -10）
 * 数据范围：每组输入的字符串长度满足 1≤n≤10000  ，坐标保证满足−2^31≤x,y≤2^31−1  ，且数字部分仅含正数
 * 【输入描述】：
 * 一行字符串
 * 【输出描述】：
 * 最终坐标，以逗号分隔
 */
public class E17_MoveCoordinate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(";");
        List<String> list = new ArrayList<>();
        for (String s : str) {
            if (isValid(s)){
                list.add(s);
            }
        }

        int x = 0;
        int y = 0;

        for (String s : list) {
            if (s.charAt(0) == 'A'){
                //左
                x -= Integer.parseInt(s.substring(1));
            } else if (s.charAt(0) == 'D') {
                //右
                x += Integer.parseInt(s.substring(1));
            } else if (s.charAt(0) == 'W') {
                //上
                y += Integer.parseInt(s.substring(1));
            }else {
                //下
                y -= Integer.parseInt(s.substring(1));
            }
        }

        System.out.println(x+","+y);

    }

    public static boolean isValid(String s){
        if (s == null || s.length() ==0){
            return false;
        }

        if (!(s.charAt(0) == 'A' || s.charAt(0) == 'S' || s.charAt(0) == 'W' || s.charAt(0) == 'D')){
            return false;
        }

        return s.substring(1).matches("^[1-9]\\d*$");
    }
}
