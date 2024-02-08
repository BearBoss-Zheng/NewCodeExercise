package com.zjx.day03;


import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-05 20:35:16
 * @Desc: 合唱队
 * 【描述】
 * N 位同学站成一排，音乐老师要请最少的同学出列，使得剩下的 K 位同学排成合唱队形。
 * 设K位同学从左到右一次编号为1，2...,K,他们的身高分别位T1、T2...Tk，若存在i(1 ≤ i ≤ k)
 * 使得T1<T2<...<Ti 且 Ti>Ti+1>...>Tk,则成这K名同学排成了合唱队形
 * 通俗来说，能找到一个同学，他的两边的同学身高都依次严格降低的队形就是合唱队形。
 * 例子：
 * 123 124 125 123 121 是一个合唱队形
 * 123 123 124 122不是合唱队形，因为前两名同学身高相等，不符合要求
 * 123 122 121 122不是合唱队形，因为找不到一个同学，他的两侧同学身高递减。
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 * 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
 * 数据范围：
 * 1≤n≤3000
 * 【输入描述】：
 * 用例两行数据，第一行是同学的总数 N ，第二行是 N 位同学的身高，以空格隔开
 * 【输出描述】：
 * 最少需要几位同学出列
 */
public class E24_Chorus {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        //正常顺序的身高记录
        int[] heights = new int[num];
        //反向顺序身高记录
        int[] reverseHeight = new int[num];

        for (int i = 0; i < num; i++) {
            heights[i] = in.nextInt();
        }

        for (int i = 0; i < heights.length; i++) {
            reverseHeight[num-i-1] = heights[i];
        }

        int[] asc = getAscSub(heights);
        int[] desc = getAscSub(reverseHeight);

        int res = 0;

        for (int i = 0; i < num; i++) {
            res = Math.max(res,asc[i]+desc[num-i-1]-1);
        }


        System.out.println(num- res);

        in.close();
    }


    /**
     * 获取最长单调子序列（增）
     */
    public static int[] getAscSub(int[] height){
        int[] res = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            res[i] = 1;
            for (int j = 0; j < i; j++) {
                if (height[j] < height[i]){
                    res[i] = Math.max(res[i],res[j]+1 );
                }
            }
        }

        return res;
    }


    //递归方法（花了一堆时间，写了一堆垃圾，****）
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] heights = new int[num];

        for (int i = 0; i < num; i++) {
            heights[i] = in.nextInt();
        }

        /*
            思路分析：
            以某一个人为基准点，看看维持她前面的数严格单调增，需要去除多少人
            再看位置这个人后面的数单调减，需要去除多少人，选择一个最小的
            less[0] = 0; 3 2 4 2 2
            height[i] > height[i-1] less[i] = less[i]-1;
            height[i] > height[i-1]
         */
        int[] ascend = new int[heights.length];
        int[] descend = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            int del = 0;
            List<Integer> list1 = new ArrayList<>();
            for (int j = 0; j <= i-1; j++) {
                list1.add(heights[j]);
            }
            ascend[i] = i==0 ? 0:sortAsc(list1,list1.get(0),0,heights[i]);

            List<Integer> list2 = new ArrayList<>();
            for (int j = i+1; j < heights.length; j++) {
                list2.add(heights[j]);
            }
            descend[i] = sortDesc(list2,heights[i],0);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < heights.length; i++) {
            res = Math.min(res,descend[i] + ascend[i]);
        }

        System.out.println(res);
    }

    /**
     * 排成单调增需要删除多少元素
     */
    public static int sortAsc(List<Integer> nums,int min,int hasDel,int standrad){
        if (nums.size() < 1){
            return 0;
        }


        boolean flag = true;
        int index = 0;
        /*
             1 7 5 1 2 5 9
         */
        //删除所有比最大值大的
        while (index < nums.size()){
            if (nums.get(index) >= standrad){
                nums.remove(index);
                hasDel++;
            }else {
                index++;
            }
        }

        //查看是否单调增
        for (int i = 0; i < nums.size()-1; i++) {
            if (nums.get(i) >= nums.get(i+1)){
                flag = false;
                break;
            }
        }


        if (!flag){
            List<Integer> newNum = new ArrayList<>();
            for (int i = 1; i <nums.size(); i++) {
                newNum.add(nums.get(i));
            }
            //如果不是单调增，就看现在数是否大于min
            if (nums.get(0) > min){
                //有两张方案
                //1.删除当前数
                int newMin = nums.get(0);
                int del = sortAsc(newNum,min,hasDel+1,standrad);
                int save = sortAsc(newNum,newMin,hasDel,standrad);
                return Math.min(del,save);
            }else {
                //当前第一个值大于最小值，那么就必须删
                return sortAsc(newNum,min,hasDel+1,standrad);
            }
        }

        return hasDel;
    }

    /**
     * 排成单调增需要删除多少元素
     */
    public static int sortDesc(List<Integer> nums,int max,int hasDel){
        if (nums.size() < 1){
            return 0;
        }

        boolean flag = true;
        int index = 0;
        /*
             1 7 5 1 2 5 9
         */
        //删除所有比最大值大的
        while (index < nums.size()){
            if (nums.get(index) >= max){
                nums.remove(index);
                hasDel++;
            }else {
                index++;
            }
        }

        //查看是否单调减
        for (int i = 0; i < nums.size()-1; i++) {
            if (nums.get(i) <= nums.get(i+1)){
                flag = false;
                break;
            }
        }


        if (!flag){
            //如果不是单调减，就看现在数是否小于max
            List<Integer> newNum = new ArrayList<>();
            for (int i = 1; i <nums.size(); i++) {
                newNum.add(nums.get(i));
            }
            if (nums.get(0) < max){
                //有两张方案
                //1.删除当前数
                int newMax = nums.get(0);
                int del = sortDesc(newNum,max,hasDel+1);
                int save = sortDesc(newNum,newMax,hasDel);
                return Math.min(del,save);
            }else {
                //当前第一个值大于最小值，那么就必须删
                return sortDesc(newNum,max,hasDel+1);
            }
        }

        return hasDel;
    }
}
