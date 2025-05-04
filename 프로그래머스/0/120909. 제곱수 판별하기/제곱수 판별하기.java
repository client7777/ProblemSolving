class Solution 
{
    public int solution(int n)
    {
        //n이 제곱수라면 1, 아니라면 2를 리턴

        boolean[] flag = new boolean[1_000_001];

        for(int i=1; i*i<= flag.length; i++)
        {
            flag[i*i] = true;
        }

        return flag[n] ? 1 : 2;
    }
}