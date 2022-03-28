package com.sp.algorithm.stringopr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
 * @author: luchao
 * @date: Created in 3/6/22 11:21 PM
 */
public class FindMaxSubStr {
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }

//        List<List<String>> segmentStrList = new ArrayList();
//
//        int result = 0;
//        int numCount = 0;
//        Map<String, Integer> appearedMap = new HashMap();
//        char[] tmpChars = s.toCharArray();
//        Integer curCount = 1;
//
//        List<String> eachSeg = new ArrayList();
//        for(int i = 0; i < tmpChars.length; i++){
//            char tmp = tmpChars[i];
//            if(appearedMap.get(String.valueOf(tmp)) == null){
//                numCount = 1;
//                appearedMap.put(String.valueOf(tmp), numCount);
//
//                if(curCount <= 1 && !eachSeg.contains(String.valueOf(tmp))) {
//                    eachSeg.add(String.valueOf(tmp));
//                    continue;
//                }
//            }else{
//                curCount = appearedMap.get(String.valueOf(tmp));
//                appearedMap.replace(String.valueOf(tmp), ++curCount);
//
//                if(eachSeg.size() > 0) {
//                    segmentStrList.add(eachSeg);
//                    eachSeg.clear();
//                    eachSeg.add(String.valueOf(tmp));
//                }
//            }
//        }
//
//        if(segmentStrList == null){
//            return 0;
//        }
//
//        int maxLenEleSize = 0;
//        for(int i = 0; i < segmentStrList.size(); i++){
//            maxLenEleSize = segmentStrList.get(i).size();
//            if(i+1 < segmentStrList.size() && segmentStrList.get(i+1).size() > maxLenEleSize){
//                maxLenEleSize = segmentStrList.get(i+1).size();
//            }
//        }
//        return maxLenEleSize;

        Map<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0;

        for (int end = 0; end < s.length(); end++){
            char tmp = s.charAt(end);
            if(map.containsKey(tmp)){
                start = Math.max(map.get(tmp) + 1, start);
            }else {
                max = Math.max(max, end - start + 1);
                map.put(tmp, end);
            }
        }

        return max;

//        动态规划，dp[i]代表以i结尾的不重复子字符串的最大长度， 那么，对于i，有两种情况：
//
//        s[i]在子字符串s[i-1 - dp[i-1], i-1]中且重复索引为j, dp[i] = i-j;
//        s[i]在不子字符串s[i-1 - dp[i-1], i-1]中且重复索引为j, dp[i] =dp[i-1] + 1;
//        int lengthOfLongestSubstring(string s) {
//            int length = s.length();
//            if(length == 0) return 0;
//            int dp[length];
//            int ans = 1;
//            dp[0] = 1;
//            for(int i=1;i<length;++i)
//            {
//                int res = s.find(s[i], i-dp[i-1]);
//                if(res >= i)
//                    dp[i] = dp[i-1] + 1;
//                else if(res < i )
//                    dp[i] = i-res;
//                ans = max(ans, dp[i]);
//            }
//            return ans;
//        }
    }

    public static void main(String[] args) {
        System.out.println(FindMaxSubStr.lengthOfLongestSubstring("abcabcbb"));
    }
}
