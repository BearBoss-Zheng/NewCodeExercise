package challenge;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-09 00:42:14
 * @Desc: 称砝码
 * 【描述】
 * 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
 * 每种砝码对应的数量为 x1,x2,x3...xn 。现在要用这些砝码去称物体的重量(放在同一侧)，
 * 问能称出多少种不同的重量。
 * 注：称重重量包括 0
 * 数据范围：每组输入数据满足 1≤n≤10  ，1≤ m ≤2000 ，1≤xi≤10
 * 【输入描述】：
 * 对于每组测试数据：
 * 第一行：n --- 砝码的种数(范围[1,10])
 * 第二行：m1 m2 m3 ... mn --- 每种砝码的重量(范围[1,2000])
 * 第三行：x1 x2 x3 .... xn --- 每种砝码对应的数量(范围[1,10])
 * 【输出描述】：
 * 利用给定的砝码可以称出的不同的重量数
 */
public class E41_Weigh {
    public static void main(String[] args){
        Scanner in  = new Scanner(System.in);
        int n = in.nextInt();
        int[] weights = new int[n];
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }


    }




}
