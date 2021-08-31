package com.alvis.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(4);
        // 用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s（show）,显示队列");
            System.out.println("e（exit）,推出队列");
            System.out.println("g（get）,获取数据");
            System.out.println("a（add）,添加数据到队列");
            System.out.println("h（head）,队列头的数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    System.out.println("退出程序");
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        System.out.println("result: " + arrayQueue.getQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("head : " + arrayQueue.headFront());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }

    }
}

// 使用数组模拟队列编写一个ArrayQueue类

/**
 * 问题分析并优化
 * 1、目前数组使用一次就不能用了，没有达到复用的效果
 * 2、将这个数组使用算法，改进成一个环形的队列： 取余
 */
class ArrayQueue {
    // 数组的最大容量
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 该数组用于存放数据，模拟队列
    private int[] arr;

    // 创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        // 指向队列头部，指向队列头的前一个位置
        front = -1;
        // 指向队列尾部，指向尾部的数据（即就是队列最后一个数据）
        rear = -1;
    }

    // 判断队列是否满
    public boolean isFull() {
        return front == -1 && rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 添加数据到队列
    public void addQueue(int num) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        arr[++rear] = num;
    }

    // 获取队列数据（出队列）
    public int getQueue() {
        // 判断队列是否为空，通过抛异常处理
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }
        return arr[++front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    // 显示队列头部数据
    public int headFront() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }
        return arr[front + 1];
    }
}
