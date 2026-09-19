class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int makeT = longest(answerKey, k,'T');
        int makeF = longest(answerKey,k,'F');
        return Math.max(makeT,makeF);       
    }
    public int longest(String answerKey, int k, char target){
        int left=0;
        int ans=0;
        int changes=0;
        for(int right=0;right<answerKey.length();right++){
            if(answerKey.charAt(right)!=target){
            changes++;
            }
            while(changes>k){
                if(answerKey.charAt(left)!=target){
                    changes--;
                }
                left++;
            }
             ans = Math.max(ans, right - left + 1);
        }
        return ans;


    }
}