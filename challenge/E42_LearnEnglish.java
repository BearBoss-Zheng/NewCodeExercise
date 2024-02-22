package challenge;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-19 17:00:18
 * @Desc:
 * Jessi初学英语，为了快速读出一串数字，编写程序将数字转换成英文：
 * 具体规则如下:
 * 1.在英语读法中三位数字看成一整体，后面再加一个计数单位。从最右边往左数，三位一单位，例如12,345 等
 * 2.每三位数后记得带上计数单位 分别是thousand, million, billion.
 * 3.公式：百万以下千以上的数 X thousand X, 10亿以下百万以上的数：X million X thousand X,
 *   10 亿以上的数：X billion X million X thousand X. 每个X分别代表三位数或两位数或一位数。
 * 4.在英式英语中百位数和十位数之间要加and，美式英语中则会省略，我们这个题目采用加上and，
 *   百分位为零的话，这道题目我们省略and
 * 下面再看几个数字例句：
 * 22: twenty two
 * 100:  one hundred
 * 145:  one hundred and forty five
 * 1,234:  one thousand two hundred and thirty four
 * 8,088:  eight thousand (and) eighty eight (注:这个and可加可不加，这个题目我们选择不加)
 * 486,669:  four hundred and eighty six thousand six hundred and sixty nine
 * 1,652,510:  one million six hundred and fifty two thousand five hundred and ten
 * 下面再看几个数字例句：
 * 22: twenty two
 * 100:  one hundred
 * 145:  one hundred and forty five
 * 1,234:  one thousand two hundred and thirty four
 * 8,088:  eight thousand (and) eighty eight (注:这个and可加可不加，这个题目我们选择不加)
 * 486,669:  four hundred and eighty six thousand six hundred and sixty nine
 * 1,652,510:  one million six hundred and fifty two thousand five hundred and ten
 * 【说明】：
 * 数字为正整数，不考虑小数，转化结果为英文小写；
 * 保证输入的数据合法
 * 关键字提示：and，billion，million，thousand，hundred。
 * 数据范围：1≤n≤2000000
 * 【输入描述】：
 * 输入一个long型整数
 * 【输出描述】：
 * 输出相应的英文写法
 */
public class E42_LearnEnglish {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //取到的long
        long num = in.nextLong();
        //1-19
        final String[] NUMS =
                {"","one","two","three","four","five","six","seven","eight","nine",
                 "ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen",
                 "seventeen","eighteen","nineteen"};
        //整10数
        final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        //度量单位
        final String[] measure = {"","thousand","million","billion"};

        List<String> list = new ArrayList<>();
        int power = 0;

        while (num > 0){
            if (power != 0){
                list.add(measure[power]);
            }
            //取第三位
            int curN =(int) num % 1000;
            if (curN % 100 < 20){
                //如果后两位小于二十，那么可以在NUMS中直接取
                if (curN % 100 != 0){
                    list.add(NUMS[curN % 100]);
                }

                //如果百位后面不为0，且有百位，那么就需要添加and
                if (curN / 100 != 0 && curN % 100 != 0){
                    list.add("and");
                }
                //如果有百位
                if (curN / 100 != 0){
                    list.add("hundred");
                    list.add(NUMS[curN/100]);
                }
            }else {
                //个位
                if (curN % 10 != 0){
                    list.add(NUMS[curN % 10]);
                }
                //十位
                curN/=10;
                if (curN%10 != 0){
                    list.add(TENS[curN % 10]);
                }

                //百位(由于后两位大于等于20，所以一定有and)
                curN /= 10;
                if (curN %10 != 0){
                    list.add("and");
                    list.add("hundred");
                    list.add(NUMS[curN % 10]);
                }
            }

            num /= 1000;
            power++;
        }

        for (int i = list.size()-1; i >= 0; i--) {
            System.out.print(list.get(i));
            if (i != 0){
                System.out.print(" ");
            }
        }

    }

}
