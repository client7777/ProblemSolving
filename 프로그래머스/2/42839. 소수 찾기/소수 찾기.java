import java.util.*;

class Solution 
{   
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] isUsed = new boolean[7];

    public int solution(String numbers)
    {
        int answer = 0;

        dfs(numbers, "", 0);

        for(Integer num:set)
        {
            if(isPrime(num)) answer++;
        }

        return answer;
    }
    
    static void dfs(String numbers, String comb, int depth)
    {
        if(depth > numbers.length()) // 탐색 깊이가 주어진 문자열의 길이보다 길어지면 탐색 중지
            return;

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

    //소수 판별 함수
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