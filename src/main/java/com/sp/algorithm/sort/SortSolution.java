package com.sp.algorithm.sort;

import java.util.Arrays;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/8/22 10:55 AM
 */
public class SortSolution {
    private int cnt = 0;

    /**
     * 数组拆分区间查找
     * @param a
     * @param b
     * @param e
     * @param t
     */
    private void msort(int[] a, int b, int e, int[] t){
        if(b >= e || b + 1 >= e){
            return;
        }

        final int m = b + ((e - b) >> 1);
        msort(a, b, m, t);
        msort(a, m, e, t);

        int i = b, j = m, to = b;
        while (i < m || j < e){
            if(j >= e || (i < m && a[i] <= a[j])){
                t[to++] = a[i++];
                cnt += (j - m);
                System.out.println("cnt:" + cnt);
            }else{
                t[to++] = a[j++];
            }
        }

        for (int k = b; k < e; k++) {
            a[k] = t[k];
            System.out.println("a[k]:" + a[k]);
        }
    }

    /**
     * 求解逆序对的个数
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums){
        if(nums == null || nums.length <= 1){
            return 0;
        }

        int[] t = new int[nums.length];
        cnt = 0;
        msort(nums, 0, nums.length, t);
        return cnt;
    }

    public static void main(String[] args) {
        int[] data = new int[] { 3,5,1,6,2};

        //合并排序--求逆序对个数
        System.out.println(new SortSolution().reversePairs(data));

        //快速排序
        new SortSolution().execQuickSort(data);
        Arrays.stream(data).forEach(t -> System.out.print(t + "\t"));
        System.out.println();

        //split
        new SortSolution().split(data);
        Arrays.stream(data).forEach(t -> System.out.print(t + "\t"));
        System.out.println();
    }

    /**
     * 快速排序
     * 较好:O(nlgn),较差:O(pow(n,2)）
     * @param data
     */
    public void execQuickSort(int[] data){
        if(data == null){
            return;
        }

        quickSort(data, 0, data.length);
    }

    /**
     * 快速排序
     * @param data
     * @param b
     * @param e
     */
    public void quickSort(int[] data, int b, int e){
        if(b >= e || b + 1 >= e){
            return;
        }

        final int m = b + ((e - b) >> 1);
        int x = data[m];
        int l = b, i = b, r = e - 1;
        while (i <= r){
            if(data[i] < x){
                swap(data, l++, i++);
            }else if(data[i] == x){
                i++;
            }else {
                swap(data, r--, i);
            }
        }

        quickSort(data, b, l);
        quickSort(data, i, e);
    }


    /**
     * 三路切分
     * 开闭原则--4个区间:[0,l)--全为0,[l,i)--全为1,[i,r)--未处理,[r,n)--全为2
     * @param data
     */
    public void split(int[] data){
        final int n = data.length;
        int i = 0, l = 0, r = n -1;
        while (i <= r){
            if(data[i] == 0){
                //case 0:l++,i++完成[l,i)区间的平移
                swap(data, l++, i++);
                //case 1:元素1直接append到[l,i)区间后面即可
            }else if(data[i] == l){
                i++;
                //case 2:元素2需要和data[r]交换,区间[r,n)向左扩张
            }else {
                swap(data, r--, i);
            }
        }

    }

    public void swap(int[] data, int i, int j){
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
