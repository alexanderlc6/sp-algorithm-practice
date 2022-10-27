package com.sp.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public class FindPathForSumTarget {
    private List<List<Integer>> ans = new ArrayList<>();

    /**
     * 前序遍历(根-左-右),回溯查找,输出所有总和为target的节点路径
     * 复杂度：n/2 * log(n) = n*lgn
     * 需复制有效路径n/2次，每次复制的路径深度为log(n)
     * @param root
     * @param path
     * @param sum
     * @param target
     */
    public void backtrace(TreeNode root, List<Integer> path, int sum, int target){
        if(root == null){
            return;
        }

        sum += root.val;

        //将节点添加到路径中,相当于压栈
        path.add(root.val);
        if(root.left == null && root.right == null){
            //若已经形成了一个有效路径
            if(sum == target){
                //添加到结果集中
                ans.add(new ArrayList<>(path));
            }
        }else {
            //回溯,分别再看子情况
            backtrace(root.left, path, sum, target);
            backtrace(root.right, path, sum, target);
        }

        //弹出栈,把节点从路径最后扔掉
        path.remove(path.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum){
        List<Integer> path = new ArrayList<>();
        backtrace(root, path, 0, sum);
        return ans;
    }

    public static void main(String[] args) {

    }
}
