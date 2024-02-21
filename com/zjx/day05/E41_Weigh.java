package com.zjx.day05;

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
    //set去重法
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] weights = new int[n];
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        /*
            思路分析：
            1.首先根据输入顺序，将砝码用数字序列表示，例如 2个 1g 和 1个 2g，就用 1 1 2 的序列表示
            2.使用 Set 表示加入当前砝码之前能产生的重量种类，set 初始化为 {0}；
              当第一个 1g 砝码放入时，则 set 中需要插入原先 set 中的所有元素 +1g后的结果，
              即 {0, 0+1}
              当第二个 1g 加入时，则 set 会插入 {0+1, 1+1}，所以 set 最终变为{0, 1, 2}
              重复上述步骤加入所有砝码
            3.最后 set 的大小即为能产生的重量种类
         */

        //1
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(weights[i]);
            }
        }

        Set<Integer> set = new HashSet<>();
        set.add(0);

        //2
        for (int i = 0; i < list.size(); i++) {
            List<Integer> temp = new ArrayList<>();
            for (Integer val : set) {
                temp.add(val + list.get(i));
            }

            set.addAll(temp);

            temp.clear();
        }

        System.out.println(set.size());

    }


    //动态规划法
    public static void main1(String[] args){
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

        /*
            思路分析：
            使用动态规划的方法
            1.将所有的砝码插入到list数组当中
            2.记砝码总重量为weightSum
            3.创建二维数组dp[list.size + 1][weightSum+1]，其中dp[i][j]表示使用前i个砝码，
              是否可以称出重量j
            4.边界条件：
              dp[i][0]：由于称出的重量都为0.所以一定为true
              dp[0][j](j>0)：不使用砝码，一定没有重量，所以一定为false
            5.动态转移方程：dp[i][j] = dp[i-1][j-weight] || dp[i-1][j]
              a.记第i个砝码的重量为weight
              b.若dp[i-1][j-weight]为true，则可以通过使用第i个砝码，使dp[i][j]为true
              c.若dp[i-1][j]为true，则不适用第i个砝码，也可以使d[i][j]为true
            6.将可行的重量放入set去重
            7.set中元素的个数，就是答案
         */

        //1.将所有的砝码插入到list数组当中
        List<Integer> list = new ArrayList<>();
        //2.记砝码总重量为weightSum
        int weightSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(weights[i]);
                weightSum += weights[i];
            }
        }

        //3.创建二维数组dp[list.size + 1][weightSum+1]，其中dp[i][j]表示使用前i个砝码，
        //  是否可以称出重量j
        //4.边界条件：
        //   dp[i][0]：由于称出的重量都为0.所以一定为true
        //   dp[0][j](j>0)：不使用砝码，一定没有重量，所以一定为false
        boolean[][] dp = new boolean[list.size()+1][weightSum+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        /*
            5.动态转移方程：dp[i][j] = dp[i-1][j-weight] || dp[i-1][j]
              a.记第i个砝码的重量为weight
              b.若dp[i-1][j-weight]为true，则可以通过使用第i个砝码，使dp[i][j]为true
              c.若dp[i-1][j]为true，则不适用第i个砝码，也可以使d[i][j]为true
            6.将可行的重量放入set去重
         */
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < dp.length; i++) {
            int weight = list.get(i-1);
            for (int j = 0; j < dp[0].length; j++) {
                if (j-weight >= 0){
                    dp[i][j] = dp[i][j] || dp[i-1][j-weight];
                }
                dp[i][j] = dp[i][j] || dp[i-1][j];

                if (dp[i][j]){
                    set.add(j);
                }
            }
        }


        System.out.println(set.size());

    }




}
