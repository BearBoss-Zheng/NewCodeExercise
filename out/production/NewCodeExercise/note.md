E2
注意next 和 nextLine 的区别

E3
TreeSet可以按顺序排列，并且唯一

E6
检查是否为质数，可以仅仅检查从2到num的平方根（包括平方根）的数字，
因为任何大于num的平方根的因子都会与小于num的因子对称。这样可以减少循环次数。
假设 根号n = a，b > a,b是n的一个因子，那么一定存在c < a，使得bc = n.

E16
背包问题（01背包、完全背包、多重背包）

E17、E20
正则表达式：
^[1-9]\d*$
String lowRegex = ".*[a-z].*";
String upRegex = ".*[A-Z].*";
String numRegex = ".*\\d.*";
String otherRegex = ".*[^a-zA-Z\\d].*";

E24
最长单调子序列问题

E25
KMP算法

E28
匈牙利算法

E29
注意char与数字计算之后，会变成int
要记得强转回char

E33
注意数据的范围,本题需要用long，却用了int

E38
保留6位小数的方法
① System.out.printf("Formatted Number: %.6f\n", number);
② DecimalFormat format = new DecimalFormat("#.######");
   System.out.println(format.format(fifthLanding));





