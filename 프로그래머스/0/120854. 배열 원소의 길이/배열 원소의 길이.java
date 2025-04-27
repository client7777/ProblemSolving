import java.util.*;

class Solution 
{
    public ArrayList<Integer> solution(String[] strlist)
    {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(String str : strlist)
        {
            answer.add(str.length());
        }
        
        return answer;
    }
}