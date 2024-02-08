package com.zjx.day03;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-06 17:24:05
 * @Desc: 查找兄弟单词
 * 【描述】
 * 定义一个单词的“兄弟单词”为：交换该单词字母顺序（注：可以交换任意次），而不添加、删除、修改原有的字母就能生成的单词。
 * 兄弟单词要求和原来的单词不同。例如： ab 和 ba 是兄弟单词。 ab 和 ab 则不是兄弟单词。
 * 现在给定你 n 个单词，另外再给你一个单词 x ，让你寻找 x 的兄弟单词里，按字典序排列后的第 k 个单词是什么？
 * 注意：字典中可能有重复单词。
 * 数据范围：1≤n≤1000 ，输入的字符串长度满足 1≤len(str)≤10 ，1≤k<n
 * 【输入描述】：
 * 输入只有一行。 先输入字典中单词的个数n，再输入n个单词作为字典单词。 然后输入一个单词x 最后后输入一个整数k
 * 【输出描述】：
 * 第一行输出查找到x的兄弟单词的个数m 第二行输出查找到的按照字典顺序排序后的第k个兄弟单词，没有符合第k个的话则不用输出。
 */
public class E27_FindBrotherWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        String[] words = new String[num];
        for (int i = 0; i < num; i++) {
            words[i] = in.next();
        }
        String bother = in.next();
        int target = in.nextInt();

        List<String> pool = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (isBrother(bother,words[i])){
                pool.add(words[i]);
            }
        }

        String[] res = pool.toArray(new String[0]);
        Arrays.sort(res);
        System.out.println(pool.size());
        if (target -1 < res.length){
            System.out.println(res[target-1]);
        }

    }


    public static boolean isBrother(String A,String B){
        if (A == null || B == null || A.length() == 0 || B.length() == 0 || A.length() != B.length() || A.equals(B)){
            return false;
        }

        Map<Character, Integer> tableA = createTable(A);
        Map<Character, Integer> tableB = createTable(B);
        for (Map.Entry<Character, Integer> entryA : tableA.entrySet()) {
            if (!tableB.containsKey(entryA.getKey()) || tableB.get(entryA.getKey()) != entryA.getValue()){
                return false;
            }
        }

        return true;
    }

    public static Map<Character,Integer> createTable(String str){
        Map<Character,Integer> res = new HashMap<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!res.containsKey(chars[i])){
                res.put(chars[i],1);
            }else {
                res.put(chars[i],res.get(chars[i]) + 1);
            }
        }

        return res;
    }

}
