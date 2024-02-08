package com.zjx.day04;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-07 12:44:41
 * @Desc: 密码截取
 * 【描述】
 * Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，
 * 比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
 * 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。
 * 因为截获的串太长了，而且存在多种可能的情况（abaaab可看作是aba,或baaab的加密形式），
 * Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
 * 数据范围：字符串长度满足 1≤n≤2500
 * 【输入描述】：
 * 输入一个字符串（字符串的长度不超过2500）
 * 【输出描述】：
 * 返回有效密码串的最大长度
 */
public class E32_InterceptPassword {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String str = in.nextLine();
            int manacher = Manacher(str);
            System.out.println(manacher);
        }

        in.close();

    }

    /**
     * 最长回文字串
     */
    public static int Manacher(String str){
        if (str == null || str.length() == 0){
            return 0;
        }

        if (str.length() == 1){
            return 1;
        }

        //abc -> #a#b#c#
        str = treatString(str);

        //各个位置的最长回文半径
        int[] pool = new int[str.length()];
        pool[0] = 0;
        //保存最大半径
        int maxR = 0;
        //当前的中心点
        int center = 0;
        //当前的点
        int cur = 1;

        int x = 0;
        while (cur < str.length()){

            if (cur >= center+pool[center]){
                //暴力扩张
                pool[cur] = violentExpansion(str,cur,0);
                center = cur;
            }else {
                //以center为原点，cur的对称点
                int symmetry = center - (cur - center);
                //以center为原点的回文边界
                int leftBoundary = center - pool[center];
                int rightBoundary = center + pool[center];
                //对称点的左边界
                int sysL = symmetry - pool[symmetry];

                if (sysL > leftBoundary){
                    pool[cur] = pool[symmetry];
                }else {
                    pool[cur] = violentExpansion(str,cur,0);
                    if (cur + pool[cur] > rightBoundary){
                        center = cur;
                    }
                }
            }
            if (maxR < pool[cur]){
                x = cur;
                maxR = pool[cur];
            }

            cur++;
        }

        //奇数  a#v#... R
        //偶数  #a#a#   R

        return maxR;

    }

    /**
     * 暴力外扩，寻找当前位置的最大回文半径
     */
    public static int violentExpansion(String str,int index,int r){
        int L = index - 1 - r;
        int R = index + 1 + r;
        while (L>=0 && R<str.length()){
            if (str.charAt(L) == str.charAt(R)){
                r++;
                L--;
                R++;
            }else {
                break;
            }
        }

        return r;
    }

    /**
     * 修改字符串
     * abc --> #a#b#c#
     */
    public static String treatString(String s){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length() * 2 + 1; i++) {
            if (i % 2 == 0){
                res.append("#");
            }else {
                res.append(s.charAt(i/2));
            }
        }

        return res.toString();
    }

}
