import java.util.*;

class Solution {

    public long findKthSmallest(int[] coins, int k) {

        long left = 1;
        long right = (long) k * getMin(coins);

        while (left < right) {

            long mid = left + (right - left) / 2;

            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(long x, int[] coins) {

        int n = coins.length;
        long result = 0;

        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    long g = gcd(lcm, coins[i]);
                    long part = lcm / g;

                    // Prevent overflow
                    if (part > x / coins[i]) {
                        overflow = true;
                        break;
                    }

                    lcm = part * coins[i];
                }
            }

            if (overflow || lcm > x) {
                continue;
            }

            long contribution = x / lcm;

            if (bits % 2 == 1) {
                result += contribution;
            } else {
                result -= contribution;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private int getMin(int[] coins) {

        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            min = Math.min(min, coin);
        }

        return min;
    }
}