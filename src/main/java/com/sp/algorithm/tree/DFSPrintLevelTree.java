package com.sp.algorithm.tree;

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
}
