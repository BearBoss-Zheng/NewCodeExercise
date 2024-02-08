package com.zjx.day03;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-05 19:03:37
 * @Desc: 简单密码
 * 【描述】
 * 现在有一种密码变换算法。
 * 九键手机键盘上的数字与字母的对应：
 * 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0，
 * 把密码中出现的小写字母都变成九键键盘对应的数字，如：a 变成 2，x 变成 9.
 * 而密码中出现的大写字母则变成小写之后往后移一位，如：X ，先变成小写，再往后移一位，变成了 y ，
 * 例外：Z 往后移是 a 。
 * 数字和其它的符号都不做变换。
 * 数据范围： 输入的字符串长度满足：1≤n≤100
 * 【输入描述】：
 * 输入一组密码，长度不超过100个字符。
 * 【输出描述】：
 * 输出密码变换后的字符串
 */
public class E21_SimplePassword {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Character,Integer> map = new HashMap<>();
        map.put('a',2);
        map.put('b',2);
        map.put('c',2);
        map.put('d',3);
        map.put('e',3);
        map.put('f',3);
        map.put('g',4);
        map.put('h',4);
        map.put('i',4);
        map.put('j',5);
        map.put('k',5);
        map.put('l',5);
        map.put('m',6);
        map.put('n',6);
        map.put('o',6);
        map.put('p',7);
        map.put('q',7);
        map.put('r',7);
        map.put('s',7);
        map.put('t',8);
        map.put('u',8);
        map.put('v',8);
        map.put('w',9);
        map.put('x',9);
        map.put('y',9);
        map.put('z',9);
        int dif = 'a' - 'A';
        String str = in.nextLine();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))){
                //小写字母
                res.append(map.get(str.charAt(i)));
            }else if ('A'<=str.charAt(i) && str.charAt(i)<'Z'){
                //大写字母（不包括‘Z’）
                res.append((char) (str.charAt(i)+dif+1));
            } else if (str.charAt(i) == 'Z') {
                //‘Z’
                res.append('a');
            } else {
                //数字
                res.append(str.charAt(i));
            }
        }


        System.out.println(res.toString());
    }
}
