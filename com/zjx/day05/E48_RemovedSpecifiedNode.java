package com.zjx.day05;

import java.util.*;

/**
 * @author zjx
 * @Date 2024-02-21 11:23:22
 * @Desc:
 * 描述
 * 输入一个单向链表和一个节点的值，从单向链表中删除等于该值的节点，删除后如果链表中无节点则返回空指针。
 * 链表的值不能重复。
 * 构造过程，例如输入一行数据为:
 * 6 2 1 2 3 2 5 1 4 5 7 2 2
 * 则第一个参数6表示输入总共6个节点，第二个参数2表示头节点值为2，
 * 剩下的2个一组表示第2个节点值后面插入第1个节点值，为以下表示:
 * 1 2 表示为
 * 2->1
 * 链表为2->1
 * 3 2表示为
 * 2->3
 * 链表为2->3->1
 * 5 1表示为
 * 1->5
 * 链表为2->3->1->5
 * 4 5表示为
 * 5->4
 * 链表为2->3->1->5->4
 * 7 2表示为
 * 2->7
 * 链表为2->7->3->1->5->4
 * 最后的链表的顺序为 2 7 3 1 5 4
 * 最后一个参数为2，表示要删掉节点为2的值
 * 删除 结点 2
 * 则结果为 7 3 1 5 4
 * 数据范围：链表长度满足 1≤n≤1000  ，节点中的值满足 0≤val≤10000
 * 测试用例保证输入合法
 * 【输入描述】：
 * 输入一行，有以下4个部分：
 * 1 输入链表结点个数
 * 2 输入头结点的值
 * 3 按照格式插入各个结点
 * 4 输入要删除的结点的值
 * 【输出描述】：
 * 输出一行
 * 输出删除结点后的序列，每个数后都要加空格
 */
public class E48_RemovedSpecifiedNode {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int quantity = in.nextInt();
        //添加第一个数
        List<Integer> list = new ArrayList<>();
        list.add(in.nextInt());
        for (int i = 0; i < quantity-1; i++) {
            int next = in.nextInt();
            int pre = in.nextInt();
            addNum(list,next,pre);
        }

        removedNum(list,in.nextInt());

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d", list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" ");
            }
        }

    }

    public static void addNum(List<Integer> list,int next,int pre){
        if (!list.contains(next) && !list.contains(pre)){
            list.add(pre);
            list.add(next);
        }else {
            //查找的点
            int point = list.contains(next) ? next : pre;
            //查找位置
            int index = list.indexOf(point);
            if (point == pre){
                list.add(index+1,next);
            }else {
                list.add(index,next);
            }
        }

    }

    public static void removedNum(List<Integer> list,int num){
        if (list.contains(num)){
            list.remove((Integer) num);
        }
    }
}
