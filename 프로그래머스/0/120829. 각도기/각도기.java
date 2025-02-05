class Solution 
{
   public int solution(int angle)
    {
        int ans = 0;
        
        if(angle > 0 && angle < 90)
            ans = 1;
        else if(angle == 90)
            ans = 2;
        else if(angle > 90 && angle < 180)
            ans = 3;
        else if(angle == 180)
            ans = 4;
        
        return ans;
    }
}