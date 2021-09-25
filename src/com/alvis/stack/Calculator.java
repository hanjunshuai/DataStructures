package com.alvis.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "10+2*6-22";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        // 用于扫描
        int index = 0;
        int num1, num2;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到的char保存到ch
        String keepNum = ""; // 用于拼接多位数

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch的类型（符号，数字）
            if (operStack.isOper(ch)) {//如果是运算符
                if (operStack.isEmpty()) {// 判断当前的符号栈是否为空
                    operStack.push(ch);
                } else {
                    // 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，
                    // 就需要从栈中pop两个数，在从符号栈中pop出一个符号，进行运算，将得到的结果入栈，
                    // 然后将当前的操作符入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 结果入栈
                        numStack.push(res);
                        // 将当前的操作符入栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接入栈
                        operStack.push(ch);
                    }
                }
            } else {
                // 如果是数字，直接入栈
                //  numStack.push(ch - 48);
                // 多位数处理，需要想expression的表达式的index后在看一位，如果是符号才入栈
                keepNum += ch;
                // 若ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个字符，如果是数字，就继续扫描，否则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 若是操作符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 表达式扫描完毕，就顺序的从 数栈 和 符号栈中pop出相应的数和符号，并运行

        while (true) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字即结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.println(numStack.pop());

    }
}

// 先创建一个栈
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组模拟栈,数据存放在数组中
    private int top = -1;// top 表示栈顶，初始化为-1

    // 构造器（栈的大小）
    public ArrayStack2(int maxSize) {
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

    // 返回栈顶的值，但不是pop
    public int peek() {
        return stack[top];
    }

    // 入栈
    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("已满");
        }
        stack[++top] = num;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈内已空~~~");
        }
        return stack[top--];
    }

    // 显示栈的内容，
    // 遍历栈，需要从栈顶开始显示数据
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈内已空~~~");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    // 返回运算符的优先级，优先级是程序员来确定的，优先级使用数字表示
    // 数字越大，优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            // 表达式只有加减乘除
            return -1;
        }
    }

    // 判断是否为一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                // 注意顺序
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                //
                result = num2 / num1;
                break;
        }
        return result;
    }
}