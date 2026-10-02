class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans= new ArrayList<>();
        helper(0,0,n,"", ans);
        return ans;
    }
    public static void helper(int unb, int count, int n, String p, List<String>ans){
        if(p.length()==2*n){
            ans.add(p);
            return;
        }
        if(count<n){
            helper(unb+1, count+1, n , p+"(",ans);
        }
        if(unb>0){
            helper(unb-1, count,n,p+")",ans);
        }
    }
}