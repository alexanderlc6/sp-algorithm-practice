package com.sp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 在[1, 2, ..., n] 这几个数中，选出 k 个数出来组成集合。输出所有的解
 * 输入：n = 2, k = 1
 * 输出：[[1], [2]]
 * 解释：一个数的选择只有 [1], [2]
 * @author: luchao
 * @date: Created in 3/28/22 11:14 AM
 */
public class FindKNums {

    public void backTrace(int begin, int end, int k, List<Integer> box, List<List<Integer>> ans){
        final int n = box == null ? 0 : box.size();

        if(n == k) {
            append(box, ans);
        }

        if(n >= k || begin >= end){
            return;
        }

        for (int i = begin; i < end; i++) {
            //a[i] = i
            box.add(i);
            backTrace(i + 1, end, k, box, ans);
            box.remove(box.size() - 1);
        }
    }

    private void append(List<Integer> box, List<List<Integer>> all) {
        all.add(new ArrayList<>());
        for (Integer x : box) {
            all.get(all.size() - 1).add(x);
        }
    }

    public List<List<Integer>> combine(int n, int k){
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        backTrace(1, n + 1, k, box, ans);
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = new FindKNums().combine(4,2);
        ans.forEach(t -> {
            System.out.print("{");
            for (int x : t){
                System.out.print(x + ",");
            }
            System.out.println("}");
        });
    }
}
