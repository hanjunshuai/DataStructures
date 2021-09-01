package com.alvis.sparsearray;

/**
 * @program: alvis
 * @description: 稀疏数组
 * @author: alvis
 * @create: 2021-08-26 09:17
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建二维数组 11*11
        int[][] cha = new int[11][11];
        cha[0][3] = 1;
        cha[1][1] = 2;
        cha[2][4] = 1;
        System.out.println("原数据~~~~");
        for (int[] ints : cha) {
            for (int num : ints) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }

        // 转化稀疏数组
        // 获取有效值的个数
        int count = 0;
        for (int[] ints : cha) {
            for (int num : ints) {
                if (num != 0) {
                    count++;
                }
            }
        }

        int[][] sparseArr = new int[count + 1][3];
        sparseArr[0][0] = cha.length;
        sparseArr[0][1] = cha[0].length;
        sparseArr[0][2] = count;

        for (int i = 0; i < cha.length; i++) {
            for (int j = 0; j < cha[i].length; j++) {
                if (cha[i][j] != 0) {
                    sparseArr[i + 1][0] = i;
                    sparseArr[i + 1][1] = j;
                    sparseArr[i + 1][2] = cha[i][j];
                }
            }
        }

        System.out.println("稀疏数组~~~~");
        for (int[] ints : sparseArr) {
            for (int num : ints) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }


        int[][] chArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("还原数据~~~");
        for (int[] ints : chArr) {
            for (int num : ints) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }
    }
}
