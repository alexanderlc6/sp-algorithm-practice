package com.sp.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @description: BFS(广度优先搜索)层序遍历-一维数组
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
 * @author: luchao
 * @date: Created in 3/7/22 12:40 AM
 */
public class BFSPrintLevelTree {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        if(root == null){
//            return new ArrayList();
//        }
//
//        List<List<Integer>> result = new ArrayList();
//        List<Integer> list = new ArrayList();
//
//        list.add(root.val);
//        if(root.left != null && root.right == null){
//            // list.add(root.left.val);
//            // list.add(root.right.val);
//            // result.add(list);
//
//            result.addAll(levelOrder(root.left));
//        }
//
//        if(root.left == null && root.right != null){
//            // list.add(root.left.val);
//            // list.add(root.right.val);
//            // result.add(list);
//
//            result.addAll(levelOrder(root.right));
//        }
//
//        return result;
//    }

    /**
     * 在while 循环的每一轮中，都是将当前层的所有结点出队列，再将下一层的所有结点入队列
     * @param root
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();

        if(root != null) {
            queue.add(root);
        }

        List<List<Integer>> result = new ArrayList();

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> levelNodes = new ArrayList();

            for (int i = 0; i <n; i++) {
                TreeNode node = queue.poll();
                levelNodes.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            result.add(levelNodes);
        }

        return result;
    }
}
