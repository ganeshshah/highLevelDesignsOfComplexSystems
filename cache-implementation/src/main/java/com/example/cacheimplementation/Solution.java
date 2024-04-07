package com.example.cacheimplementation;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int longestOnes(int[] nums, int k) {

        int left = 0, right = 0, maxOnes = 0, zeroCount = 0;

        while (right < nums.length) {
            if (nums[right] == 0)
                zeroCount++;

            while (zeroCount > k && left < nums.length) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }

            maxOnes = Math.max(maxOnes, right - left + 1);
            right++;
        }
        return maxOnes;

    }

    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,4,6,7,8,9,10};

        System.out.println(bs(array,0,8, 5));
    }

    private static boolean bs(int[] array, int left,int right, int search) {

        int mid = (right + left) / 2;

        if(left>right)
            return false;

        if(array[mid] == search)
            return true;
        else if(array[mid]>search)
            return bs(array,left,mid-1,search);
        else
            return bs(array,mid+1,right,search);
    }
}
