package com.zjx.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author zjx
 * @Date 2024-02-06 19:38:28
 * @Desc: 字符串合并处理
 * 【描述】
 * 按照指定规则对输入的字符串进行处理。
 * 详细描述：
 * 第一步：将输入的两个字符串str1和str2进行前后合并。如给定字符串 "dec" 和字符串 "fab" ，
 *       合并后生成的字符串为 "decfab"
 * 第二步：对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。
 *        这里的下标的意思是字符在字符串中的位置。注意排序后在新串中仍需要保持原来的奇偶性。
 *        例如刚刚得到的字符串“decfab”，分别对下标为偶数的字符'd'、'c'、'a'和
 *        下标为奇数的字符'e'、'f'、'b'进行排序（生成 'a'、'c'、'd' 和 'b' 、'e' 、'f'），
 *        再依次分别放回原串中的偶数位和奇数位，新字符串变为“abcedf”
 * 第三步：对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作。
 * 转换规则如下：
 * 对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，然后再转换成对应的十六进制大写字符
 * （注：字符 a~f 的十六进制对应十进制的10~15，大写同理）。
 * 如字符 '4'，其二进制为 0100 ，则翻转后为 0010 ，也就是 2 。转换后的字符为 '2'。
 * 如字符 ‘7’，其二进制为 0111 ，则翻转后为 1110 ，对应的十进制是14，转换为十六进制的大写字母为 'E'。
 * 如字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，也就是3。转换后的字符是 '3'。
 * 根据这个转换规则，由第二步生成的字符串 “abcedf” 转换后会生成字符串 "5D37BF"。
 * 数据范围：输入的字符串长度满足1≤n≤100
 * 【输入描述】：
 * 样例输入两个字符串，用空格隔开。
 * 【输出描述】：
 * 输出转化后的结果。
 */
public class E30_MergeSpring {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String A = in.next();
        String B = in.next();

        //第一步：合并
        String mergeString = mergeString(A, B);
        //第二步：排序
        String sort = sort(mergeString);
        //第三步：转换
        String transfer = transfer(sort);

        System.out.println(transfer);
    }

    /**
     * 第一步：将输入的两个字符串str1和str2进行前后合并。如给定字符串 "dec" 和字符串 "fab" ，
     *       合并后生成的字符串为 "decfab"
     */
    public static String mergeString(String A,String B){
        return A + B;
    }

    /**
     * 第二步：对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。
     *        这里的下标的意思是字符在字符串中的位置。注意排序后在新串中仍需要保持原来的奇偶性。
     *        例如刚刚得到的字符串“decfab”，分别对下标为偶数的字符'd'、'c'、'a'和
     *        下标为奇数的字符'e'、'f'、'b'进行排序（生成 'a'、'c'、'd' 和 'b' 、'e' 、'f'），
     *        再依次分别放回原串中的偶数位和奇数位，新字符串变为“abcedf”
     */
    public static String sort(String str){
        List<Character> even = new ArrayList<>();
        List<Character> odd = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0){
                //偶数
                even.add(str.charAt(i));
            }else {
                //奇数
                odd.add(str.charAt(i));
            }
        }

        even = even.stream().sorted().collect(Collectors.toList());
        odd = odd.stream().sorted().collect(Collectors.toList());

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0){
                res.append(even.get(i/2));
            }else {
                res.append(odd.get(i/2));
            }
        }

        return res.toString();
    }

    /**
     * 第三步：对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作。
     * 转换规则如下：
     * 对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，然后再转换成对应的十六进制大写字符
     * （注：字符 a~f 的十六进制对应十进制的10~15，大写同理）。
     * 如字符 '4'，其二进制为 0100 ，则翻转后为 0010 ，也就是 2 。转换后的字符为 '2'。
     * 如字符 ‘7’，其二进制为 0111 ，则翻转后为 1110 ，对应的十进制是14，转换为十六进制的大写字母为 'E'。
     * 如字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，也就是3。转换后的字符是 '3'。
     */
    public static String transfer(String str){
        if (str == null || str.length() == 0){
            return null;
        }

        char[] chars = str.toCharArray();
        char zero = '0';
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])){
                //处理数字
                res.append(transferDigital(chars[i] - zero));
            }else if (('a'<=chars[i] && chars[i] <= 'f') || ('A'<=chars[i] && chars[i] <= 'F')){
                //处理a-f
                res.append(transferLetter(chars[i]));
            }else {
                res.append(chars[i]);
            }
        }

        return res.toString();
    }


    public static String transferDigital(int num){
        //10进制 --> 2进制
        String binary = Integer.toBinaryString(num);
        //反转
        binary = reverse(binary);
        while (binary.length() < 4){
            binary +="0";
        }
        //转换成16进制
        String res = Integer.toHexString(Integer.parseInt(binary, 2));
        return res.toUpperCase();
    }

    public static String transferLetter(char letter){
        //16进制 --> 10进制
        int num = Integer.parseInt(String.valueOf(letter),16);
        return transferDigital(num);
    }

    public static String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }
}
