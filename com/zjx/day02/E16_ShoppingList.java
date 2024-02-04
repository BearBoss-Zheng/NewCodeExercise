package com.zjx.day02;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-03 21:09:23
 * @Desc: 购物单
 * 【描述】：
 * 王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，
 * 下表就是一些主件与附件的例子：
 * 主件	        附件
 * 电脑	        打印机，扫描仪
 * 书柜	        图书
 * 书桌	        台灯，文具
 * 工作椅	    无
 * 如果要买归类为附件的物品，必须先买该附件所属的主件，且每件物品只能购买一次。
 * 每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
 * 王强查到了每件物品的价格（都是 10 元的整数倍），而他只有 N 元的预算。
 * 除此之外，他给每件物品规定了一个重要度，用整数 1 ~ 5 表示。他希望在花费不超过 N 元的前提下，
 * 使自己的满意度达到最大。
 * 满意度是指所购买的每件物品的价格与重要度的乘积的总和，假设第i件物品的价格为v[i]，重要度为w[i]，
 * 共选中了k件物品，编号依次为j1,j2...jk，则满意度为v[j1]*w[j1]+...+v[jk]*w[jk]
 * 【输入描述】
 * 输入的第 1 行，为两个正整数N，m，用一个空格隔开：
 * （其中 N （ N<32000 ）表示总钱数， m （m <60 ）为可购买的物品的个数。）
 * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
 * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ），
 * q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件，
 * q 是所属主件的编号）
 * 【输出描述】：
 * 输出一个正整数，为张强可以获得的最大的满意度。
 */
public class E16_ShoppingList {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //商品清单，key为编号，value为商品的价格和满意度
        Map<Integer,Product> productMap = new HashMap<>();


        int money = in.nextInt();
        int nums = in.nextInt();
        int index = 1;

        //添加所有商品信息
        for (int i = 0; i < nums; i++) {
            int price = in.nextInt();
            int degree = in.nextInt();
            int master = in.nextInt();
            productMap.put(index++,new Product(price,degree,master));
        }

        //为主键添加附件
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            int id = entry.getKey();
            Product product = entry.getValue();
            if (product.master != 0){
                //获取附件的主键标号
                Product master = productMap.get(product.master);
                if (master.s1 == 0){
                    master.s1 = id;
                }else {
                    master.s2 = id;
                }
            }
        }


        //动态规划数组
        int[][] dp = new int[nums+1][money+1];

        //有点类似 01背包问题
        for (int i = 1; i < dp.length; i++) {
            Product product = productMap.get(i);
            int p=0,p1=0,p2=0,p3=0,v=0,v1=0,v2=0,v3=0;
            //当前是主件
            if (product.master == 0){
                p = product.price;
                v = product.degree * product.price;

                if (product.s1 != 0){//主件+附件1
                    p1 = p + productMap.get(product.s1).price;
                    v1 = v + productMap.get(product.s1).price * productMap.get(product.s1).degree;
                }

                if (product.s2 != 0){//主件+附件2
                    p2 = p + productMap.get(product.s2).price;
                    v2 = v + productMap.get(product.s2).price * productMap.get(product.s2).degree;
                }

                if (product.s1 != 0 && product.s2 != 0){//主件+附件1+附件2
                    p3 = p + productMap.get(product.s1).price +productMap.get(product.s2).price ;
                    v3 = v + productMap.get(product.s1).price * productMap.get(product.s1).degree + productMap.get(product.s2).price * productMap.get(product.s2).degree;
                }
            }

            for (int j = 10; j < money + 1; j++) {
                //附件直接跳过
                if (product.master != 0){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j];
                    if (p <= j){//主件
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p]+v);
                    }

                    if (p1 <= j){//主件+附件1
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p1]+v1);
                    }

                    if (p2 <= j){//主件+附件2
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p2]+v2);
                    }

                    if (p3 <= j){//主件+附件1+附件2
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p3]+v3);
                    }
                }

            }
        }

        System.out.println(dp[nums][money]);

    }

    public static class Product{
        int price;
        int degree;
        int master;

        int s1;//附件1
        int s2;//附件2
        public Product(int price, int degree,int master) {
            this.price = price;
            this.degree = degree;
            this.master = master;
        }
    }
}
