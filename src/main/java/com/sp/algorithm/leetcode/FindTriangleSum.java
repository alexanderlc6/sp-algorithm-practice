package com.sp.algorithm.leetcode;

import java.util.*;

/**
 * LeetCode Practises
 *
 * @author luchao Created on 2022/10/19
 */
public class FindTriangleSum {
    /**
     * https://leetcode.cn/problems/triangle
     *  * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     *  * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点
     *    下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
            return -1;
        }

//        for(List<Integer> layerNums : triangle){
//            if(layerNums == null || layerNums.size() == 0){
//                continue;
//            }
//
//            int tmp = 0;
//            for (int i = 0; i < layerNums.size(); i++) {
//                if(layerNums.get(i) < layerNums.get(i + 1)){
//                    tmp = layerNums.get(i);
//                }
//            }
//
//            sumNum += tmp;
//        }
        int len = triangle.size();
        int[][] eachLayerNums = new int[len][len];
        eachLayerNums[0][0] = triangle.get(0).get(0);

        int layerTotal = 0;
        for (int i = 1; i < len; i++) {
            eachLayerNums[i][0] = eachLayerNums[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                eachLayerNums[i][j] = Math.min(eachLayerNums[i-1][j-1], eachLayerNums[i-1][j]) + triangle.get(i).get(j);
            }

            eachLayerNums[i][i] = eachLayerNums[i - 1][i - 1] + triangle.get(i).get(i);
        }

        layerTotal = eachLayerNums[len - 1][0];
        for (int i = 1; i < len; i++) {
            layerTotal = Math.min(layerTotal, eachLayerNums[len - 1][i]);
        }

        return layerTotal;

//        //动态规划法
//        int len = triangle.size();
//        int[] dp = new int[len + 1];
//        for (int i = len - 1; i >= 0  ; i--) {
//            for (int j = 0; j <= i; j++) {
//                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
//            }
//        }
//
//        return dp[0];
    }

    /**
     * Find sum num of 3 numbs in array
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                for (int k = 0; k < nums.length; k++) {
//                    if(i != j && i != k && j != k && nums[i] + nums[j] + nums[k] == 0){
//                        List<Integer> validNums = new ArrayList<>();
//                        validNums.add(nums[i]);
//                        validNums.add(nums[j]);
//                        validNums.add(nums[k]);
//                        result.add(validNums);
//                    }
//                }
//            }
//        }

        Set<List<Integer>> tmpCalcNums = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right){
                if(nums[i] + nums[left] + nums[right] == 0){
                    tmpCalcNums.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }else if(nums[i] + nums[left] + nums[right] < 0){
                    left++;
                }else {
                    right--;
                }
            }
        }

        result.addAll(tmpCalcNums);
        return result;
    }

    /**
     * Get max profit
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }

//        //场景1：可多次卖出求最大利润--方法1
//        boolean isHold = false;
//        int buyPrice = 0;
//        int maxProfit = 0;
//        int len = prices.length;
//
//        for (int i = 0; i < len - 1; i++) {
//            if(isHold) {
//                if (prices[i] < prices[i + 1]) {
//                    continue;
//                }
//
//                maxProfit += prices[i] - buyPrice;
//                isHold = false;
//            }else {
//                if (prices[i] < prices[i + 1]) {
//                    isHold = true;
//                    buyPrice = prices[i];
//                }else {
//                    continue;
//                }
//            }
//        }
//
//        if(isHold){
//            maxProfit += prices[len - 1] - buyPrice;
//        }

//        //场景2：可多次卖出求最大利润--方法2
//        int maxProfit = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            if(prices[i] < prices[i + 1]) {
//                int diffAmt = prices[i + 1] - prices[i];
//                maxProfit += diffAmt;
//            }
//        }

        //只可按日期顺序单次买入和卖出
        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int diffAmt = prices[i] - minPrice;
            if(maxProfit < diffAmt){
                maxProfit = diffAmt;
            }
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
