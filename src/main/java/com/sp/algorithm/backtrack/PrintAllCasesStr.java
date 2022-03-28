package com.sp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 784.字母大小写全排列
 * https://leetcode-cn.com/problems/letter-case-permutation/description/
 * @author: luchao
 * @date: Created in 3/27/22 6:33 PM
 */
public class PrintAllCasesStr {

    public void backTrace(String s, int i, StringBuffer box, List<String> ans){
        final int n = s.length();

        if(i > n){
            return;
        }

        if(i == n){
            ans.add(box.toString());
            return;
        }

        final char c = s.charAt(i);
        if(c >= '0' && c <= '9'){
            box.append(c);
            backTrace(s, i + 1, box, ans);
            box.setLength(box.length() - 1);
        }else {
            //小写字母
            box.append(Character.toLowerCase(c));
            backTrace(s, i + 1, box, ans);
            box.setLength(box.length() - 1);

            //大写字母
            box.append(Character.toUpperCase(c));
            backTrace(s, i + 1, box, ans);
            box.setLength(box.length() - 1);
        }
    }

    public List<String> letterCombination(String s){
        List<String> ans = new ArrayList<>();
        StringBuffer box = new StringBuffer();
        backTrace(s, 0, box, ans);
        return ans;
    }

    public static void main(String[] args) {
        List<String> tt = new PrintAllCasesStr().letterCombination("a1b2");
        tt.stream().forEach(t -> System.out.print(t + ","));
        System.out.println();
    }
}
