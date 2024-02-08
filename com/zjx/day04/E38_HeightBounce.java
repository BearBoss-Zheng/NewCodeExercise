package com.zjx.day04;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-07 22:06:40
 * @Desc
 * 描述
 * 假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半;
 * 再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高？
 * 数据范围：输入的小球初始高度满足1≤n≤1000  ，且保证是一个整数
 * 输入描述：
 * 输入起始高度，int型
 * 输出描述：
 * 分别输出第5次落地时，共经过多少米以及第5次反弹多高。
 * 注意：你可以认为你输出保留六位或以上小数的结果可以通过此题。
 */
public class E38_HeightBounce {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int startHeight = in.nextInt();
        DecimalFormat format = new DecimalFormat("#.######");
        float fifthBounce = (float) (startHeight / Math.pow(2,5));
        float fifthLanding = 0f;
        for (int i = 0; i < 5; i++) {
            if (i == 0){
                fifthLanding += startHeight;
            }else {
                fifthLanding  +=  2* startHeight*(float)((Math.pow(0.5,i)));
            }
        }

        System.out.println(format.format(fifthLanding));
        System.out.println(format.format(fifthBounce));

        in.close();

    }


}
