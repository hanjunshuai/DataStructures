package com.alvis.linkedlist;

/**
 * 约瑟夫问题
 * Josephu 问题为：设编号为1，2，3....n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m的那个人出列，它的下一位又开始从报数，数到m的那个人出列，依次类推，直到所有人出列为止，由此
 * 产生一个出队编号的序列。
 * <p>
 * n = 5，即有5个人
 * k = 1，从第一个人开始报数
 * m = 2，数2下
 */
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.show();
        circleSingleLinkedList.addBoy(5);// 加入5个节点
        circleSingleLinkedList.show();

        System.out.println("出圈");
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

/**
 * 构建一个单向环形链表
 * 思路：
 * 1、先创建第一个节点，让first指向该节点，并形成环形
 * 2、后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中
 * <p>
 * 遍历环形链表
 * 1、先让一个辅助指针（变量），指向first节点
 * 2、然后通过一个while循环遍历该环形链表
 */

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {
    // 创建一个first节点，当前没有编号
    private Boy first = null;

    // 添加小孩节点，构建一个环形的链表

    /**
     * 先创建第一个节点，让first指向该节点，并形成环形
     * 后面每创建一个新的节点，就把该节点，加入到已有的环形链表即可
     *
     * @param nums 节点个数
     */
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        // 辅助变量，帮助构建环形链表
        Boy curBuy = null;
        // 使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);// 构成环
                curBuy = first;// 让curBoy指向第一个小孩
            } else {
                curBuy.setNext(boy);
                boy.setNext(first);
                curBuy = boy;
            }
        }
    }

    // 遍历当前的环形链表

    /**
     * 1、先让一个辅助指针(变量)curBoy，指向first节点
     * 2、通过一个while循环遍历该环形链表即可curBoy.next == first 结束
     */
    public void show() {
        // 链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为first不能动，因此使用辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                // 遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    // 根据用户的输入，计算出圈的顺序

    /**
     * @param startNo 表示从第几个小孩数数
     * @param countNum   表示数几下
     * @param nums    表示最初有多少节点在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误，请重新输入");
            return;
        }
        // 创建辅助指针（变量），事先应该指向环形链表的最后这个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                // helper 指向最后的节点
                break;
            }
            helper = helper.getNext();
        }

        // 报数前，先让 first 和helper 移动 k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 报数时，让first和helper 同时移动 countNum-1 次，然后出圈
        while (true) {
            if (helper == first) {
                // 说明圈中只有一个节点
                break;
            }
            // 让first和helper 同时移动 countNum-1 次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的节点，就是要出圈的节点
            System.out.printf("编号为%d\n", first.getNo());
            // 这时将first指向的节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的节点编号为%d", first.getNo());
    }
}

/**
 * 创建一个Boy类，表示一个节点
 */
class Boy {
    private int no; // 编号
    private Boy next;// 指向下一个节点，默认为null


    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
