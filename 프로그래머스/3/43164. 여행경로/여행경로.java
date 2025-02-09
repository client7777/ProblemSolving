import java.util.*;

class Solution 
{
    static int length;
    static boolean[] visit;
    static ArrayList<String> path = new ArrayList<>();
    public String[] solution(String[][] tickets)
    {
        length = tickets.length;
        visit = new boolean[length];
        
        dfs("ICN", "ICN", tickets, 0);
        path.sort(Comparator.naturalOrder());
        
        return path.get(0).split(" ");
    }

    static void dfs(String start, String route, String[][] tickets, int depth)
    {
        if(depth == length)
        {
            path.add(route);
            return;
        }

        for(int i=0; i<length; i++)
        {
            if(start.equals(tickets[i][0]) && !visit[i])
            {
                visit[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, depth + 1);
                visit[i] = false;
            }
        }
    }
}