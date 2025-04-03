class Solution 
{
    public int solution(String num_str)
        {
            int answer = 0;

            String[] arr = num_str.split("");
            
            for(int i=0; i<arr.length; i++)
            {
                answer += Integer.parseInt(arr[i]);
            }
            
            return answer;
        }
}