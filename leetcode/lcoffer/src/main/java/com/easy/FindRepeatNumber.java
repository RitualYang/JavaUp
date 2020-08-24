package com.easy;

import java.util.HashSet;

/**
 * 数组中重复的数字
 *
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每
 * 个数字重复了几次。请找出数组中任意一个重复的数字。
 * 示例 1：
 *   输入：
 *   [2, 3, 1, 0, 2, 5, 3]
 *   输出：2 或 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * @author wty
 * @create 2020-02-26 19:07
 */
public class FindRepeatNumber {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        //时间复杂度：O(N)      空间复杂度：O(N)
        int repeatNumber1 = findRepeatNumber1(nums);//执行用时：8ms,内存消耗：52.8MB
        //时间复杂度：O(N)      空间复杂度：O(1)
        int repeatNumber2 = findRepeatNumber2(nums);//执行用时：0ms,内存消耗：49.8MB

    }

    /**
     * 根据Set不重复的性质
     * @param nums
     * @return
     */
    public static int findRepeatNumber1(int[] nums) {
        HashSet hs = new HashSet();
        for (int i = 0; i < nums.length; i++){
            if (!hs.contains(nums[i])){
                hs.add(nums[i]);
            }else{
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 所有数全部小于数组长度，所以每一个数组下标对应有0或1或n个数据。如果当前位置的数据的值nums[i]，
     * 所对应的数组下标的数值nums[nums[i]]相等，为重复。
     * @param nums
     * @return
     */
    public static int findRepeatNumber2(int[] nums){
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[i] == nums[nums[i]]) {
                return nums[i];
            }
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }
}
