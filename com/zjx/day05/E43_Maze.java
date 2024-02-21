package com.zjx.day05;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-20 22:14:38
 * @Desc: 迷宫问题
 * 【描述】
 * 定义一个二维数组 N*M ，如 5 × 5 数组下所示：
 * int maze[5][5] = {
 *      0, 1, 0, 0, 0,
 *      0, 1, 1, 1, 0,
 *      0, 0, 0, 0, 0,
 *      0, 1, 1, 1, 0,
 *      0, 0, 0, 1, 0,
 * };
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要
 * 求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。
 * 【数据范围】：
 * 2≤n,m≤10 ，输入的内容只包含 0≤val≤1
 * 【输入描述】：
 * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。
 * 数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 * 【输出描述】：
 * 左上角到右下角的最短路径，格式如样例所示。
 * 输入：5 5
 *      0 1 0 0 0
 *      0 1 1 1 0
 *      0 0 0 0 0
 *      0 1 1 1 0
 *      0 0 0 1 0
 * 输出：(0,0)
 *      (1,0)
 *      (2,0)
 *      (2,1)
 *      (2,2)
 *      (2,3)
 *      (2,4)
 *      (3,4)
 *      (4,4)
 *
 */
public class E43_Maze {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] maze = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maze[i][j] = in.nextInt();
            }
        }

        List<String> res = new ArrayList<>();

        solveMaze2(maze,0,0,res);

        for (String re : res) {
            System.out.println(re);
        }

    }

    public static boolean solveMaze2(int[][] maze,int row,int col,List<String> list){
        maze[row][col] = 2;
        String s = String.format("(%d,%d)",row,col);
        list.add(s);

        //结束条件
        if (row == maze.length-1 && col == maze[0].length-1){
            return true;
        }

        //往右走
        if (col+1 < maze[0].length && maze[row][col+1] == 0){
            if (solveMaze2(maze,row,col+1,list)){
                return true;
            }
        }

        //往下走
        if (row+1 < maze.length && maze[row+1][col] == 0){
            if (solveMaze2(maze,row+1,col,list)){
                return true;
            }
        }

        //往左走
        if (col-1 >= 0 && maze[row][col-1] == 0){
            if (solveMaze2(maze,row,col-1,list)){
                return true;
            }
        }

        //往上走
        if (row-1 >= 0 && maze[row-1][col] == 0){
            if (solveMaze2(maze,row-1,col,list)){
                return true;
            }
        }

        maze[row][col] = -1;
        list.remove(list.size() - 1);

        return false;

    }

    /*
        方法一（不够好）：
        将当前走到的位置变成2，并添加到list离苦精当中
        然后按左，下，右，上的顺序找一个可以走的路，
        如果都不能走，就把当前的值设置为-1，表示这条路走不通，返回上一个点
        如果已经走到终点，那么就添加进去，退出程序
     */
    public static boolean solveMaze1(int[][] maze,int row,int col,List<String> list){
        maze[row][col] = 2;
        String s = String.format("(%d,%d)",row,col);
        list.add(s);
        boolean isSuccess = false;
        //走到终点
        if (col == maze[0].length-1 && row == maze.length-1){
            return true;
        }

        //往右走
        if (col+1 < maze[0].length && maze[row][col+1] == 0){
            isSuccess = solveMaze1(maze,row,col+1,list);
        }

        //往下走
        if (!isSuccess && row+1 < maze.length && maze[row+1][col] == 0){
            isSuccess = solveMaze1(maze,row+1,col,list);
        }

        //往左走
        if (!isSuccess && col-1 >= 0 && maze[row][col-1] == 0){
            isSuccess = solveMaze1(maze,row,col-1,list);
        }

        //往上走
        if (!isSuccess && row-1 >= 0 && maze[row-1][col] == 0){
            isSuccess = solveMaze1(maze,row-1,col,list);
        }

        //走不通
        if (!isSuccess){
            maze[row][col] = -1;
            list.remove(list.size()-1);
            return false;
        }else {
            return true;
        }


    }
}
