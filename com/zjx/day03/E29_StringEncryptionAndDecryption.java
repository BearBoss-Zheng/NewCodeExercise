package com.zjx.day03;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-06 19:30:28
 * @Desc: 字符串加解密
 * 【描述】
 * 对输入的字符串进行加解密，并输出。
 * 加密方法为：
 * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
 * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
 * 其他字符不做变化。
 * 解密方法为加密的逆过程。
 * 数据范围：输入的两个字符串长度满足
 * 1≤n≤1000  ，保证输入的字符串都是只由大小写字母或者数字组成
 * 【输入描述】：
 * 第一行输入一串要加密的密码
 * 第二行输入一串加过密的密码
 * 【输出描述】：
 * 第一行输出加密后的字符
 * 第二行输出解密后的字符
 */
public class E29_StringEncryptionAndDecryption {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String needEncrypted = in.nextLine();
        String needDecrypted = in.nextLine();

        System.out.println( encrypt(needEncrypted));
        System.out.println(decrypt(needDecrypted));

        in.close();
    }

    /**
     * 加密
     */
    public static String encrypt(String str){
        if (str == null || str.length() == 0){
            return null;
        }

        StringBuilder res = new StringBuilder();
        
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])){
                //处理数字
                String n = String.valueOf(chars[i] - '0' + 1);
                res.append(n.substring(n.length() - 1));
            } else if (Character.isUpperCase(chars[i])) {
                //处理大写字母
                if (chars[i] == 'Z'){
                    res.append('a');
                }else {
                    res.append(Character.toLowerCase((char)(chars[i] + 1)));
                }
            }else {
                //处理小写字幕
                if (chars[i] == 'z'){
                    res.append('A');
                }else {
                    res.append(Character.toUpperCase((char) (chars[i] + 1)));
                }
            }
        }

        return res.toString();
    }

    /**
     * 解密
     */
    public static String decrypt(String str){
        if (str == null || str.length() == 0){
            return null;
        }

        StringBuilder res = new StringBuilder();

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])){
                //处理数字
                if (chars[i] == '0'){
                   res.append('9');
                }else {
                    res.append(chars[i] - '0' - 1);
                }
            } else if (Character.isUpperCase(chars[i])) {
                //处理大写字母
                if (chars[i] == 'A'){
                    res.append('z');
                }else {
                    res.append(Character.toLowerCase((char) (chars[i] - 1)));
                }
            }else {
                //处理小写字幕
                if (chars[i] == 'a'){
                    res.append('Z');
                }else {
                    res.append(Character.toUpperCase((char) (chars[i] - 1)));
                }
            }
        }

        return res.toString();
    }
}
