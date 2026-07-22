class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashSet<Character> map= new HashSet<>();
        int left=0;
        int ans=0;
        for(int right=0;right<n;right++){
            char ch= s.charAt(right);
            while(map.contains(ch)){
                map.remove(s.charAt(left));
                left++;
            }
            map.add(ch);
            ans=Math.max(ans,right-left+1);
        }
        return ans;

    }
}