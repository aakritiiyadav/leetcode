class Solution {
    public String removeOuterParentheses(String s) {
        int depth =0;
        String ans= "";
        int n =s.length();
        for(int i =0;i<n;i++){
            if(s.charAt(i)=='('){
                if(depth>0){
                ans=ans+"(";
                }
                depth++;
            }
            else if (s.charAt(i)==')'){
                depth--;
                if(depth>0){
                ans=ans+")";
                }
                
            }
        }
        return ans;
    }
}
