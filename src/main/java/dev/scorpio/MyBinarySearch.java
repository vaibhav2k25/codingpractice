package dev.scorpio;

public class MyBinarySearch {

    static int[] arr = {1,3,5,7,9,11,13,17,19,20,44,66};

    static int target = 44;

    public static void main(String[] args) {
        int index = binarySearchRecursive(arr, target);
        System.out.println("The target found at: "+index);
    }

    private static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;

        while(left<right){
            int mid = left + (right-left)/2;

            if(arr[mid] == target)
                return mid;
            else if(arr[mid]<target)
                left = mid+1;
            else
                right = mid -1;
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        return binarySearchRecursive(arr, left, right);
    }

    private static int binarySearchRecursive(int[] arr, int left, int right) {
        int mid = left + (right-left)/2;

        if(arr[mid] == target)
            return mid;
        else if(arr[mid] <target)
            return  binarySearchRecursive(arr, mid+1, right);
        return binarySearchRecursive(arr, left, mid-1);
    }


}
