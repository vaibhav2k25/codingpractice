package dev.scorpio;

public class LongestSubarraySum {

    /*public static int longestSubarrayWithSumAtMostK(int[] nums, int k) {
        // TODO: Implement the logic here
        int left = 0;
        int right = 0;
        int sum = 0;
        int len = 0;
        int maxSoFar = 0;
        while (left < nums.length && right < nums.length) {
            sum += nums[right];
            right++;
            while (sum > k) {
                len = right - left ;
                maxSoFar = Math.max(len, maxSoFar);
                sum-=nums[left];
                left++;
            }
        }
        return maxSoFar;
    }*/

    public static int longestSubarrayWithSumAtMostK(int[] nums, int k) {
        int left = 0, sum = 0, maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // Shrink window while sum > k
            while (sum > k && left <= right) {
                sum -= nums[left];
                left++;
            }

            // Now sum is <= k, update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }


    public static void main(String[] args) {
        int[] nums = {10, 11, 12};
        int k = 5;

        int result = longestSubarrayWithSumAtMostK(nums, k);
        System.out.println("Length of longest subarray with sum ≤ " + k + " is: " + result);
    }
}
