package com.sp.algorithm.tree;

/**
 * 找到BST节点的最低公共祖先
 *
 */
public class FindLowestCommonAncestor {
    private TreeNode ans = null;
    private int postOrder(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return 0;
        }

        int lCnt = postOrder(root.left, p, q);
        int rCnt = postOrder(root.right, p, q);

        if(lCnt == rCnt){
            ans = root;
        }else if(lCnt == 1 || rCnt == 1){
            if(root == p || root == q){
                ans = root;
            }
        }

        //返回以root为根的子树,统计p,q节点个数
        return lCnt + rCnt + ((root == p || root == q) ? 1 : 0);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        ans = null;
        postOrder(root, p, q);
        return ans;
    }
}
