package com.zjx.day01;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-02 16:50:03
 * @Desc
 */
public class E7_GetApproximation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        float f = in.nextFloat();
        int i = (int)f;
        if (f >= i+0.5){
            System.out.println(i+1);
        }else {
            System.out.println(i);
        }
    }
}
