package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-06 19:23:12
 * @Desc: 素数伴侣
 * 【描述】
 * 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。
 * 现在密码学会请你设计一个程序，从已有的 N （ N 为偶数）个正整数中挑选出若干对组成“素数伴侣”，
 * 挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，
 * 而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，
 * 当然密码学会希望你寻找出“最佳方案”。
 * 【输入】:
 * 有一个正偶数 n ，表示待挑选的自然数的个数。后面给出 n 个具体的数字。
 * 【输出】:
 * 输出一个整数 K ，表示你求得的“最佳方案”组成“素数伴侣”的对数。
 * 数据范围：1≤n≤100  ，输入的数据大小满足2≤val≤30000
 * 【输入描述】：
 * 1 输入一个正偶数 n
 * 2 输入 n 个整数
 * 【输出描述】：
 * 求得的“最佳方案”组成“素数伴侣”的对数。
 */
public class E28_PrimePartner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = in.nextInt();
        }

        //奇偶分离
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                even.add(nums[i]);
            } else {
                odd.add(nums[i]);
            }
        }

        int res = 0;


    }

}
