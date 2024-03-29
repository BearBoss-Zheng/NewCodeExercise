package challenge;



import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zjx
 * @Date 2024-02-06 12:29:05
 * @Desc: 数据分类处理
 * 【描述】
 * 信息社会，有海量的数据需要分析处理，比如公安局分析身份证号码、 QQ 用户、手机号码、银行帐号等信息
 * 及活动记录。
 * 采集输入大数据和分类规则，通过大数据分类处理程序，将大数据分类输出。
 * 数据范围：1≤I,R≤100  ，输入的整数大小满足 0≤val≤2^31−1
 * 【输入描述】：
 * 一组输入整数序列I和一组规则整数序列R，I和R序列的第一个整数为序列的个数（个数不包含第一个整数）；
 * 整数范围为0~(2^31)-1，序列个数不限
 * 【输出描述】：
 * 从R依次中取出R<i>，对I进行处理，找到满足条件的I：
 * I整数对应的数字需要连续包含R<i>对应的数字。比如R<i>为23，I为231，那么I包含了R<i>，条件满足 。
 * 按R<i>从小到大的顺序:
 * (1)先输出R<i>；
 * (2)再输出满足条件的I的个数；
 * (3)然后输出满足条件的I在I序列中的位置索引(从0开始)；
 * (4)最后再输出I。
 * 附加条件：
 * (1)R<i>需要从小到大排序。相同的R<i>只需要输出索引小的以及满足条件的I，索引大的需要过滤掉
 * (2)如果没有满足条件的I，对应的R<i>不用输出
 * (3)最后需要在输出序列的第一个整数位置记录后续整数序列的个数(不包含“个数”本身)
 * 序列I：15,123,456,786,453,46,7,5,3,665,453456,745,456,786,453,123（第一个15表明后续有15个整数）
 * 序列R：5,6,3,6,3,0（第一个5表明后续有5个整数）
 * 输出：30, 3,6,0,123,3,453,7,3,9,453456,13,453,14,123,6,7,1,456,2,786,4,46,8,665,9,453456,11,456,12,786
 * 说明：
 * 30----后续有30个整数
 * 3----从小到大排序，第一个R<i>为0，但没有满足条件的I，不输出0，而下一个R<i>是3
 * 6--- 存在6个包含3的I
 * 0--- 123所在的原序号为0
 * 123--- 123包含3，满足条件
 *
 * 输入：
 * 15 123 456 786 453 46 7 5 3 665 453456 745 456 786 453 123
 * 5 6 3 6 3 0
 * 输出：
 * 30 3 6 0 123 3 453 7 3 9 453456 13 453 14 123 6 7 1 456 2 786 4 46 8 665 9 453456 11 456 12 786
 * 说明：
 * 将序列R：5,6,3,6,3,0（第一个5表明后续有5个整数）排序去重后，可得0,3,6。
 * 序列I没有包含0的元素。
 * 序列I中包含3的元素有：I[0]的值为123、I[3]的值为453、I[7]的值为3、I[9]的值为453456、I[13]的值为453、I[14]的值为123。
 * 序列I中包含6的元素有：I[1]的值为456、I[2]的值为786、I[4]的值为46、I[8]的值为665、I[9]的值为453456、I[11]的值为456、I[12]的值为786。
 * 最后按题目要求的格式进行输出即可。
 */
public class E25_DataClassificationProcess {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numI = in.nextInt();
        String[] I = new String[numI];
        //收集数据I
        for (int i = 0; i < I.length; i++) {
            I[i] = String.valueOf(in.nextInt());
        }

        //收集数据R，利用TreeSet去重+
        int numR = in.nextInt();
        Set<String> R = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.parseInt(s1) - Integer.parseInt(s2);
            }
        });
        for (int i = 0; i < numR; i++) {
            R.add(in.next());
        }

        //保存I数据，坐标和数值
        List<String> I_List = new ArrayList<>();
        //保存R数据，Ri和数量，每一轮结束，会将I_List也合并进来，这样就是结果了
        List<String> R_List = new ArrayList<>();

        for (String Ri : R) {
            int count = 0;
            for (int i = 0; i < I.length; i++) {
                if (I[i].contains(Ri)){
                    count++;
                    I_List.add(String.valueOf(i));
                    I_List.add(I[i]);
                }
            }

            if (count > 0){
                R_List.add(Ri);
                R_List.add(String.valueOf(count));
                R_List.addAll(I_List);
            }

            I_List.clear();
        }

        System.out.print(R_List.size());
        for (String s : R_List) {
            System.out.print(" "+s);
        }


    }


}
