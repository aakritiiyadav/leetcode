class Solution {

    public boolean canConstruct(String s, int k) {

        if (k > s.length())
            return false;

        int[] freq = new int[26];

        for (char ch : s.toCharArray())
            freq[ch - 'a']++;

        int odd = 0;

        for (int count : freq)
            if (count % 2 == 1)
                odd++;

        return odd <= k;
    }
}