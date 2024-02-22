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
    public static void main(String[] args){



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
