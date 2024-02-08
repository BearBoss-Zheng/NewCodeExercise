package com.zjx.day04;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-07 21:21:10
 * @Desc: 统计每个月兔子的总数
 * 【描述】
 * 有一种兔子，从出生后第3个月起每个月都生一只兔子，小兔子长到第三个月后每个月又生一只兔子。
 * 例子：假设一只兔子第3个月出生，那么它第5个月开始会每个月生一只兔子。
 * 一月的时候有一只兔子，假如兔子都不死，问第n个月的兔子总数为多少？
 * 数据范围：输入满足 1≤n≤31
 * 【输入描述】：
 * 输入一个int型整数表示第n个月
 * 【输出描述】：
 * 输出对应的兔子总数
 */
public class E37_CountRabbit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int months = in.nextInt();

        System.out.println(birthRabbit(months,1));
    }

    /**
     * T(n) = T(n-1) + T(n-2)
     * 这个月的兔子 = 上个月所有的兔子 + 2个月前成熟的兔子所出生的兔子（即三个月前所有的兔子）
     */
    public static int birthRabbit(int months,int rabbits){
        if (months <= 2){
            return rabbits;
        }

        return birthRabbit(months-1,rabbits) + birthRabbit(months-2,rabbits);
    }

}
