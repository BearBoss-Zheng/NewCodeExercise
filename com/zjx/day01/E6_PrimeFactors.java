package com.zjx.day01;


import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-02 15:53:23
 * @Desc：质数因子
 * 【描述】
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）
 * （如180的质因子为2 2 3 3 5 ）
 * 【数据范围】：
 * 1≤n≤2×10^9 +14
 * 【输入描述】：质数
 * 输入一个整数
 * 【输出描述】：
 * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。
 */
public class E6_PrimeFactors {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num <= 3){
            System.out.println(num);
        }

        int cur = num;

        StringBuilder str = new StringBuilder();
        for (int i = 2; i <= cur; i++) {
            if (isPrimeNumber(cur)){
                str.append(cur).append(" ");
                break;
            }

            if (isPrimeNumber(i)){
                while (cur % i == 0){
                    str.append(i).append(" ");
                    cur /= i;
                }
            }
        }

        System.out.println(str.toString());

    }

    public static boolean isPrimeNumber(int num){
        if (num == 2){
            return true;
        }

        if (num % 2 == 0 || num <= 1){
            return false;
        }

        int cur = 3;
        int sqrt =(int) Math.sqrt(num);

        while (cur <= sqrt){
            if (num % cur == 0){
                return false;
            }
            cur += 2;
        }

        return true;
    }
}
