class Solution 
{
    public int solution(int a, int b, int c) 
    {
        int answer = 0;
        
        if( a != b && b != c && a != c) //세 숫자가 모두 다른 경우
        {
            answer = a + b + c;
        }
        
        else if((a == b && a !=c) || (a == c && a != b) || (b == c && c != a)) 
            //세 숫자 중 어느 두 숫자만 같은 경우
        {
            answer = (a + b +c) * (a*a + b*b + c*c);
        }
        else if(a == b && b == c && a == c) //세 숫자 모드 같은 경우
        {
            answer = (a + b +c) * (a*a + b*b + c*c) * (a*a*a + b*b*b + c*c*c );
        }
        
        return answer;
    }
}