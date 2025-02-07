class Solution {
   public int solution(int n, String control)
    {
        int answer = n;

        char[] ch = control.toCharArray();

        for(int i=0; i< ch.length; i++)
        {
            if(ch[i] == 'w')
            {
                answer += 1;
            }
            else if(ch[i] == 's')
            {
                answer -= 1;
            }
            else if(ch[i] == 'd')
            {
                answer += 10;
            }
            else answer -= 10;
        }
        return answer;
    }
}