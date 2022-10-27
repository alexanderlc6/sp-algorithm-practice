package com.sp.algorithm.array;

import java.util.Arrays;

public class CombineArray {

        public static void main(String[] args) {
            int[] result = mergeList(new int[] {1,2,3,0,0,0}, new int[]{2,5,6}, 3,3);
             for(int i=0;i<result.length;i++){
                 System.out.print(result[i]);
             }
        }

        public static int[] mergeList(int[] nums1, int[] nums2, int m, int n){
              if(nums1 == null && nums2 == null){
                 return new int[]{};
              }

              // Abandoned
//              int[] tmpList = new int[m + n];
//              int cnt = 0;
//              int i = 0;
//
//              while(nums1 != null || nums2 != null){
//                  if(nums2 == null || (i< n && nums1[i] < nums2[i])){
//                     tmpList[cnt++] = nums1[i++];
//                  }else if(nums1 == null || (i > m && nums1[i] < nums2[i])){
//                     tmpList[cnt++] = nums2[i++];
//                 }
//              }

            //方法1
//            int i = m -1;
//            int j = n -1;
//            int k = m+n - 1;
//
//            while(i >= 0 && j >= 0){
//                nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
//            }
//            System.arraycopy(nums2, 0, nums1, 0, j+1);

            //方法2
            int q = 0;
            for (int i = 0; i < nums1.length; i++) {
                if(nums1[i] == 0){
                    nums1[i] = nums2[q++];
                }

                if(q == n){
                    break;
                }
            }
            Arrays.sort(nums1);


            //方法3
//            System.arraycopy(nums2, 0, nums1, m, n);
//            Arrays.sort(nums1);

            return nums1;
        }
}
