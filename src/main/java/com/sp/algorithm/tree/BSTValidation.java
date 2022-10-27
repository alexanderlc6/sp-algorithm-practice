package com.sp.algorithm.tree;

public class BSTValidation {
    private boolean ans = true;

    /**
     * 前序遍历BST
     * 通过影子二叉树校验是否BST合法
     * @param root
     * @param l
     * @param r
     */
    private void preOrder(TreeNode root, Long l, Long r){
        if(root == null || !ans){
            return;
        }

        if(!(l < root.val && root.val < r)){
            ans = false;
            return;
        }

        //遍历左子树,(l,root.val)为影子二叉树的左子节点
        preOrder(root.left, l, Long.valueOf(root.val));
        //遍历右子树,(root.val, r)为影子二叉树的右子节点
        preOrder(root.right, Long.valueOf(root.val), r);
    }

    /**
     * 中序遍历BST
     * 校验是否BST合法
     * @param root
     * @param l
     * @param r
     */
    private Long preValue = Long.MIN_VALUE;
    private void midOrder(TreeNode root){
        if(!ans){
            return;
        }

        if(root != null){
            midOrder(root.left);
            //中序遍历时与前一节点值比较即可
            if(preValue >= root.val){
                ans = false;
                return;
            }

            //比较完成后,更新下前节点的值
            preValue = Long.valueOf(root.val);
            midOrder(root.right);
        }
    }

    public Boolean isValidBST(TreeNode root){
        ans = true;
        preOrder(root, Long.MIN_VALUE, Long.MAX_VALUE);
//        midOrder(root);
        return ans;
    }

    /**
     * 后序遍历验证BST
     */
    class Range {
        //[inf,-inf]
        public Long min = Long.MAX_VALUE;
        public Long max = Long.MIN_VALUE;

        public Range(){
        }

        public Range(Long l, Long r){
            min = l;
            max = r;
        }

        private Range emptyRange = new Range();
        private Range postOrder(TreeNode root){
            if(root == null || !ans){
                return emptyRange;
            }

            Range l = postOrder(root.left);
            Range r = postOrder(root.right);
            if(!(l.max < root.val && root.val < r.min)){
                ans = false;
                return emptyRange;
            }

            //需取左子树最小值核当前节点的最小值,需取右子树最大值核当前节点的最大值,
            return new Range(Math.min(l.max, root.val), Math.max(r.max, root.val));
        }

        public Boolean isValidBST(TreeNode root){
            ans = true;
            postOrder(root);
            return ans;
        }
    }
}
