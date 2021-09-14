package com.alvis.stack;

import java.util.Scanner;

/**
 * @program: DataStructures
 * @description:
 * @author: alvis
 * @create: 2021-09-14 11:04
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);

        // 用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s（show）,显示队列");
            System.out.println("e（exit）,推出队列");
            System.out.println("g（pop）,获取数据");
            System.out.println("a（push）,添加数据到队列");

            key = scanner.next().charAt(0);
            switch (key) {
                case 'e':
                    scanner.close();
                    System.out.println("退出程序");
                    loop = false;
                    break;
                case 's':
                    try {
//                        stack.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.println("输入一个数");
                        int value = scanner.nextInt();
                        stack.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int pop = stack.pop();
                        System.out.println("出栈：" + pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组模拟栈,数据存放在数组中
    private int top = -1;// top 表示栈顶，初始化为-1

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("已满");
        }
        stack[++top] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈内已空~~~");
        }
        return stack[top--];
    }

//    public void show() {
//        if (isEmpty()) {
//            throw new RuntimeException("栈内已空~~~");
//        }
//        for (int i = top; i > 0; i--) {
//            System.out.println(stack[i]);
//        }
//    }
}
