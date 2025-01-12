import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] computers) 
    {
        //n = 컴퓨터의 개수, computers = 인접행렬
        int answer = 0;
        boolean[] visit = new boolean[n];
        for(int i=0; i<n; i++)
        {
            if(!visit[i])
            {
                answer++;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visit[i] = true;
                while(!q.isEmpty())
                {
                    int cur = q.poll();
                    for(int k=0; k<computers.length; k++)
                    {
                        if(computers[cur][k] == 1 && !visit[k])
                        {
                            q.add(k);
                            visit[k] = true;
                        }
                    }
                }
            }
        }
        return answer;
    }
}