package com.sp.algorithm.tree;

public class BSTRecovery {
    //第一个交换位置
    private TreeNode first = null;
    //第二个交换位置
    private TreeNode second = null;
    //记录中序遍历前面的位置
    private TreeNode pre = null;

    private void midOrder(TreeNode root){
        //Morris遍历
        TreeNode cur = root;
        while (cur != null){
            if(cur.left != null){
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur){
                    pre = pre.right;
                }

                if(pre.right != null){
                    pre.right = cur;
                    cur = cur.left;
                }else {
                    check(cur);
                    pre.right = null;
                    cur = cur.right;
                }
            }else {
                check(cur);
                cur = cur.right;
            }
        }
    }

    /**
     * 检查是不是交换位置
     * @param cur
     */
    public void check(TreeNode cur){
        // 如果原来的顺序是[1, 2, 3, 4, 5, 6, 7]
        // 两个节点交换之后[1, 6, 3, 4, 5, 2, 7]
        // 第一次是 6 > 3, 需要记住6
        if(pre != null && pre.val > cur.val){
            if(first == null){
                first = pre;
            }

            // 第二次是 5 > 2,此时需要记住2
            second = cur;
        }

        //更新pre
        pre = cur;
    }

    public void recoveryBST(TreeNode root){
        first = second = pre = null;
        midOrder(root);

        if(first != null && second != null){
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }
}
