package com.sp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/27/22 5:25 PM
 */
public class Box {
    private List<Integer> l = new ArrayList();

    public Box(List<Integer> l) {
        this.l = l;
    }

    public void push(int x){
        l.add(x);
    }

    public void pop(){
        l.remove(l.size() - 1);
    }

    public void print(){
        System.out.print("{");
        for (int x : l){
            System.out.print(x + ",");
        }
        System.out.println("}");
    }
}
