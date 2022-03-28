package com.sp.algorithm.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * @author: luchao
 * @date: Created in 3/7/22 3:48 PM
 */
public class MergeArea {

    public List<List<Integer>> mergeArea(List<List<Integer>> originData){
        Map<Integer, List<Integer>> areaMap = new HashMap<>();

        List<Integer> segmentNums = new ArrayList<>();
        int segId = 0;

        for (int i = 0; i < originData.size(); i++) {
            List<Integer> tmpList = originData.get(i);
            List<Integer> convList = new ArrayList<>();

            for (int j = 0; j < tmpList.size(); j++) {
                if(areaMap == null || areaMap.containsKey(segId)){
                    segId = 1;
                    convList.add(tmpList.get(j));
                }else {
                    List<Integer> existList = areaMap.get(segId);
                    existList.add(tmpList.get(j));
                }

                areaMap.put(segId, convList);
                segId++;
            }
        }

        return null;
    }
}
