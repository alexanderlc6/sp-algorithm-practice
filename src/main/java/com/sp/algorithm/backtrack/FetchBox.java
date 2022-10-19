package com.sp.algorithm.backtrack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/27/22 5:28 PM
 */
public class FetchBox {
    public void solve(int[] a, int i, Box s){
        final int n = a == null ? 0 : a.length;
        s.print();
        if(i >= n){
            return;
        }

        //把自己选中的宝石放进去
        s.push(a[i]);

        //借给下一个人
        solve(a, i + 1, s);

        //把自己的宝石取出来，再把箱子按原样还回去
        s.pop();
    }

    public static void main(String[] args) {
        FetchBox fetchBox = new FetchBox();
        int[] a = {1,2,3};
        List<Integer> dataList = Arrays.stream(a).boxed().collect(Collectors.toList());
        fetchBox.solve(a, 3, new Box(dataList));
    }
}
