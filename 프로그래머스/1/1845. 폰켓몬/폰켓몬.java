import java.util.*;

class Solution 
{
    public int solution(int[] nums) 
    {
        HashSet<Integer> numSet = new HashSet<>();

        for(int val:nums)
        {
            numSet.add(val);
        }

        int max = nums.length / 2;

        return Math.min(numSet.size(), max);
    }
}