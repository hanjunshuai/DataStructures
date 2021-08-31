package com.alvis.linkedlist.single;

import java.util.Stack;

public class SingleLikedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        SingleLikedList singleLikedList = new SingleLikedList();
//        singleLikedList.show();
//        System.out.println("添加节点到单向链表数据。。。。。。");
//        singleLikedList.add(heroNode1);
//        singleLikedList.add(heroNode4);
//        singleLikedList.add(heroNode2);
//        singleLikedList.add(heroNode3);

        System.out.println("根据编号顺序添加～～～");
        singleLikedList.addByOrder(heroNode2);
        singleLikedList.addByOrder(heroNode1);
        singleLikedList.addByOrder(heroNode4);
        singleLikedList.addByOrder(heroNode3);
        System.out.println("原始链表～～～");
        singleLikedList.show();
//        singleLikedList.addByOrder(heroNode1);

//        singleLikedList.update(new HeroNode(2, "孙悟空", "猴子"));
//        singleLikedList.show();
//        singleLikedList.update(new HeroNode(5, HeroNode"八戒", "猪"));
//        singleLikedList.delete(4);
//        singleLikedList.delete(1);
//        singleLikedList.delete(2);
//        singleLikedList.delete(3);
//        singleLikedList.show();

//        System.out.println("有效节点个数：" + getLength(singleLikedList.getHead()));
//        System.out.println("倒数第二个：" + findLastIndexNode(singleLikedList.getHead(), 2));

        //reverseList(singleLikedList.getHead());
        //singleLikedList.show();
        System.out.println("逆序打印链表～～～");
        reversePrintList(singleLikedList.getHead());
    }


    // 从尾到头打印链表(逆序打印单链表)

    /**
     * 思路
     * 方式1、将单链表进行反转，再遍历（这样做会破坏链表的结构）
     * 方式2、利用栈数据结构，将各个节点压入到栈中，然后利用先进后出的特点。
     */
    public static void reversePrintList(HeroNode headNode) {
        HeroNode temp = headNode.next;
        if (temp == null) {
            System.out.println("链表为空～～～");
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        // 将链表中的所有节点压入栈中
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }

        // 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop().toString());
        }
    }

    // 单链表反转

    /**
     * 思路：
     * 1、先定义一个节点reverseHead
     * 2、从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新链表的最前端
     * 3、原链表的head.next = reverseHead.next
     *
     * @param headNode
     */
    public static void reverseList(HeroNode headNode) {
        // 若链表为空或链表节点只有一个，无需反转
        // 定义一个辅助变量，帮助我们遍历原来的链表
        HeroNode temp = headNode.next;
        if (temp == null || temp.next == null) {
            return;
        }
        // 指向当前节点（temp）的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(-1, "", "");
        // 遍历原来的链表
        // 从头到尾遍历原来的节点
        // 每遍历一个节点，就取出，并放在新的链表reverseNode 的最前端
        while (temp != null) {
            // 先暂时保存当前节点的下一个节点
            next = temp.next;
            // 将temp的下一个节点指向新链表的最前端
            temp.next = reverseHead.next;
            // 将temp连接到新的链表上
            reverseHead.next = temp;
            // 后移
            temp = next;
        }

        // 将head.next指向reverseNode.net
        headNode.next = reverseHead.next;
    }


    // 查找单链表中的倒数第k个节点
    // 思路：
    // 1、编一个方法，接收head节点，同时接收一个index
    // 2、index 表示倒数第index个节点
    // 3、先把链表从头到尾遍历，得到链表总的长度 getLength
    // 4、得到size后，我们从链表的第一个开始遍历（size-index）个
    public static HeroNode findLastIndexNode(HeroNode headNode, int index) {
        HeroNode temp = headNode.next;
        if (temp == null) {
            return null;
        }
        int size = getLength(headNode);
        if (index <= 0 || index > size) {
            return null;
        }
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // 获取到单链表的节点个数（如果是带头节点的链表，需求不统计头节点）

    /**
     * @param headNode 链表的头节点
     * @return 有效节点个数
     */
    public static int getLength(HeroNode headNode) {
        HeroNode temp = headNode.next;
        if (temp == null) {
            return 0;
        }
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

}

// 定义SingleLinkedList 管理我们的英雄
class SingleLikedList {
    // 初始化头节点(不存放具体的数据)
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表

    /**
     * 思路：当不考虑顺序时
     * 1、找到当前链表的最后节点
     * 2、将最后这个节点的next 指向 新的节点
     */
    public void add(HeroNode node) {
        //因为head节点不能动，因此需要一个辅助变量 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后的节点，就将temp后移
            temp = temp.next;
        }

        // 当退出while循环时，temp就指向了链表的最后
        temp.next = node;
    }

    /**
     * 第二种添加，根据排名将英雄插入指定位置
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        // 标志添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            // temp 已经在链表的最后
            if (temp.next == null) {
                break;
            }
            // 位置找到，就在temp的后面插入
            if (temp.next.no > node.no) {
                // 添加到temp 与 temp.next 之间
                break;
            } else if (temp.next.no == node.no) {
                // 希望添加node编号已经存在，
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag的值
        if (flag) {
            // 不能添加，说明编号已存在
            System.out.println("准备插入的node已经存在～～～");
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    // 修改节点，根据编号no来修改，编号no不可修改。
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head.next;
        // 判断是否找到该节点
        boolean flag = false;
        if (temp == null) {
            System.out.println("链表为空～～～～");
            return;
        }
        while (true) {
            if (temp == null) {
                // 已经遍历完链表
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nikeName = newHeroNode.nikeName;
        } else {
            System.out.println("没有找到 编号为:" + newHeroNode.no + " 的节点");
        }
    }

    // 删除节点，
    public void delete(int no) {
        HeroNode temp = head;
        // 是否找到代删除的节点
        boolean flag = false;
        if (temp.next == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.no == no) {
                // 找到代删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("不存在该编号的节点");
        }
    }

    // 显示链表
    public void show() {
        HeroNode temp = head.next;
        // 判断链表是否为空
        if (temp == null) {
            System.out.println("链表为空～～～");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}