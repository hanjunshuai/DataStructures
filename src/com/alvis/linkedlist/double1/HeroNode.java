package com.alvis.linkedlist.double1;

/**
 * 定义 HeroNode ，每个HeroNode对象就是一个节点
 */
public class HeroNode {
    public int no;
    public String name;
    public String nikeName;
    // 指向下一个节点
    public HeroNode next = null;
    // 指向上一个节点
    public HeroNode pre = null;

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }
}
