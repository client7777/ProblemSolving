import java.util.*;

class Solution 
{
    public int solution(String[][] clothes)
    {
        int answer = 0;

        HashMap<String, Integer> map = new HashMap<>();

        for(String[] row:clothes)
        {
            String type = row[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        int cnt = 1;
        for(String key:map.keySet())
        {
            cnt *= (map.get(key) + 1);
        }

        answer = cnt - 1;

        return answer;
    }
}