package com.sp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/19/22 5:22 PM
 */
public class FindSubCollection {
    private static int [] data = {1,2,3};

    /**
     * 将box状态加入答案中
     * @param box
     * @param all
     */
    public void append(List<Integer> box, List<List<Integer>> all){
        all.add(new ArrayList());
        for (Integer i : box){
            all.get(all.size() - 1).add(i);
        }
    }

    public void backtrace(int[] a, int start, int end, List<Integer> box, List<List<Integer>> all){
        final int n = a == null ? 0 : a.length;
        append(box, all);

        if(start >= n){
            return;
        }

        for (int j = start; j < n; j++) {
            box.add(a[j]);
            backtrace(a,  j + 1, end, box, all);
            box.remove(box.size() - 1);
        }
    }

    /**
     * 时间复杂度O(n * pow(n,2)个子集),空间复杂度为O(n)
     * @param a
     * @return
     */
    public List<List<Integer>> subsets(int[] a){
        final int n = a == null ? 0 : a.length;
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        backtrace(a, 0, n, box, ans);
        return ans;
    }

    public static void main(String[] args) {
        FindSubCollection subCollection = new FindSubCollection();
        List<List<Integer>> ans = subCollection.subsets(data);
        StringBuffer stbOutput = new StringBuffer();

        stbOutput.append("[");
        ans.forEach(t -> {
            stbOutput.append("[");
            t.forEach(m -> {
                stbOutput.append(m + ",");
            });
            stbOutput.deleteCharAt(stbOutput.length() - 1);
            stbOutput.append("],");
        });
        stbOutput.deleteCharAt(stbOutput.length() - 1);
        System.out.println(stbOutput.toString());
    }
}
