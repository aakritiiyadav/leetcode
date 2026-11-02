class Solution {
public int candy(int[] ratings) {


    int n = ratings.length;

    int[] candies = new int[n];

    // Initially give everyone 1 candy
    Arrays.fill(candies, 1);

    // Left to Right
    for (int i = 1; i < n; i++) {

        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1;
        }
    }

    // Right to Left
    for (int i = n - 2; i >= 0; i--) {

        if (ratings[i] > ratings[i + 1]) {
            candies[i] = Math.max(
                candies[i],
                candies[i + 1] + 1
            );
        }
    }

    // Calculate total candies
    int sum = 0;

    for (int candy : candies) {
        sum += candy;
    }

    return sum;
}


}
