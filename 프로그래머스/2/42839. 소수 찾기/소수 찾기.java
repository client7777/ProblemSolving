import java.util.*;

class Solution 
{   
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] isUsed = new boolean[7]; // 주어진 문자열의 길이는 7이 최대
    public int solution(String numbers)
    {
        int answer = 0;

        dfs(numbers, "", 0);

        Iterator<Integer> setIterator = set.iterator();

        while (setIterator.hasNext())
        {
            if(isPrime(setIterator.next())) answer++;
        }

        return answer;
    }
    static void dfs(String numbers, String comb, int depth)
    {
        if(depth > numbers.length()) return;

        for(int i=0; i<numbers.length(); i++)
        {
            if(!isUsed[i])
            {
                isUsed[i] = true;
                set.add(Integer.parseInt(comb + numbers.charAt(i)));
                dfs(numbers, comb + numbers.charAt(i), depth + 1);
                isUsed[i] = false;
            }
        }
    }

    static boolean isPrime(int num)
    {
        if(num <= 1) return false;

        for(int i=2; i<=Math.sqrt(num); i++)
        {
            if(num % i == 0) return false;
        }

        return true;
    }
}