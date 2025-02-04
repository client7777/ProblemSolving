class Solution {
    public int solution(int a, int d, boolean[] included) {
        
        //a = 초항, d = 공차
        //included에는 true가 적어도 하나는 존재
        //일반항 = an = a1 + (n - 1)d
        
        int answer = 0;
        
        for(int i=0; i<included.length; i++)
        {
            if(included[i])
            {
                answer += a + i*d;
                
            }
        }
        
        return answer;
    }
}