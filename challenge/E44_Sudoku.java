package challenge;

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
        int[][] chessboard = new int[9][9];

        //存放要填的空格
        List<Pos> list = new ArrayList<>();

        //填写棋盘数据和list
        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9; j++) {
                chessboard[i][j] = in.nextInt();
                if (chessboard[i][j] == 0){
                    list.add(new Pos(i,j));
                }
            }
        }

        //从第一个空格开始填写
        write(chessboard,list,0);

        //输出结果
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(chessboard[i][j]);
                if (j!=8){
                    System.out.print(" ");
                }else {
                    if (i!=8){
                        System.out.println();
                    }
                }
            }
        }

    }

    /**
     *
     * @param chessboard 棋盘
     * @param list 需要填写的位置
     * @param index 当前填写的是第几个空格
     * @return 当前方案是否可行
     */
    public static boolean write(int[][] chessboard,List<Pos> list,int index){
        //base case
        if (index == list.size()){
            return true;
        }

        //取出当前的位置
        Pos pos = list.get(index);

        //将1-9填入，这是数独可以填写的数
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            set.add(i);
        }

        //将所有已存在的数去掉（横轴，竖轴，3*3宫格内的数字）
        for (int i = 0; i < 9; i++) {
            //横轴
            set.remove(chessboard[pos.row][i]);
            //竖轴
            set.remove(chessboard[i][pos.col]);
        }

        //3*3
        int startRow = (pos.row/3) * 3;
        int startCol = (pos.col/3) * 3;
        for (int i = startRow; i < startRow+3; i++) {
            for (int j = startCol; j < startCol+3; j++) {
                set.remove(chessboard[i][j]);
            }
        }

        //此时set中剩余的数，都是当前可以填写的数
        for (Integer tryNum : set) {
            chessboard[pos.row][pos.col] = tryNum;
            if (write(chessboard,list,index+1)){
                return true;
            }
        }

        //没有所有数填进去都不行，那就是之前的值不对，进行回溯
        chessboard[pos.row][pos.col] = 0;
        return false;

    }

}
