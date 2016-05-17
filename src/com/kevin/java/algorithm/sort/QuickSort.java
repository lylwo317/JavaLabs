package com.kevin.java.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序，挖坑填坑法
 * Created by kevin on 4/28/16.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] s = new int[]{72,6,57,88,60,42,83,73,48,85};
        //int[] s = new int[]{72,6,57,72,72,72,83,73,48,85};
        quick_sort(s,0,s.length-1);
        System.out.println("result="+Arrays.toString(s));
    }

    public static void quick_sort(int array[], int left, int right) {

        if (left >= right) {//不满足left<right,结束递归
            return;
        }

        int middle = array[left];//取第一个值作为基准数
        int l = left;
        int r = right;

        while (l < r) {
            while (l < r && array[r] >= middle) {
                r--;
            }

            if (l < r) {
                array[l] = array[r];
                /*array[r]=middle;
                System.out.println(Arrays.toString(array));*/
                l++;
            }

            while (l < r && array[l] < middle) {
                l++;
            }

            if (l < r) {
                array[r] = array[l];
                /*array[l]=middle;
                System.out.println(Arrays.toString(array));*/
                r--;
            }
        }
        array[l] = middle;
        quick_sort(array,left,l-1);
        quick_sort(array,l+1,right);
    }
}
