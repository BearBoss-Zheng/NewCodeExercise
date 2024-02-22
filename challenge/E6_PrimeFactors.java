package challenge;

import java.util.ArrayList;
import java.util.List;
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
 * 【输入描述】：
 * 输入一个整数
 * 【输出描述】：
 * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。
 */
public class E6_PrimeFactors {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        StringBuilder res = new StringBuilder();
        int cur = 2;
        while (num > 1 && cur <= num){

            if (isPrime(num)){
                res.append(num).append(" ");
                break;
            }

            if (num % cur == 0 && isPrime(cur)){
               res.append(cur).append(" ");
               num /= cur;
            }else {
                cur++;
            }
        }
        System.out.print(res.toString());
    }

    public static boolean isPrime(int num){
        //2为质数
        if (num == 2){
            return true;
        }

        //小于2,以及除2以外的偶数，都不是质数
        if (num < 2 || num % 2 == 0){
            return false;
        }

        //一个数如果不是质数，那么一定存在  xy = n，x<=sqrt,y>=sqrt,(x,y!=n)
        int sqrt = (int) Math.sqrt(num);

        for (int i = 3; i <= sqrt; i+=2) {
            if (num % i == 0){
                return false;
            }
        }

        return true;
    }

}
