package com.sp.algorithm.linkedlist;

import com.sp.algorithm.tree.TreeNode;

import java.util.ArrayList;

public class TreePathSumCalc {

    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> selectedNodes = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int expectNumber) {
        if(root == null || expectNumber == 0){
            return new ArrayList<ArrayList<Integer>>();
        }

        // if(root.val == expectNumber){
        //     ArrayList<Integer> tmp = new ArrayList<Integer>();
        //     tmp.add(root.val);
        //     result.add(tmp);
        // }

        recersiveFind(root, expectNumber);

        return result;
    }

    public void recersiveFind(TreeNode root, int expectNumber){
        selectedNodes.add(root.val);
        expectNumber -= root.val;

        if(expectNumber == 0 && root.left == null && root.right == null){
            // ArrayList<ArrayList<Integer>> tmpTreeNode = new ArrayList<ArrayList<Integer>>();
            // tmpTreeNode.add(selectedNodes);
            result.add(selectedNodes);
        }
        recersiveFind(root.left, expectNumber);
        recersiveFind(root.right, expectNumber);

        if(selectedNodes.size() != 0){
            selectedNodes.remove(selectedNodes.size());
        }
    }


    /**
     public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;

     }

     }
     */
}
