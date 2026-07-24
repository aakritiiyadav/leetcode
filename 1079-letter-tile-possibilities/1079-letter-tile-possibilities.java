class Solution {

    int count = 0;

    public int numTilePossibilities(String tiles) {

        int[] freq = new int[26];

        for (char ch : tiles.toCharArray()) {
            freq[ch - 'A']++;
        }

        dfs(freq);

        return count;
    }

    private void dfs(int[] freq) {

        for (int i = 0; i < 26; i++) {

            if (freq[i] == 0)
                continue;

            // choose this letter
            count++;

            freq[i]--;

            dfs(freq);

            // backtrack
            freq[i]++;
        }
    }
}