package com.sp.algorithm.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/17/22 3:25 PM
 */
public class FindMinKNums {
    private int [] a = null;
    private int n = 0;
    HeapOpr heapOpr = new HeapOpr();

    public int size(){
        return n;
    }

    /**
     * 获取数组中最小的k个数
     * 时间复杂度为 O(nlgk), 空间复杂度为 O(k)
     * 方法一：使用大顶堆
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastMinKNums(int[] arr, int k){
        if(k <= 0 || arr == null || arr.length == 0){
            return new int[0];
        }

        a = new int[k + 1];
        n = 0;
        for (int x : arr){
            heapOpr.push(x);

            if(size() > k){
                heapOpr.pop();
            }
        }

        int[] ans = new int[k];
        int i = 0;
        while (size() > 0){
            ans[i++] = heapOpr.pop();
        }

        return ans;
    }

    /**
     * 方法二：使用优先级队列
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastMinKNumsWithQueue(int[] arr, int k){
        if(k == 0 || arr == null || arr.length == 0){
            return new int[0];
        }

        Queue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int x : arr){
            queue.offer(x);

            if(queue.size() > k){
                queue.poll();
            }
        }

        int[] ans = new int[k];
        int i = 0;
        for (int x : queue){
            arr[i++] = x;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] data = new int[]{ 9,3,2,8,1,7,5,6,4};
        int k = 4;  //最小的4个数
        FindMinKNums findMinKNums = new FindMinKNums();
        System.out.println(Arrays.toString(findMinKNums.getLeastMinKNums(data, k)));
        System.out.println(Arrays.toString(findMinKNums.getLeastMinKNumsWithQueue(data, k)));
    }
}
