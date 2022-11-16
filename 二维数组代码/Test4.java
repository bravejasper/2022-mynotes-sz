package com.itheima.demo06_二维数组;

public class Test4 {
    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {4, 5, 6}};

        //遍历二维数组,获取到每一个一维数组
        //for (int i = 0; i < arr.length; i++) {
        //    //获取元素(一维数组)
        //    int[] arr2 = arr[i];
        //    //获取了一维数组,再去遍历一维数组
        //    for (int j = 0; j < arr2.length; j++) {
        //        System.out.println(arr2[j]);
        //    }
        //}

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println(arr[i][j]);
            }
        }

    }
}
