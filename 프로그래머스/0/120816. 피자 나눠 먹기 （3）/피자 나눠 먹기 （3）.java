class Solution {
    public int solution(int slice, int n) 
    {
        int ans = 0;
        
        while(true)
        {
            if(n <= 0) break;
            
            n -= slice;
            
            ans++;
                
        }
        
        return ans;
    }
}