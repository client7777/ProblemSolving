import java.util.*;

class Solution 
{
    public int solution(int[] citations)
    {
        int answer = 0;

        Arrays.sort(citations);

        int h_index = citations.length;

        for(int val:citations)
        {
            if(val >= h_index) break;
            else h_index--;
        }
        
        answer = h_index;
        
        return answer;
    }
}