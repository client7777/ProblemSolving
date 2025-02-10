class Solution 
{
    public int solution(int[][] sizes)
    {
        int row = 0;
        int cal = 0;

        for(int[] size:sizes)
        {
            row = Math.max(row, Math.max(size[0], size[1]));
            cal = Math.max(cal ,Math.min(size[0], size[1]));
        }
        return row * cal;
    }
}