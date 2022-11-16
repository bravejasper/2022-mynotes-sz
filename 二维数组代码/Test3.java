package com.itheima.demo06_二维数组;

public class Test3 {
    public static void main(String[] args) {
        int[][] arr1 = new int[2][]; //二维数组里存储了两个null
        /*
        {
            null,
            null
        }
         */
        int[][] arr2 = new int[2][0]; //二维数组里存储了两个一位数组,每个一维数组有0个元素
       /* {
            {},
            {}
        }*/
        int[][] arr3 = new int[2][2]; //二维数组里存储了两个null,每个一维数组有2个元素
        /* {
            {0,0},
            {0,0}
        }*/

        //访问格式:
        //数组名[a][b]
        //a:第几个一维数组   从0开始
        //b:一维数组第几个元素  从0开始

        System.out.println(arr3[0][0]);//访问了第一个数组的第一个元素
        //System.out.println(arr2[0][0]);//索引越界
        //System.out.println(arr1[0][0]);//空指针异常

    }
}
