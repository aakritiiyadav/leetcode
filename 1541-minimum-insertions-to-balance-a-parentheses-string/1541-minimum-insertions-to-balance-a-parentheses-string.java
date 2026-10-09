class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int ans = 0;

        int i = 0;
        int n = s.length();

        while (i < n) {

            if (s.charAt(i) == '(') {
                open++;
            } 
            else {
              
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++;
                } 
                else {
                   
                    ans++;
                }

                if (open == 0) {
                 
                    ans++;
                } 
                else {
                    open--;
                }
            }

            i++;
        }

     
        ans += 2 * open;

        return ans;
    }
}