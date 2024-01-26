package com.sp.algorithm.tree;

import java.util.Stack;

/**
 * DFS(深度优先搜索)遍历-二维数组
 * @description: https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
 * @author: luchao
 * @date: Created in 3/7/22 12:46 AM
 */
public class DFSPrintLevelTree {

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
    }

    /**
     * 合并树(遍历节点时先左后右进栈)
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2){
        Stack<TreeNode> stk = new Stack<>();
        int isLeft = 0;
        stk.push(t1);
        System.out.println(t1.val);

        while (stk.size() != 0){
            TreeNode pointer = stk.peek();
            if(isLeft == 0){
                if(pointer.left != null){
                    System.out.println(pointer.left.val);
                    stk.push(pointer.left);
                    isLeft = 0;
                }else {
                    isLeft = 1;
                }
            }else {
                stk.pop();
                isLeft = 1;
                if(pointer.right != null){
                    System.out.println(pointer.right.val);
                    stk.push(pointer.right);
                    isLeft = 0;
                }
            }
        }

        return t1;
    }
}
