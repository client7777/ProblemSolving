import java.util.*;

class Solution 
{
    public String solution(String[] participant, String[] completion)
    {
       HashMap<String, Integer> parti = new HashMap<>();

        for(String str : participant)
        {
            parti.put(str, parti.getOrDefault(str, 0) + 1);
        }

        for(String str : completion)
        {
            parti.put(str, parti.get(str) - 1);
        }

        String ans = "";
        for(String key : parti.keySet())
        {
            if(parti.get(key) != 0)
            {
                ans = key;
                break;
            }
        }
        return ans;
    }
}