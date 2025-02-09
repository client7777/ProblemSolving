package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_15723
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[][] visit = new boolean[26][26];

        for(int i=0; i<n; i++)
        {
            String[] str = br.readLine().split(" is ");
            int a = str[0].charAt(0) - 'a';
            int b = str[1].charAt(0) - 'a';

            visit[a][b] = true;
        }

        for(int k=0; k<26; k++)
        {
            for(int i=0; i<26; i++)
            {
                for(int j=0; j<26; j++)
                {
                    if(visit[i][k] && visit[k][j]) visit[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++)
        {
            String[] str = br.readLine().split(" is ");
            int a = str[0].charAt(0) - 'a';
            int b = str[1].charAt(0) - 'a';

            sb.append(visit[a][b] ? "T" : "F").append('\n');
        }

        System.out.print(sb);
    }
}
/*
*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[26];
        for(int i=0; i<26; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'a';
            String is = st.nextToken(); // is 버림
            int b = st.nextToken().charAt(0) - 'a';

            graph[a].add(b);
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'a';
            String is = st.nextToken();
            int b = st.nextToken().charAt(0) - 'a';

            sb.append(bfs(a,b) ? "T" : "F").append('\n');
        }

        System.out.print(sb);
    }

    static boolean bfs(int start, int end)
    {
        boolean[] visit = new boolean[26];
        Queue<Integer> q = new LinkedList<>();
        visit[start] = true;
        q.add(start);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            if(cur == end) return true;

            for(Integer next:graph[cur])
            {
                if(!visit[next])
                {
                    q.add(next);
                    visit[next] = true;
                }
            }
        }
        return false;

    }
}

*
* */