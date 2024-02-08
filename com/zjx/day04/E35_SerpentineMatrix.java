package com.zjx.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-07 17:40:23
 * @Desc: 蛇形矩阵
 * 【描述】
 * 蛇形矩阵是由1开始的自然数依次排列成的一个矩阵上三角形。
 * 例如，当输入5时，应该输出的三角形为：
 * 1 3 6 10 15
 * 2 5 9 14
 * 4 8 13
 * 7 12
 * 11
 * 【输入描述】：
 * 输入正整数N（N不大于100）
 * 【输出描述】：
 * 输出一个N行的蛇形矩阵。
 */
public class E35_SerpentineMatrix {
    /**
     * 优化方案
     * 选两个点，初始位置都在（0，0）
     * 之后一个往右走，一个往下走，然后填写他们连城的线
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        //这里两个点共用一个指针就行了
        int index = 0;

        //创建容器
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            matrix.add(new ArrayList<>());
        }

        //开始存放数据
        int val = 1;
        for (int i = 0; i < size; i++) {
            while (index < size){
                int row = index;
                while (row >= 0){
                    matrix.get(row).add(val++);
                    row--;
                }
                index++;
            }
        }

        //输出
        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> row = matrix.get(i);
            for (Integer n : row) {
                System.out.print(n + " ");
            }
            if (i != matrix.size()-1){
                System.out.println();
            }
        }

    }


    /**
     * 算法一般：
     * 就正常一步一步走
     */
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int lines = in.nextInt();

        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < lines; i++) {
            matrix.add(new ArrayList<>());
        }

        int value = 1;

        for (int i = 0; i < lines; i++) {
            for (int row = 0; row <=i ; row++) {
                matrix.get(i-row).add(value++);
            }
        }

        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> row = matrix.get(i);
            for (Integer n : row) {
                System.out.print(n + " ");
            }
            if (i != matrix.size()-1){
                System.out.println();
            }
        }
    }
}
