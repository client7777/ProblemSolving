package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_18513
{
    static int n,k;
    static int cnt = 0;
    static long ans = 0;
    static HashSet<Integer> set = new HashSet<>();
    static Queue<Node> q = new LinkedList<>();
    static int[] d = {-1,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n  = Integer.parseInt(st.nextToken());
        k  = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int pos = Integer.parseInt(st.nextToken());
            q.add(new Node(pos,0));
            set.add(pos);
        }

        bfs();
        System.out.println(ans);
    }

    static void bfs()
    {
        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            for(int i=0; i<2; i++)
            {
                int nextNode = curNode + d[i];

                if(OOB(nextNode) || set.contains(nextNode)) continue; // 범위 밖이거나 이미 방문한 곳이라면 무시

                if(cnt < k)
                {
                    ans += curDist + 1;
                    set.add(nextNode);
                    q.add(new Node(nextNode, curDist + 1));
                    cnt++;
                }
            }
        }
    }

    static boolean OOB(int node)
    {
        return node > 1_000_000_000 || node < -1_000_000_000;
    }
    static class Node
    {
        int node;
        int dist;

        public Node(int node, int dist)
        {
            this.node = node;
            this.dist = dist;
        }
    }
}
