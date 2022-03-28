package com.sp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @description:给定一个正数数组 A[]，和一个正整数 target。输出所有的子集，使得子集和等于 target。
 *
 * 注意：里面的元素可以重复选取。
 * 输入：A = [2, 3, 8]，target = 7
 * 输出：[2, 2, 3]只有这个子集和等于 7。
 * @author: luchao
 * @date: Created in 3/28/22 12:13 PM
 */
public class FindSubSumValueList {

    public void append(List<Integer> box, List<List<Integer>> all) {
        all.add(new ArrayList());
        for (Integer i : box) {
            all.get(all.size() - 1).add(i);
        }
    }

    public void backtrace(int[] a, int start, int boxSum, int target, List<Integer> box, List<List<Integer>> all) {
        final int n = a == null ? 0 : a.length;

        if(boxSum == target) {
            append(box, all);
        }

        if (start >= n || boxSum >= target) {
            return;
        }

        for (int i = start; i < n; i++) {
            box.add(a[i]);
            boxSum += a[i];

            // 当A[]数组里面的元素，不能重复选择的时候，递归的start = i + 1
            // 当A[]里面的元素可以重复选择的时候，递归的start = i
            backtrace(a, i, boxSum, target, box, all);
            box.remove(box.size() - 1);
            boxSum -= a[i];
        }
    }

    public List<List<Integer>> getAllSubSumList(int[] a, int target){
        final int n = a == null ? 0 : a.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> box = new ArrayList<>();
        int boxSum = 0;
        backtrace(a, 0, boxSum, target, box, ans);
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = new FindSubSumValueList().getAllSubSumList(new int[]{2,3,8}, 7);
        StringBuffer stbTmp = new StringBuffer();
        ans.forEach(t -> {
            stbTmp.append("{");
            for (int x : t){
                stbTmp.append(x + ",");
            }
            stbTmp.deleteCharAt(stbTmp.length() - 1);
            stbTmp.append("}");
        });

        System.out.println(stbTmp);
    }
}
