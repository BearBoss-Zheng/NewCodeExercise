package com.zjx.test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zjx
 * @Date 2024-02-04 23:30:53
 * @Desc
 */
public class TestCode {
    public static void main(String[] args) {
        char[] str = "aC".toCharArray();
        Arrays.sort(str);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("C");
        list = list.stream().sorted().collect(Collectors.toList());
        System.out.println(list);
        int a = Integer.parseInt("C", 16);
        System.out.println(a);
        String s = "1";
        char c = s.charAt(0);
        char zero = '0';
        int i = 1;
        System.out.println((c-zero) == i);
    }

    // 上升子序列长度
    private static int[] getAscendingSubsequence(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    result[i] = Math.max(result[i], result[j] + 1);
                }
            }

        }
        return result;
    }

    // 递减子序列长度
    private static int[] getDescendingSubsequence(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = 1;
            for (int j = result.length - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    result[i] = Math.max(result[i], result[j] + 1);
                }
            }

        }
        return result;
    }
}
