package com.zjx.day04;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-07 20:37:55
 * @Desc: 字符串加密
 * 【描述】
 * 有一种技巧可以对数据进行加密，它使用一个单词作为它的密匙。
 * 下面是它的工作原理：首先，选择一个单词作为密匙，如TRAILBLAZERS。
 * 如果单词中包含有重复的字母，只保留第1个，将所得结果作为新字母表开头，
 * 并将新建立的字母表中未出现的字母按照正常字母表顺序加入新字母表。如下所示：
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * T R A I L B Z E S C D F G H J K M N O P Q U V W X Y
 * （实际需建立小写字母的字母表，此字母表仅为方便演示）
 * 上面其他用字母表中剩余的字母填充完整。在对信息进行加密时，信息中的每个字母被固定于顶上那行，
 * 并用下面那行的对应字母一一取代原文的字母(字母字符的大小写状态应该保留)。
 * 因此，使用这个密匙， Attack AT DAWN (黎明时攻击)就会被加密为Tpptad TP ITVH。
 * 请实现下述接口，通过指定的密匙和明文得到密文。
 * 数据范围：1≤n≤100  ，保证输入的字符串中仅包含小写字母
 * 【输入描述】：
 * 先输入key和要加密的字符串
 * 【输出描述】：
 * 返回加密后的字符串
 */
public class E36_EncryptString {
    public static void main(String[] args) {
        /*
            思路分析：
            1.取出重复的单词中重复的字幕
            2.创建字母的对照表
            3.加密
         */
        Scanner in = new Scanner(System.in);
        String key = in.nextLine();
        String word = in.nextLine();

        //1
        List<Character> keyList = createKeyList(key);
        //2
        Map<Character, Character> comparisonTable = createComparisonTable(keyList);
        //3
        String encryptedString = encrypt(comparisonTable, word);

        System.out.println(encryptedString);
    }

    /**
     * 建立一个链表，添加密钥，并且去重
     */
    public static List<Character> createKeyList(String key){

        List<Character> res = new ArrayList<>();

        if (key == null || key.length() == 0){
            return res;
        }

        for (int i = 0; i < key.length(); i++) {
            if (!res.contains(key.charAt(i))){
                res.add(key.charAt(i));
            }
        }

        return res;

    }

    /**
     * 创建字母对照表
     */
    public static Map<Character,Character> createComparisonTable(List<Character> keyList){
        char start = 'a';
        Map<Character,Character> map = new HashMap<>();
        List<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            map.put((char)(start+i),'a');
            alphabet.add((char)(start+i));
        }

        //先将keyList添加进去
        for (int i = 0; i <keyList.size() ; i++) {
            map.put((char)(start+i),keyList.get(i));
        }

        char index = (char) ('a' + keyList.size());
        //然后将map中的其他元素补齐
        for (int i = 0; i < 26; i++) {
            if (!keyList.contains((char)(start+i))){
                map.put(index,(char)(start+i));
                index = (char)(index + 1);
            }
        }

        return map;
    }

    /**
     * 加密
     */
    public static String encrypt(Map<Character,Character> comparisonTable,String word){
        StringBuilder res = new StringBuilder();

        for (int i = 0; i <word.length() ; i++) {
            char cur = word.charAt(i);
            //如果是字母，就贾母，否则直接使用
            if (Character.isLetter(cur)){
                res.append(comparisonTable.get(cur));
            }else {
                res.append(cur);
            }
        }

        return res.toString();
    }
}
