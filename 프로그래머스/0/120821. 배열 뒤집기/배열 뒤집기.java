import java.util.*;

class Solution 
{
    public ArrayList<Integer> solution(int[] num_list)
    {
       ArrayList<Integer> answer = new ArrayList<>();
       
       for(int val : num_list)
       {
           answer.add(val);
       }
       
       Collections.reverse(answer);
       
       return answer;
    }
}