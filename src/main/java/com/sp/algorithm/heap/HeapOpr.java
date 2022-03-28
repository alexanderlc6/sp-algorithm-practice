package com.sp.algorithm.heap;

/**
 * @description: 堆-完全二叉树,复杂度为O(lgN),下沉/上浮路径即为树的高度
 * 大顶堆-下沉/上浮取最大结点值,小顶堆-上浮/下沉取最小结点值
 *
 * @author: luchao
 * @date: Created in 3/17/22 2:36 PM
 */
public class HeapOpr {
    private final static int [] a = new int[]{ 9,3,2,8,1,7,5,6,4};
    int n = 0;

    /**
     * 下沉--类似递增插入排序
     * @param i
     */
    public void sink(int i){
        int j = 0;
        int t = a[i];

        // 找到i节点的左子结点
        while ((j = (i << 1) + 1) < n){
            // 需要在两个后继中找个最大的出来, j < n - 1判断是否有右子结点,如果有,并且右子结点更大,则j指向右子结点
            if(j < n - 1 && a[j] < a[j + 1]){
                j++;
            }

            // 如果子结点比t大,那么t的位置还需要往后排
            if(a[j] > a[i]){
                a[i] = a[j];
                i = j;
            }else {
                // t即为最大结点值
                break;
            }
        }

        // 将t放在找到的位置那里
        a[i] = t;
    }

    /**
     * 上浮
     * @param i
     */
    public void swim(int i){
        int t = a[i];
        int par = 0;

        // 如果还存在父结点
        while (i > 0 && (par = (i - 1) >> 1) != i){
            // 如果父结点比t值小,则向下移动父结点的值
            if(a[par] < t){
                a[i] = a[par];
                i = par;
            }else{
                break;
            }
        }

        a[i] = t;
    }

    /**
     * push操作(尾部插入)
     * @param v
     */
    public void push(int v){
        a[n++] = v;
        swim(n - 1);
    }

    /**
     * pop操作(顶部冒出)
     * @return
     */
    public int pop(){
        int ret = a[0];
        a[0] = a[--n];
        sink(0);
        return ret;
    }

    /**
     * 查看堆大小
     * @return
     */
    public int size(){
        return n;
    }
}
