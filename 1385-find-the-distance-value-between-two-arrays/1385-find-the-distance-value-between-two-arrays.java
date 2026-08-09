import java.util.*;

class Solution {

    public int findTheDistanceValue(
        int[] arr1,
        int[] arr2,
        int d
    ) {

        Arrays.sort(arr2);

        int ans = 0;

        for (int x : arr1) {

            int idx = lowerBound(arr2, x - d);

            // No element >= x-d
            // OR first such element is greater than x+d
            if (idx == arr2.length || arr2[idx] > x + d) {
                ans++;
            }
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        int ans = arr.length;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {

                ans = mid;
                high = mid - 1;

            } else {

                low = mid + 1;
            }
        }

        return ans;
    }
}