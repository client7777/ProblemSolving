class Solution 
{
    static int max = Integer.MIN_VALUE;
    static int length;
    
    public int solution(int k, int[][] dungeons)
    {
        length = dungeons.length;

        boolean[] visit = new boolean[length];

        backTrack(0,k,0,visit,dungeons);

        return max;
    }

    static void backTrack(int depth, int cur, int cnt, boolean[] visit, int[][] dungeons)
    {
        max = Math.max(max, cnt);

        for(int i=0; i<length; i++)
        {
            if(!visit[i])
            {
                if(cur >= dungeons[i][0])
                {
                    visit[i] = true;
                    cur -= dungeons[i][1];
                    backTrack(depth + 1, cur, cnt + 1, visit, dungeons);
                    cur += dungeons[i][1];
                    visit[i] = false;
                }
            }
        }
    }
}