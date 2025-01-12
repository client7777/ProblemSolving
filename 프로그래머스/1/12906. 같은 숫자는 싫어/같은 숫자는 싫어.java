import java.util.*;

public class Solution
{
    public int[] solution(int []arr)
    {
        Stack<Integer> stack = new Stack<>();

        stack.add(arr[0]);

        for(int i=1; i<arr.length; i++)
        {
            if(stack.peek() == arr[i]) continue;
            stack.add(arr[i]);
        }

        int[] ans = new int[stack.size()];

        for(int i=ans.length-1; i >= 0; i--)
        {
            ans[i] = stack.pop();
        }

        return ans;
    }
}