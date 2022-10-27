package com.sp.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NormalOpr {
    /**
     * 前序遍历
     * 时复: O(n),空复: O(h)--h为树高
     * @param root
     * @param ans
     */
    void preOrder(TreeNode root, List<Integer> ans){
        if(root == null){
            return;
        }

        ans.add(root.val);
        preOrder(root.left, ans);
        preOrder(root.right,ans);
    }

    public List<Integer> preOrderTraversalByNormal(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        preOrder(root, ans);
        return ans;
    }

    /**
     * 非递归实现前序遍历
     * 时复: O(n),空复: O(h)--h为树高,空间由树高决定
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversalByNonRecursion(TreeNode root){
        Stack<TreeNode> s = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);

                //存放遍历结果
                ans.add(root.val);
                root = root.left;
            }

            //当无法压栈时,将root.right压栈
            root = s.peek();
            s.pop();
            root = root.right;
        }

        return ans;
    }

    /**
     * Morrir前序遍历, O(1)
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversalByMorris(TreeNode root){
        TreeNode cur = root;
        List<Integer> ans = new ArrayList<>();

        while (cur != null){
            if(cur.left != null){
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur){
                    pre = pre.right;
                }

                if(pre.right == null){
                    ans.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                }else {
                    pre.right = null;
                    cur = cur.right;
                }
            }else {
                ans.add(cur.val);
                cur = cur.right;
            }
        }

        return ans;
    }

    /**
     * 中序遍历
     * @param root
     * @param ans
     */
    void midOrder(TreeNode root, List<Integer> ans){
        if(root == null){
            return;
        }

        midOrder(root.left, ans);
        ans.add(root.val);
        midOrder(root.right, ans);
    }

    /**
     * 非递归中序遍历
     * @param root
     */
    List<Integer> midOrderByNonRecursion(TreeNode root){
        Stack<TreeNode> s = new Stack<>();
        List<Integer> ans = new ArrayList<>();

        while (root != null || !s.isEmpty()){
            //向左边走连续入栈,直到不能走为止
            while (root != null){
                s.push(root);
                root = root.left;
            }

            //到达最左边，弹出节点进行遍历
            root = s.peek();
            s.pop();
            ans.add(root.val);
            //转向右子树
            root = root.right;
        }

        return ans;
    }

    /**
     * 后序遍历
     * @param root
     * @param ans
     */
    void postOrder(TreeNode root, List<Integer> ans){
        if(root == null){
            return;
        }

        midOrder(root.left, ans);
        midOrder(root.right, ans);
        ans.add(root.val);
    }

    /**
     * 非递归后序遍历
     * @param root
     */
    List<Integer> postOrderByNonRecursion(TreeNode root){
        Stack<TreeNode> s = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode pre = null;

        while (root != null || !s.isEmpty()){
            //向左边走连续入栈,直到不能走为止
            while (root != null){
                s.push(root);
                root = root.left;
            }

            //到达最左边，弹出节点进行遍历
            root = s.peek();

            if(root.right == null || root.right == pre){
                ans.add(root.val);
                s.pop();
                pre = root;
                root = null;
            }else {
                root = root.right;
            }
        }

        return ans;
    }


    /**
     * 插入BST节点
     * @param root
     * @param value
     * @return
     */
    public TreeNode insertToBST(TreeNode root, int value){
        if(root == null){
            return new TreeNode(value);
        }

        if(value < root.val){
            root.left = insertToBST(root.left, value);
        }else if(value > root.val){
            root.right = insertToBST(root.right, value);
        }

        return root;
    }

    /**
     * 搜索BST节点
     * @param root
     * @param value
     * @return
     */
    public TreeNode searchBST(TreeNode root, int value){
        TreeNode ans = null;
        while (root != null){
            if(root.val == value){
                return root;
            } else if(value < root.val){
                root =  root.left;
            } else if(value > root.val){
                root = root.right;
            }
        }

        return root;
    }

    /**
     * 删除BST指定节点,返回删除之后的根节点
     * 平衡BST: O(lgn),非平衡BST: O(n)
     * @param root
     * @param key
     */
    public TreeNode deleteNode(TreeNode root, int key){
        //Case1
        if(root == null){
            return null;
        }

        //Case2
        if(key < root.val){
            root.left = deleteNode(root.left, key);
        }else if(key > root.val){
            //Case3
            root.right = deleteNode(root.right, key);
        }else {
            //Case4
            //当前树只有1个节点
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null) {
                //若删除的节点有左子树，则需在左子树中找到最大值、与当前节点交换值(非Node)、再在左子树中删除value
                TreeNode large = root.left;
                while (large.right != null) {
                    large = large.right;
                }
                swapValue(root,large);
                root.left = deleteNode(root.left, key);
            }else if(root.right != null) {
                //若删除的节点只有右子树，则需在右子树中找到最小值、与当前节点交换值(非Node)、再在右子树中删除value
                TreeNode small = root.right;
                while (small.left != null){
                    small = small.left;
                }
                swapValue(root, small);
                root.right = deleteNode(root.right, key);
            }
        }

        return root;
    }

    public void swapValue(TreeNode a, TreeNode b){
        int t = a.val;
        a.val = b.val;
        b.val = t;
    }

    /**
     * 二叉搜索树节点最小距离
     * 给定一个二叉搜索树的根节点root，返回树中任意两节点的差的最小值
     * 输入: root = [4,2,6,1,3,null,null]
     * 输出: 1
     * ⁠         4
     * ⁠       /   \
     * ⁠     2      6
     * ⁠    / \
     * ⁠   1   3
     *
     * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
     */
    TreeNode pre = null;
    int ans = Integer.MAX_VALUE;
    public void midOrder(TreeNode root){
        if(root != null){
            midOrder(root.left);

            if(pre != null){
                ans = Math.min(ans, root.val - pre.val);
            }

            pre = root;
            midOrder(root.right);
        }
    }

    public int minDiffInBST(TreeNode root){
        if(root == null){
            return 0;
        }

        midOrder(root);
        return ans;
    }

    public static void main(String[] args) {

    }
}
