package challenge;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-09 00:42:14
 * @Desc: 称砝码
 * 【描述】
 * 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
 * 每种砝码对应的数量为 x1,x2,x3...xn 。现在要用这些砝码去称物体的重量(放在同一侧)，
 * 问能称出多少种不同的重量。
 * 注：称重重量包括 0
 * 数据范围：每组输入数据满足 1≤n≤10  ，1≤ m ≤2000 ，1≤xi≤10
 * 【输入描述】：
 * 对于每组测试数据：
 * 第一行：n --- 砝码的种数(范围[1,10])
 * 第二行：m1 m2 m3 ... mn --- 每种砝码的重量(范围[1,2000])
 * 第三行：x1 x2 x3 .... xn --- 每种砝码对应的数量(范围[1,10])
 * 【输出描述】：
 * 利用给定的砝码可以称出的不同的重量数
 */
public class E41_Weigh {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //砝码数量
        int n = in.nextInt();
        //重量表
        int[] weights = new int[n];
        //数量表
        int[] nums = new int[n];

        //填入重量
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
        }

        //填入数量
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        /*
            思路分析：
            1.首先根据输入顺序，将砝码用数字序列表示，例如 2个 1g 和 1个 2g，就用 1 1 2 的序列表示
            2.使用 Set 表示加入当前砝码之前能产生的重量种类，set 初始化为 {0}；
              当第一个 1g 砝码放入时，则 set 中需要插入原先 set 中的所有元素 +1g后的结果，
              即 {0, 0+1}
              当第二个 1g 加入时，则 set 会插入 {0+1, 1+1}，所以 set 最终变为{0, 1, 2}
              重复上述步骤加入所有砝码
            3.最后 set 的大小即为能产生的重量种类
         */

        //1
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(weights[i]);
            }
        }

        //2
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = 0; i < list.size(); i++) {
            List<Integer> temp = new ArrayList<>();
            for (Integer val : set) {
                temp.add(val + list.get(i));
            }

            set.addAll(temp);

            temp.clear();
        }

        //3
        System.out.println(set.size());

    }


}
