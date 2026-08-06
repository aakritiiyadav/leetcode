class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int low=1;
        int high= 0;
          for (int quantity : quantities)
            high = Math.max(high, quantity);
        while (low < high) {
    int mid = low + (high - low) / 2;

    if (isPossible(quantities, n,mid))
        high = mid;
    else
        low = mid + 1;
}

return low;
    }
    private boolean isPossible(int[] quantities, int n, int limit) {

    long stores = 0;

    for (int quantity : quantities) {
        stores += (quantity + limit - 1) / limit;

        if (stores > n)
            return false;
    }

    return true;
}
}