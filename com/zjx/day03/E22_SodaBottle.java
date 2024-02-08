package com.zjx.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-05 19:32:56
 * @Desc: 汽水瓶
 * 【描述】
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足
 * 1≤n≤100
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 * 【输入描述】：
 * 输入文件最多包含 10 组测试数据，每个数据占一行，仅包含一个正整数 n（ 1<=n<=100 ），
 * 表示小张手上的空汽水瓶数。n=0 表示输入结束，你的程序不应当处理这一行。
 * 【输出描述】：
 * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
 */
public class E22_SodaBottle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (in.hasNext()){
            int emptyBottle = in.nextInt();
            int soda = getSoda(emptyBottle);
            list.add(soda);
        }

        for (Integer res :list){
            if (res != 0){
                System.out.println(res);
            }
        }
    }

    public static int getSoda(int emptyBottle){
        if (emptyBottle == 2){
            return 1;
        }

        if (emptyBottle < 2){
            return 0;
        }

        return emptyBottle/3 + getSoda(emptyBottle % 3+emptyBottle / 3);
    }
}
