package com.zjx.day02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-04 00:39:43
 * @Desc: 识别有效的IP地址和掩码并进行分类统计
 * 【描述】
 * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
 * 所有的IP地址划分为 A,B,C,D,E五类
 * A类地址从1.0.0.0到126.255.255.255;
 * B类地址从128.0.0.0到191.255.255.255;
 * C类地址从192.0.0.0到223.255.255.255;
 * D类地址从224.0.0.0到239.255.255.255；
 * E类地址从240.0.0.0到255.255.255.255
 *
 * 私网IP范围是：
 * 从10.0.0.0到10.255.255.255
 * 从172.16.0.0到172.31.255.255
 * 从192.168.0.0到192.168.255.255
 * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
 * （注意二进制下全是1或者全是0均为非法子网掩码）
 *
 * 注意：
 * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，
 *    也不属于不合法ip地址，计数时请忽略
 * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
 *
 * 【输入描述】：
 * 多行字符串。每行一个IP地址和掩码，用~隔开。
 *
 * 请参考帖子https://www.nowcoder.com/discuss/276处理循环输入的问题。
 * 【输出描述】：
 * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
 */
public class E18_ClassifyAndCountValidIPs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] res = new int[7];

        while (in.hasNextLine()){
            String[] ipAndSub = in.nextLine().split("~");
            String[] ip = ipAndSub[0].split("\\.");
            String[] subnet = ipAndSub[1].split("\\.");
            int ipType = classifyIP(ip);
            boolean subType = classifySubnet(subnet);
            if (ipType != 7){
                if (subType && ipType != 5){

                    if (ipType >= 10){
                        res[6]++;
                        res[ipType-10]++;
                    }else {
                        res[ipType]++;
                    }
                }else {
                    res[5]++;
                }
            }

        }

        for (int re : res) {
            System.out.print(re+" ");
        }

    }

    /**
     * 0-A,1-B,2-C,3-D,4-E,5-不合法,10-私有ip,7-特殊
     */
    public static int classifyIP(String[] ip){
        int res = 0;


        if (ip.length != 4){
            res = 5;
            return res;
        }

        int first = Integer.parseInt(ip[0]);

        if (1<=first && first <=126){
            res = 0;
        } else if (128<=first && first <=191) {
            res = 1;
        }else if (192<=first && first <=223) {
            res = 2;
        }else if (224<=first && first <=239) {
            res = 3;
        }else if (240<=first && first <=255) {
            res = 4;
        }else if (first == 0 || first == 127) {
            return 7;
        }

        int second = Integer.parseInt(ip[1]);
        if (    first == 10 ||
                (first==172 && 16<=second && second <= 31) ||
                (first == 192 && second == 168)){
            res = res + 10;
        }

        return res;


    }


    public static boolean classifySubnet(String[] subnet){
        if (subnet.length != 4){
            return false;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            String binaryString = Integer.toBinaryString(Integer.parseInt(subnet[i]));
            int length = binaryString.length();
            for (int j = 0; j < 8 - length; j++) {
                binaryString = "0" + binaryString;
            }
            sb.append(binaryString);
        }

        if (sb.toString().equals("11111111111111111111111111111111") || sb.toString().equals("00000000000000000000000000000000")){
            return false;
        }

        int index = 0;

        for (int i = 0; i <sb.length() ; i++) {
            if (sb.charAt(i) == '0'){
                index = i;
                break;
            }

        }

        for (int i = index+1; i <sb.length() ; i++) {
            if (sb.charAt(i) == '1'){
                return false;
            }
        }

        return true;


    }

}
