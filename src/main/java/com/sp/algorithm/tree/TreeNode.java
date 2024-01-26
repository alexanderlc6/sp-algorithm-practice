package com.sp.algorithm.tree;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/7/22 12:41 AM
 */
public class TreeNode {
    public int val = 0;

    public TreeNode left = null;

    public TreeNode right = null;
    TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
