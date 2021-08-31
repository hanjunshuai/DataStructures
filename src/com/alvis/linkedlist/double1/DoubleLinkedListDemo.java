package com.alvis.linkedlist.double1;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.show();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        System.out.println("原始链表～～～");
        doubleLinkedList.show();

//        System.out.println("修改节点～～～");
//        doubleLinkedList.update(new HeroNode(1, "song", "boss"));
        System.out.println("删除节点");
        doubleLinkedList.delete(4);
        doubleLinkedList.delete(1);
        doubleLinkedList.show();
    }
}

/**
 * 双向链表
 */
class DoubleLinkedList {
    // 初始化头节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 删除节点
     * 1、对于双向链表，可直接找到要删除的这个节点
     * 2、找到后自我删除
     *
     * @param no
     */
    public void delete(int no) {
        HeroNode temp = head.next;
        // 是否找到代删除的节点
        boolean flag = false;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp == null) {
                // 已经到链表的最后节点的next
                break;
            }

            if (temp.no == no) {
                // 找到代删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            // 如果是最后一个节点，就不需要给下一个节点的pre赋值
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("不存在该编号的节点");
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

    // 添加节点到链表的最后
    public void add(HeroNode newNode) {
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
        temp.next = newNode;
        newNode.pre = temp;

    }

    // 遍历双向链表
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
