package com.zjx.day05;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-21 00:03:31
 * @Desc:数独
 * 问题描述：数独（Sudoku）是一款大众喜爱的数字逻辑游戏。玩家需要根据9X9盘面上的已知数字，
 * 推算出所有剩余空格的数字，并且满足每一行、每一列、每一个3X3粗线宫内的数字均含1-9，并且不重复。
 * 例如：
 * 输入
 */
public class E44_Sudoku {
    private static class Pos{
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] nums = new int[9][9];
        //记录空位
        List<Pos> list = new ArrayList<>();
        //填数据
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                nums[i][j] = in.nextInt();
                if (nums[i][j] == 0){
                    list.add(new Pos(i,j));
                }
            }
        }

        write(nums,list,0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(nums[i][j]);
                if (j != 8){
                    System.out.print(" ");
                }else {
                    if (i != 8){
                        System.out.println();
                    }
                }
            }

        }


    }

    public static boolean write(int[][] nums,List<Pos> list,int index){
        if (index == list.size()){
            return true;
        }
        Pos pos = list.get(index);


        //先看看这个位置能填什么数
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            set.add(i);
        }
        
        //去掉横着的和竖着的
        for (int i = 0; i < 9; i++) {
            set.remove(nums[pos.row][i]);
            set.remove(nums[i][pos.col]);
        }

        //去掉方格内的
        int startR = pos.row/3 * 3;
        int startC = pos.col/3 * 3;
        for (int i = startR; i <startR+3 ; i++) {
            for (int j = startC; j <startC+3 ; j++) {
                set.remove(nums[i][j]);
            }
        }

        //剩下的set就是可以填进去的数
        for (Integer tryNum : set) {
            nums[pos.row][pos.col] = tryNum;
            if (write(nums,list,index+1)){
                return true;
            }
        }

        //都填了都不行，说明之前的数不对
        nums[pos.row][pos.col] = 0;
        return false;


    }


}
