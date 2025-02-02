class Solution {
    public int solution(int n) {
        int answer = 0;
        
        if(n%2 != 0) //n이 홀수인 경우
        {
            for(int i=1; i<=n; i = i+2)
            {
                answer+=i;
            }
            
        }
        else
        {
            for(int i=2; i<=n; i = i+2)
            {
                answer += i * i;
            }
            
        }
        
        return answer;
    }
}