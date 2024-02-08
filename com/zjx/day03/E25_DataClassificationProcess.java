package com.zjx.day03;



import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zjx
 * @Date 2024-02-06 12:29:05
 * @Desc: 数据分类处理
 * 【描述】
 * 信息社会，有海量的数据需要分析处理，比如公安局分析身份证号码、 QQ 用户、手机号码、银行帐号等信息
 * 及活动记录。
 * 采集输入大数据和分类规则，通过大数据分类处理程序，将大数据分类输出。
 * 数据范围：1≤I,R≤100  ，输入的整数大小满足 0≤val≤2^31−1
 * 【输入描述】：
 * 一组输入整数序列I和一组规则整数序列R，I和R序列的第一个整数为序列的个数（个数不包含第一个整数）；
 * 整数范围为0~(2^31)-1，序列个数不限
 * 【输出描述】：
 * 从R依次中取出R<i>，对I进行处理，找到满足条件的I：
 * I整数对应的数字需要连续包含R<i>对应的数字。比如R<i>为23，I为231，那么I包含了R<i>，条件满足 。
 * 按R<i>从小到大的顺序:
 * (1)先输出R<i>；
 * (2)再输出满足条件的I的个数；
 * (3)然后输出满足条件的I在I序列中的位置索引(从0开始)；
 * (4)最后再输出I。
 * 附加条件：
 * (1)R<i>需要从小到大排序。相同的R<i>只需要输出索引小的以及满足条件的I，索引大的需要过滤掉
 * (2)如果没有满足条件的I，对应的R<i>不用输出
 * (3)最后需要在输出序列的第一个整数位置记录后续整数序列的个数(不包含“个数”本身)
 * 序列I：15,123,456,786,453,46,7,5,3,665,453456,745,456,786,453,123（第一个15表明后续有15个整数）
 * 序列R：5,6,3,6,3,0（第一个5表明后续有5个整数）
 * 输出：30, 3,6,0,123,3,453,7,3,9,453456,13,453,14,123,6,7,1,456,2,786,4,46,8,665,9,453456,11,456,12,786
 * 说明：
 * 30----后续有30个整数
 * 3----从小到大排序，第一个R<i>为0，但没有满足条件的I，不输出0，而下一个R<i>是3
 * 6--- 存在6个包含3的I
 * 0--- 123所在的原序号为0
 * 123--- 123包含3，满足条件
 *
 * 输入：
 * 15 123 456 786 453 46 7 5 3 665 453456 745 456 786 453 123
 * 5 6 3 6 3 0
 * 输出：
 * 30 3 6 0 123 3 453 7 3 9 453456 13 453 14 123 6 7 1 456 2 786 4 46 8 665 9 453456 11 456 12 786
 * 说明：
 * 将序列R：5,6,3,6,3,0（第一个5表明后续有5个整数）排序去重后，可得0,3,6。
 * 序列I没有包含0的元素。
 * 序列I中包含3的元素有：I[0]的值为123、I[3]的值为453、I[7]的值为3、I[9]的值为453456、I[13]的值为453、I[14]的值为123。
 * 序列I中包含6的元素有：I[1]的值为456、I[2]的值为786、I[4]的值为46、I[8]的值为665、I[9]的值为453456、I[11]的值为456、I[12]的值为786。
 * 最后按题目要求的格式进行输出即可。
 */
public class E25_DataClassificationProcess {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ISize = in.nextInt();
        int[] I = new int[ISize];
        for (int i = 0; i < ISize; i++) {
            I[i] = in.nextInt();
        }

        int RSize = in.nextInt();
        List<Integer> R = new ArrayList<>();
        Map<Integer,List<Integer>> RIMap = new HashMap<>();
        for (int i = 0; i < RSize; i++) {
            int cur = in.nextInt();
            //去重
            if (!R.contains(cur)){
                R.add(cur);
                RIMap.put(cur,new ArrayList<>());
            }
        }

        R = R.stream().sorted().collect(Collectors.toList());

        int totalNum = 0;
        StringBuilder res = new StringBuilder();

        for (Integer r : R) {
            List<Integer> IList = RIMap.get(r);
            String curR = String.valueOf(r);
            for (int j = 0; j < I.length; j++) {
                String curI = String.valueOf(I[j]);
                //取出I中的数，查看是否包含R<i>
                if (KMP(curI, curR)) {
                    //如果符合要求，那么就添加
                    IList.add(j);
                }
            }

            if (IList.size() != 0){
                totalNum = totalNum + 2 + 2 * IList.size();
                res.append(r).append(" ") //添加当前的R
                    .append(IList.size()).append(" ");//添加满足要求的数的数量
                for (Integer index : IList) {
                    //添加索引，然后是具体的数
                    res.append(index).append(" ")
                            .append(I[index]).append(" ");
                }
            }
        }

        System.out.println(totalNum + " "+res.toString());

        in.close();
    }

    public static boolean KMP(String I,String R){
        if (R == null || R.length()==0){
            return false;
        }

        int[] partialMatchTable = getPartialMatchTable(R);
        int IIndex = 0;
        int RIndex = 0;
        while (IIndex < I.length() && RIndex < R.length()){
            if (I.charAt(IIndex) == R.charAt(RIndex)){
                IIndex++;
                RIndex++;
            }else if (RIndex > 0){
                RIndex = partialMatchTable[RIndex];
            }else {
                IIndex++;
            }
        }

        return RIndex == R.length();
    }

    /**
     * KMP算法的核心是构建一个部分匹配表（Partial Match Table），简称PMTable或者Next数组。
     * 对于每个位置，计算其前缀和后缀的最长公共部分长度。
     * 注意前缀和后缀，都是从前往后的，不要把后缀当作从后往前。
     */
    public static int[] getPartialMatchTable(String R){
        if (R == null || R.length() == 0){
            return null;
        }

        char[] chs = R.toCharArray();

        if (chs.length == 1){
            return new int[]{-1};
        }

        int[] res = new int[chs.length];
        res[0] = -1;
        res[1] = 0;
        int compareIndex = 0;
        int curIndex = 2;

        while (curIndex<chs.length){
            if (chs[compareIndex] == chs[curIndex-1]){
                res[curIndex] = res[curIndex-1]+1;
                compareIndex++;
                curIndex++;
            }else if (compareIndex >0){
                compareIndex = res[compareIndex];
            }else {
                res[curIndex++] = 0;
            }
        }

        return res;

    }


}
