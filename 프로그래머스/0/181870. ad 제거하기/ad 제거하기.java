import java.util.*;

class Solution 
{
    public ArrayList<String> solution(String[] strArr)
        {
            ArrayList<String> answer = new ArrayList<>();
            
            for(String str : strArr)
            {
                if(!str.contains("ad")) answer.add(str);
            }
            
            return answer;
        }
}