package com.sp.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/18/22 1:58 PM
 */
public class PhoneCall {
    private final static String[] dsKeys = new String[]{
            "","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };

    public void backtrace(String a, int i, StringBuffer box, List<String> ans){
        final int n = a == null ? 0 : a.length();

        //已递归完毕,状态已满足需求
        if(box.length() == n){
            ans.add(box.toString());
        }

        if(i >= n){
            return;
        }

        final int stoneIndex = (int)(a.charAt(i) - '0');
        for (int idx = 0; idx < dsKeys[stoneIndex].length(); idx++) {
            //拿到宝石
            Character stone = dsKeys[stoneIndex].charAt(idx);

            //放入箱子中
            box.append(stone);

            //传递/开始处理第i+1个人
            backtrace(a, i+1, box, ans);

            //取出自己的宝石保持箱子原样
            box.deleteCharAt(box.length() - 1);
        }
    }

    public List<String> letterCombinations(String a){
        if(a == null || a.length() == 0){
            return null;
        }

        StringBuffer box = new StringBuffer();
        List<String> ans = new ArrayList<>();
        backtrace(a, 0, box, ans);
        return ans;
    }

    public static void main(String[] args) {
        List<String> tt = new PhoneCall().letterCombinations("253");
        tt.stream().forEach(t -> System.out.print(t + ","));
        System.out.println();
    }
}
