package com.zjx.day05;

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
    /*
        优化方案
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Long num = in.nextLong();

        final String[] NUMS =
                {"","one","two","three","four","five","six","seven","eight","nine",
                 "ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen",
                "seventeen","eighteen","nineteen"};
        final String[] TENS = {"","","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
        final String[] measure = {"hundred","thousand","million","billion"};

        List<String> pool = new ArrayList<>();
        int power = 0;

        while (num != 0){

            if (power != 0 ){
                pool.add(measure[power]);
            }

            //每次取三位处理
            int curNum = (int)(num % 1000);

            //如果小于20，直接度就行了
            if (curNum % 100 < 20){
                //十位个位，直接度就行了
                if (curNum % 100 != 0){
                    pool.add(NUMS[curNum % 100]);
                }

                //有百位
                if (curNum / 100 != 0){
                    //就要看看有没有and，只要后面两位不是0，就要有and
                    if (curNum % 100 != 0){
                        pool.add("and");
                    }

                    pool.add("hundred");
                    pool.add(NUMS[curNum / 100]);
                }

            }else {
                //添加个位
                if (curNum % 10 != 0){
                    pool.add(NUMS[curNum%10]);
                }

                //添加十位
                curNum /= 10;
                if (curNum % 10 >= 2){
                    pool.add(TENS[curNum % 10]);
                }

                //添加百位
                curNum /= 10;
                if (curNum > 0){
                    pool.add("and");
                    pool.add("hundred");
                    pool.add(NUMS[curNum]);
                }
            }

            power++;
            num /= 1000;
        }

        StringBuilder res = new StringBuilder();
        //添加数据得时候，pool从前往后，pool里面得list，从后往前
        for (int i = pool.size()-1; i >= 0; i--) {
                res.append(pool.get(i));

                if (i!=0){
                    res.append(" ");
                }

        }

        System.out.println(res.toString());

    }



    /*
        初始方式，不是很好
     */
    public static Map<Character,String> units = new HashMap<>();
    public static Map<Character,String> tens = new HashMap<>();
    public static Map<Character,String> oTen = new HashMap<>();
    public static Map<Integer,String> measure = new HashMap<>();

    public static void main1(String[] args) {
        //将个位数一一对应
        units.put('0',"");
        units.put('1',"one");
        units.put('2',"two");
        units.put('3',"three");
        units.put('4',"four");
        units.put('5',"five");
        units.put('6',"six");
        units.put('7',"seven");
        units.put('8',"eight");
        units.put('9',"nine");

        //十位数一一对应
        tens.put('0',"");
        tens.put('2',"twenty");
        tens.put('3',"thirty");
        tens.put('4',"forty");
        tens.put('5',"fifty");
        tens.put('6',"sixty");
        tens.put('7',"seventy");
        tens.put('8',"eighty");
        tens.put('9',"ninety");

        //10-19
        oTen.put('0',"ten");
        oTen.put('1',"eleven");
        oTen.put('2',"twelve");
        oTen.put('3',"thirteen");
        oTen.put('4',"fourteen");
        oTen.put('5',"fifteen");
        oTen.put('6',"sixteen");
        oTen.put('7',"seventeen");
        oTen.put('8',"eighteen");
        oTen.put('9',"nineteen");

        //单位
        measure.put(2,"thousand");
        measure.put(3,"million");
        measure.put(4,"billion");


        Scanner in = new Scanner(System.in);
        long num = in.nextLong();
        String data = String.valueOf(num);
        String res = processHole(data);
        System.out.println(res);
    }

    /**
     * 处理整个long数据
     * @param num
     * @return
     */
    public static String processHole(String num){
        int mod = num.length() % 3;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            sb.append(num.charAt(i));
            if ((i+1) % 3 == mod){
                sb.append(",");
            }
        }

        String[] split = sb.toString().split(",");
        StringBuilder res = new StringBuilder();
        int mea = split.length;
        if (mea == 0){
            return "";
        }

        for (int i = 0; i < split.length; i++) {
            String cur = processNum(split[i]);

            if (cur != null && cur.length() != 0){
                res.append(cur);
                if (measure.containsKey(mea)){
                    res.append(" ").append(measure.get(mea--));
                }
            }

            if (i != split.length -1 && !split[i+1].equals("000")){
                res.append(" ");
            }
        }

        return res.toString();


    }

    /**
     * 只处理三位数
     * @return 犯这个对应的数，如果为null，就代表0
     */
    public static String processNum(String str){

        if (str == null || str.length() == 0){
            return null;
        }

        str = String.valueOf(Integer.parseInt(str));

        StringBuilder res = new StringBuilder();
        //记录是否有百位
        boolean hFlag = false;
        //记录是否加过and
        boolean aFlag = false;
        //记录十位数是否为1
        boolean tFlag = false;
        //记录是有十位数
        boolean sFlag = false;
        for (int i = str.length()-1; i >= 0 ; i--) {
            char cur = str.charAt(str.length()-i-1);
            if (i == 2){
                hFlag = true;
                //百位数
                res.append(units.get(cur)).append(" ").append("hundred");
            }else if (i==1){

                //如果十位数不为0，那么直接加and
                if (cur != '0' && hFlag){
                    res.append(" and ");
                    aFlag = true;
                }

                //十位数
                if (cur != '1' && cur != '0'){
                    res.append(tens.get(cur)).append(" ");
                    sFlag = true;
                }else if(cur == '1'){
                    tFlag = true;
                }


            }else {

                //如果有百位，之前又没加and，且现在个位数不为0，那么久就加and
                if (hFlag && !aFlag && cur != '0'){
                    res.append(" and ");
                }

                if (sFlag && cur == '0'){
                    res.delete(res.length()-1,res.length());
                }

                //个位数
                if (tFlag){
                    //如果十位数为1
                    res.append(oTen.get(cur));
                }else {
                    res.append(units.get(cur));
                }
            }
        }

        return res.toString();
    }
}
