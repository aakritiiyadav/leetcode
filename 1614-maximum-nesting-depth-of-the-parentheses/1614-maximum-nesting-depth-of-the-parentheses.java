class Solution {
    public int maxDepth(String s) {
        int currD= 0;
        int maxD=0;
        int n =s.length();
        for(int i =0; i<n;i++){
            if(s.charAt(i)=='('){
                currD++;
                maxD=Math.max(currD,maxD);
            }else if(s.charAt(i)==')'){
                currD--;
            }
        }
        return maxD;
    }
}