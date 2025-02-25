class Solution 
{
    public int solution(int n)
    {
        //피보나치 수열의 n번째 항
        int[] d = new int[n+1];
        
        d[0] = 0;
        d[1] = 1;
        d[2] = 1;
        
        for(int i=3; i<=n; i++)
        {
            d[i] = (d[i-1] + d[i-2]) % 1234567;
        }
        
        return d[n];
    }
}