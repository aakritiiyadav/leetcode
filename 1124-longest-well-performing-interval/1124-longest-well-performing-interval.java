class Solution {
    public int longestWPI(int[] hours) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int ans = 0;

        for (int i = 0; i < hours.length; i++) {

            if (hours[i] > 8)
                sum++;
            else
                sum--;

            if (sum > 0) {
                ans = i + 1;
            } else {

                if (map.containsKey(sum - 1)) {
                    ans = Math.max(ans, i - map.get(sum - 1));
                }
            }

            map.putIfAbsent(sum, i);
        }

        return ans;
    }
}