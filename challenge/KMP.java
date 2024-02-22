package challenge;

/**
 * @author zjx
 * @Date 2024-02-21 22:54:40
 * @Desc
 */
public class KMP {
    public static void main(String[] args) {
        String s = "asdgfasdfgkjhwabjndagbsfdasflkajasdfg";
        String m = "fgk";
        System.out.println(getIndexOf(s,m));
    }

    public static int getIndexOf(String s,String m){
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }

        int sIndex = 0;
        int mIndex = 0;
        //m的加速数组
        int[] nextArr = getNextArr(m);
        while (sIndex < s.length() && mIndex < m.length()){
            if (s.charAt(sIndex) == m.charAt(mIndex)){
                sIndex++;
                mIndex++;
            }else if (mIndex > 0){
                mIndex = nextArr[mIndex];
            }else {
                sIndex++;
            }
        }

        return mIndex == m.length() ? sIndex - mIndex : -1;

    }


    /**
     * 加速数组
     * int[i] 保存的是i位置的最大前后缀长度
     */
    public static int[] getNextArr(String str){
        if (str == null || str.length() == 0){
            return null;
        }

        if (str.length() == 1){
            return new int[]{-1};
        }

        int[] res = new int[str.length()];
        res[0] = -1;
        res[1] = 0;
        int i = 2;
        int preIndex = 0;//前缀长度指标

        while (i < str.length()){
            if (str.charAt(i-1) == str.charAt(preIndex)){
                res[i] = res[i-1]+1;
                i++;
                preIndex++;
            }else if (preIndex > 0){
                preIndex = res[preIndex];
            }else {
                res[i] = 0;
                i++;
            }
        }

        return res;

    }
}
