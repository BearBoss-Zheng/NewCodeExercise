package challenge;

import java.util.Scanner;

/**
 * @author zjx
 * @Date 2024-02-07 12:44:41
 * @Desc: 密码截取
 * 【描述】
 * Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，
 * 比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
 * 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。
 * 因为截获的串太长了，而且存在多种可能的情况（abaaab可看作是aba,或baaab的加密形式），
 * Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
 * 数据范围：字符串长度满足 1≤n≤2500
 * 【输入描述】：
 * 输入一个字符串（字符串的长度不超过2500）
 * 【输出描述】：
 * 返回有效密码串的最大长度
 */
public class E32_InterceptPassword {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String str = in.nextLine();
            int manacher = Manacher(str);
            System.out.println(manacher);
        }

        in.close();

    }

    /**
     * 最长回文子串
     */
    public static int Manacher(String str){
        if (str == null || str.length() == 0){
            return 0;
        }

        //修改字符串(abc - #a#b#c#)
        char[] chs = modifyString(str);
        //当前的中心点
        int center = 0;
        //中心点的回文半径
        int r = 0;
        //当前计算的点
        int index = 1;
        //保存各点的回文半径
        int[] rs = new int[chs.length];
        //最大回文半径
        int R = 0;

        while (index < chs.length){
            //index不在center的范围内
            if (index >= center+r){
                //暴力扩张
                int curR = violentExpansion(chs, index, 0);
                rs[index] = curR;
            }else
            //index在center范围内
            {
                //index相对center的对称点
                int s = center - (index - center);
                int sr = rs[s];
                //对称点的回文刚在center的范围内
                if (index + sr < center + r){
                    rs[index] = sr;
                }else {
                    //对称点的回文在center的范围外，那么就要向外扩张了
                    int curR = violentExpansion(chs,index,center+r-index);
                    rs[index] = curR;
                }
            }
            //更新数据
            if (index + rs[index] > center + r){
                center = index;
                r = rs[index];
            }
            R = Math.max(R,rs[index]);
            index++;
        }

        return R;

    }

    /**
     * 暴力扩张，获取当前点的最大回文半径
     * @param chs 字符串
     * @param index 回文中点
     * @param r 已知的最长半径
     * @return 最大回文半径
     */
    public static int violentExpansion(char[] chs,int index,int r){
        while (index + (r+1) < chs.length //右边界
                && index - (r+1) >= 0  //左边界
                && chs[index + (r+1)] == chs[index-(r+1)] //满足回文条件
        ){
            r++;
        }

        return r;
    }



    /**
     * 修改字符串
     * abc - #a#b#c#
     */
    public static char[] modifyString(String str){
        if (str == null || str.length() == 0){
            return null;
        }

        char[] chs = new char[str.length()*2+1];
        for (int i = 0; i < chs.length; i++) {
            if (i % 2 ==0){
                chs[i] = '#';
            }else {
                chs[i] = str.charAt(i/2);
            }
        }

        return chs;
    }

}
