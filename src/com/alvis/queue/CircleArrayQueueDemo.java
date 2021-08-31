package com.alvis.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray arrayQueue = new CircleArray(3);
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
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("head : " + arrayQueue.headFront());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

class CircleArray {
    // 数组的最大容量
    private int maxSize;
    // 队列头:指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素。
    // front的初始值为 0
    private int front;
    // 队列尾:指向队列的最后一个元素的后一个位置，因为希望空出一个空间做约定。
    // rear的初始值为 0
    private int rear;
    // 该数组用于存放数据，模拟队列
    private int[] arr;

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    // 判断是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int num) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        arr[rear] = num;
        // 将rear后移，注：考虑取模
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据～～～");
        }
        int num = arr[front];
        front = (front + 1) % maxSize;
        return num;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，不能取数据～～～");
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 显示队列头部数据
    public int headFront() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }
        return arr[front];
    }

    // 求出当前队列有效数据的个数
    public int size() {
        // rear = 1
        // front = 0
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }
}