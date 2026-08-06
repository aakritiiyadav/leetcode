class Solution {
    public int smallestNumber(int n, int t) {
        for(int i =n; ;i++){
            int pro=1;
            int x=i;
            while(x>0){
                pro*=(x%10);
                x/=10;
            }
            if(pro%t==0)
                return i;
            
        }
    }
}