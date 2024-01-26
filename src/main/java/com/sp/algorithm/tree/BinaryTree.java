package com.sp.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree<E> {

    public TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode treeNode = null;

        //判断输入序列list是否等于null,或者输入序列list为空
        if(list == null || list.isEmpty()){
            return null;
        }

        //获取list第一个数据
        Integer data = list.removeFirst();
        if(data == null){
            return null;
        }

        treeNode = new TreeNode(data);
        treeNode.left = createBinaryTree(list);
        treeNode.right = createBinaryTree(list);
        return treeNode;
    }

    public static void main(String[] args) {
        Integer[] nodes = new Integer[] {1,2,4,null,null,5,null,null,3,null,6};
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(nodes));
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        TreeNode root = binaryTree.createBinaryTree(list);

        NormalOpr normalOpr = new NormalOpr();
        List<Integer> ans = new ArrayList<>();

        System.out.println("前序遍历");
        normalOpr.preOrder(root, ans);
        ans.forEach(t -> System.out.print(t));

        ans = new ArrayList<>();
        System.out.println("中序遍历");
        normalOpr.midOrder(root, ans);
        ans.forEach(t -> System.out.print(t));

        ans = new ArrayList<>();
        System.out.println("后序遍历");
        normalOpr.postOrder(root, ans);
        ans.forEach(t -> System.out.print(t));
    }
}
