package com.sp.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public void mergeSortList(int [] nums){
        if(nums == null || nums.length == 0){
            return;
        }

        int [] t = new int[nums.length];
        mSort(nums, 0, nums.length, t);
    }

    private void mSort(int[] nums, int b, int e, int[] t) {
        if(b >=e || b +1 >= e){
            return;
        }

        int m = b+((e-b) >> 1);
        mSort(nums, b, m, t);
        mSort(nums, m, e, t);

        int i = b, j = m, to = b;
        while (i < m || j < e){
            if(j>=e || i < m && nums[i] < nums[j]){
                t[to++] = nums[i++];
            }else{
                t[to++] = nums[j++];
            }
        }

        for (int k = b; k < e; k++) {
            nums[k] = t[k];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] data = new int [] {9,4,6,3,1,2,1,8,7};
        mergeSort.mergeSortList(data);
        Arrays.stream(data).forEach(t -> System.out.print(t + "\t"));
    }
}
