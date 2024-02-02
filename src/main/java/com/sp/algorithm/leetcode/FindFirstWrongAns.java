package com.sp.algorithm.leetcode;

/**
 * @author Alex
 * @description 找出第一次出问题的代码版本
 * 线上某天某个人发布一个有问题的代码版本上线，在此版本后续所有的代码都是有问题的。
 * 现在已经有一个工具类函数 checkRight 可以检测出有问题的代码版本，给定发布以来所有的代码版本号（**已经按照提交时间排序**），
 * 写一段程序找到第一个有问题的代码版本。
 * @date 2024/2/2 11:50
 */
public class FindFirstWrongAns {

    public static void main(String[] args) {

        // String[] versions = new String[]{"1", "2", "3", "wrong1", "wrong2"};
        // String[] versions = new String[]{ "wrong1", "wrong2"};
        String[] versions = new String[]{"1", "2", "3", "4", "wrong1", "wrong2"};
//        String[] versions = new String[]{"hash1", "abcd2", "qoweioqhw3","doadjadj4", "wrong1ashdauhdu", "wrong2qweqweqwe"};
        System.out.println(findFirstWrong(versions));
    }

    /**
     * Retrieve the first version of the code that contains bugs and is incorrect.
     * @param versions An array of code versions ordered by commit time.
     * @return The first incorrect version of the code.
     */
    public static String findFirstWrong(String[] versions) {
        if(versions == null || versions.length == 0){
            return null;
        }

        int num = versions.length;
        if(num == 1){
            return versions[num];
        }

        int start = 1, end = num;
        while (start < end){
            int mid = (start + end) / 2;
            if(!checkRight(versions[mid])){
                end = mid;
            }else {
                start = mid + 1;
            }
        }

        return versions[start];
    }

    /**
     * Verify the correctness and absence of bugs in the code.
     * @param version The hash of the code from a commit.
     * @return True if the code version is correct; otherwise, return False.
     */
    public static boolean checkRight(String version) {

        // Just mock some time consuming check
        doSomeTimeConsumingCheck();

        if (version.startsWith("wrong")) {
            return false;
        } else {
            return true;
        }
    }

    public static void doSomeTimeConsumingCheck() {
        try {
            Thread.sleep(1000L);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
