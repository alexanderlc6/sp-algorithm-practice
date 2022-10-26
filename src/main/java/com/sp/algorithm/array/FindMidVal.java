package com.sp.algorithm.array;

public class FindMidVal {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //方法一: 数组合并(归并排序),根据奇偶数计算中位数,时间复杂度是O(m + n)
//        int m = nums1.length;
//        int n = nums2.length;
//        int[] nums = new int[m + n];
//        //nums1为空
//        if(m == 0){
//            if(n % 2 == 0){
//                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2;
//            }else {
//                return nums2[n / 2];
//            }
//        }
//
//        //nums2为空
//        if(n == 0){
//            if(m % 2 == 0){
//                return (nums2[m / 2 - 1] + nums2[m / 2]) / 2;
//            }else {
//                return nums2[m / 2];
//            }
//        }
//
//        int count = 0;
//        int i = 0, j = 0;
//        while (count != m + n){
//            if(i == m){
//                while (j != n){
//                    nums[count++] = nums2[j++];
//                }
//                break;
//            }
//
//            if(j == n){
//                while(i != m){
//                    nums[count++] = nums1[i++];
//                }
//                break;
//            }
//
//            if(nums1[i] < nums2[j]){
//                nums[count++] = nums1[i++];
//            }else {
//                nums[count++] = nums2[j++];
//            }
//        }
//
//        if(count % 2 == 0){
//            return (nums[count /2 - 1] + nums2[count / 2]) / 2;
//        }else {
//            return nums[count / 2];
//        }

        //【推荐】方法二:不合并,直接找中位数
        //遍历 len/2+1 次，len=m+n，所以时间复杂度依旧是 O(m+n)
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;

        //奇数和偶数都是需要遍历len/2+1 次
        for (int i = 0; i <= len / 2; i++) {
            //返回中位数的话，奇数需要最后一次遍历的结果就可以了，偶数需要最后一次和上一次遍历的结果
            //right 保存当前循环的结果,每次给left，这样【最后一次循环的时候，left 将得到right的值(上一次循环的结果)、right 更新为最后一次的结果】
            left = right;
            //aStart 还没有到最后且A<B则 后移（bStart >= n是为了nums2没元素了防止越界）
            if(aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])){
                right = nums1[aStart++];
            }else {
                right = nums2[bStart++];
            }
        }

        if((len & 1) == 0){
            return (left + right) / 2.0;
        }else {
            return right;
        }

        //方法3:每次找第min(k / 2, len(nums1)个小的数
//        int m = nums1.length;
//        int n = nums2.length;
//        int left = (n + m + 1) / 2;
//        int right = (n + m + 2) / 2;
//        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
//        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) / 2;
    }

    /**
     * 方法3 递归
     * @param nums1
     * @param start1
     * @param end1
     * @param nums2
     * @param start2
     * @param end2
     * @param k
     * @return
     */
    public int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k){
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if(len1 > len2){
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }

        if(len1 == 0){
            return nums2[start2 + k -1];
        }

        if(k == 1){
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if(nums1[i] > nums2[j]){
            return getKth(nums1, start1, end1, nums2, j+1, end2, k - (j -start2 + 1));
        }else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i -start1 + 1));
        }
    }

    public static void main(String[] args) {
        FindMidVal findMidVal = new FindMidVal();
        int[] nums1 = new int[] {1,2};
        int[] nums2 = new int[] {3,4};
        System.out.println(findMidVal.findMedianSortedArrays(nums1, nums2));
    }
}
