package challenge;


import java.util.*;
import java.util.regex.Pattern;

/**
 * @author zjx
 * @Date 2024-02-04 16:55:44
 * @Desc: 密码验证合格程序
 * 【描述】
 * 密码要求:
 * 1.长度超过8位
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 * 数据范围：输入的字符串长度满足
 * 1≤n≤100
 * 【输入描述】：
 * 一组字符串。
 * 【输出描述】：
 * 如果符合要求输出：OK，否则输出NG
 */
public class E20_VerifyPassword {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String s = in.nextLine();
            if (verify(s)){
                System.out.println("OK");
            }else {
                System.out.println("NG");
            }
        }

    }

    public static boolean verify(String s){

        return verifyLength(s) && verifyType(s) && verifySubString(s);

    }

    //1.验证长度
    public static boolean verifyLength(String s){
        //1.长度超过8位
        return s.length() > 8;
    }

    //2.验证种类
    public static boolean verifyType(String s){
        int type = 0;

        String lowRgex = ".*[A-Z].*";
        String upRegex = ".*[a-z].*";
        String numRegex  = ".*\\d.*";
        String otherRegex = ".*[^a-zA-Z\\d].*";

        if (Pattern.compile(lowRgex).matcher(s).matches()){
            type++;
        }

        if (Pattern.compile(upRegex).matcher(s).matches()){
            type++;
        }

        if (Pattern.compile(numRegex).matcher(s).matches()){
            type++;
        }

        if (Pattern.compile(otherRegex).matcher(s).matches()){
            type++;
        }

        return type >= 3;

    }

    //3.验证最大公共子串
    public static boolean verifySubString(String s){
        Map<String,Integer> map = new HashMap<>();
        int index = 0;
        while (index < s.length()-2){
            String cur = s.substring(index,index+3);
            if (map.containsKey(cur)){
                //aaaaaaa合法
                //aaaaaaabaaaaaa不合法
                //所以需要分类讨论，如果是字串内部元素不同，那么直接返回false，
                //如果内部元素相同，那么就要看中间的元素是否也相同
                if (!allCharactersSame(cur)){
                    return false;
                }else {
                    int pre = map.get(cur);
                    if (!allCharactersSame(s.substring(pre,index))){
                        return false;
                    }else {
                        map.put(cur,index);
                    }
                }

            }else {
                map.put(cur,index);
            }

            index++;
        }

        return true;
    }

    public static boolean allCharactersSame(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        char firstChar = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != firstChar) {
                return false;
            }
        }

        return true;
    }
}
