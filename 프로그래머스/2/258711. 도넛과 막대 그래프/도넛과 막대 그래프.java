import java.util.*;

class Solution
{
    public int[] solution(int[][] edges)
    {
        Map<Integer, int[]> nodes = new HashMap<>();

        int extraNode = 0;
        int doughnut = 0;
        int stick = 0;
        int figure8 = 0;

        for(int[] edge:edges)
        {
            int start = edge[0];
            int end = edge[1];

            if(!nodes.containsKey(start))
            {
                nodes.put(start, new int[]{0,0});
            }
            if(!nodes.containsKey(end))
            {
                nodes.put(end, new int[]{0,0});
            }

            nodes.get(start)[0]++;
            nodes.get(end)[1]++;
        }
        for(int key:nodes.keySet())
        {
            int[] cnt = nodes.get(key);

            if(cnt[0] >= 2 && cnt[1] == 0)
            {
                extraNode = key;
            }
            else if(cnt[0] == 0 && cnt[1] > 0)
            {
                stick++;
            }
            else if(cnt[0] >= 2 && cnt[1] >= 2)
            {
                figure8++;
            }
        }
        doughnut = nodes.get(extraNode)[0] - stick - figure8;

        return new int[]{extraNode, doughnut, stick, figure8};
    }
}