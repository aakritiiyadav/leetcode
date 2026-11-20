class Solution {
    int[][] coins;
        long[] prefix;
        int n;
    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins,(a,b)->Integer.compare(a[0],b[0]));
        this.coins=coins;
        n= coins.length;
        prefix= new long[n+1];
        for(int i =0;i<n;i++){
            long l = coins[i][0];
            long r = coins[i][1];
            long c= coins[i][2];
            prefix[i+1]=prefix[i]+(r-l+1)*c;
        }
        long ans=0;
        for(int i =0;i<n;i++){
            long l = coins[i][0];
            long r = coins[i][1];
            ans= Math.max(ans,getSum(l,k));
            ans=Math.max(ans,getSum(r-k+1,k));
        }
        return ans;

    }
    private long getSum(long x, int k ){
        long right= x+k-1;
        return prefixSum(right)-prefixSum(x-1);
    }
    private long prefixSum(long x){
        if(x<=0)return 0;
        int low =0;
        int high= n-1;
        int idx=-1;
        while(low<=high){
            int mid= low+(high-low)/2;
            if(coins[mid][0]<=x){
                idx= mid;
                low= mid+1;
            }else{
                high= mid-1;
            }
        }
        if(idx==-1)return 0;
        long l = coins[idx][0];
        long r = coins[idx][1];
        long c = coins[idx][2];
        long contribution=(Math.min(x,r)-l+1)*c;
        return prefix[idx]+contribution;
    }
}