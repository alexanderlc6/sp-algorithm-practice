package com.sp.algorithm.normal;

/**
 * @description: 字符串操作
 * @author: luchao
 * @date: Created in 3/23/22 1:49 PM
 */
public class StringOpr {
    public static int reverse(int x) {
        if(x == 0){
            return 0;
        }

        char[] numChars = String.valueOf(x).toCharArray();
        String result = "";
        boolean isContain = false;
        for(int i = numChars.length - 1; i >= 0 ; i--){
            if(i == 0 && numChars[i] == '-'){
                isContain = true;
                break;
            }
            result += numChars[i];
        }

        return isContain ? Integer.parseInt("-" + result) : Integer.parseInt(result);
    }

    public static void main(String[] args) {
        System.out.println(StringOpr.reverse(-123));
    }
}
