package challenge;


import java.util.*;

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
        //总钱数
        int money = in.nextInt();
        //购物数量
        int num = in.nextInt();
        //商品清单
        Map<Integer, Product> commodityMap = new HashMap<>();
        //商品主件-附件
        Map<Product, List<Product>> relation = new HashMap<>();
        int index = 1;
        //添加所有的商品，并构建主从关系
        for (int i = 0; i < num; i++) {
            Product product = new Product(in.nextInt(), in.nextInt(), in.nextInt());
            commodityMap.put(index,product);
            if (product.masterID == 0){
                //主件
                relation.put(product,new ArrayList<>());
            }else {
                //附件
                Product master = commodityMap.get(product.masterID);
                List<Product> products = relation.get(master);
                products.add(product);
            }
            index++;
        }


        //构建01背包
        int[][] dp = new int[num+1][money+1];
        for (int i = 1; i <= num; i++) {
            Product product = commodityMap.get(i);
            List<Product> salves = relation.get(product);
            int p0=0,p1=0,p2=0,p3=0,
                v0=0,v1=0,v2=0,v3=0;
            //只处理主件
            if (product.masterID == 0){

                //单个主件
                p0 = product.price;
                v0 = p0 * product.degree;

                //主件+附件1
                if (salves.size()>=1){
                    p1 = p0 + salves.get(0).price;
                    v1 = v0 + salves.get(0).price*salves.get(0).degree;
                }

                //主件+附件2 和 主件+附件1+附件2
                if (salves.size() >= 2){
                    p2 = p0 + salves.get(1).price;
                    v2 = v0 + salves.get(1).price*salves.get(1).degree;

                    p3 = p0 + salves.get(0).price + salves.get(1).price;
                    v3 = v0 + salves.get(0).price*salves.get(0).degree + salves.get(1).price*salves.get(1).degree;
                }
            }

            for (int j = 0; j<= money ; j++) {
                if (product.masterID != 0){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j];
                    //购买主件
                    if (j>=p0){
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p0]+v0);
                    }

                    //购买主件+附件1
                    if (j>=p1){
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p1]+v1);
                    }

                    //购买主件+附件2
                    if (j>=p2){
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p2]+v2);
                    }

                    //购买主件+附件1+附件2
                    if (j>=p3){
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-p3]+v3);
                    }
                }
            }
        }

        System.out.print(dp[num][money]);


    }

    private static class Product{
        int price;
        int degree;
        int masterID;

        public Product(int price, int degree, int masterID) {
            this.price = price;
            this.degree = degree;
            this.masterID = masterID;
        }
    }

}
