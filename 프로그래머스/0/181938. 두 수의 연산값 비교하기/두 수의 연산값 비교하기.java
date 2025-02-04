class Solution 
{
    public int solution(int a, int b) 
    {
        int answer = 0;
        
        String ab = String.valueOf(a) + String.valueOf(b);
        
        int c = Integer.parseInt(ab);
        
        if(c > (2*a*b))
        {
            return c;    
        }
        
        else
        {
            return (2*a*b);
        }
    }
}