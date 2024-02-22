package challenge;


import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-05 20:35:16
 * @Desc: 合唱队
 * 【描述】
 * N 位同学站成一排，音乐老师要请最少的同学出列，使得剩下的 K 位同学排成合唱队形。
 * 设K位同学从左到右一次编号为1，2...,K,他们的身高分别位T1、T2...Tk，若存在i(1 ≤ i ≤ k)
 * 使得T1<T2<...<Ti 且 Ti>Ti+1>...>Tk,则成这K名同学排成了合唱队形
 * 通俗来说，能找到一个同学，他的两边的同学身高都依次严格降低的队形就是合唱队形。
 * 例子：
 * 123 124 125 123 121 是一个合唱队形
 * 123 123 124 122不是合唱队形，因为前两名同学身高相等，不符合要求
 * 123 122 121 122不是合唱队形，因为找不到一个同学，他的两侧同学身高递减。
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 * 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
 * 数据范围：
 * 1≤n≤3000
 * 【输入描述】：
 * 用例两行数据，第一行是同学的总数 N ，第二行是 N 位同学的身高，以空格隔开
 * 【输出描述】：
 * 最少需要几位同学出列
 */
public class E24_Chorus {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //人数
        int nums = in.nextInt();
        //身高
        int[] heights = new int[nums];
        for (int i = 0; i < nums; i++) {
            heights[i] = in.nextInt();
        }

        int[] pre = getAscSub(heights);
        //反转数组
        int[] reverseHeight = new int[nums];
        for (int i = 0; i < nums; i++) {
            reverseHeight[i] = heights[nums-i-1];
        }

        int[] back = getAscSub(reverseHeight);

        int lcp = 0;

        for (int i = 1; i < heights.length - 1; i++) {
            lcp = Math.max(lcp,pre[i]+back[nums-i-1]-1);
        }

        System.out.print(nums-lcp);
    }

    //获取最长子序列（增）
    public static int[] getAscSub(int[] heights){
        //第一行为最长子序列的
        int[] res = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            res[i] = 1;
            for (int j = 0; j < i; j++) {
                if(heights[i] > heights[j]){
                    res[i] = Math.max(res[i],res[j]+1);
                }
            }
        }

        return res;
    }



}
