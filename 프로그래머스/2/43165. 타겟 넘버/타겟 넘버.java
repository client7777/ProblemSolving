class Solution {
    
    static int ans = 0;
    public int solution(int[] numbers, int target) 
    {
        dfs(numbers, 0, target, 0);
        return ans;
    }
    static void dfs(int[] numbers, int depth, int target, int calc)
    {
        if(depth == numbers.length)
        {
            if(target == calc)
            {
                ans++;
            }
        }
        else
        {
            dfs(numbers, depth + 1, target, calc + numbers[depth]);
            dfs(numbers, depth + 1, target, calc - numbers[depth]);
        }
    }
}