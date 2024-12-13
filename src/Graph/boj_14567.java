package Graph;

import java.io.*;
import java.util.*;

public class boj_14567
{
    static int n,m;
    static int[] inDegree, term;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        inDegree = new int[n+1];
        term = new int[n+1];
        Arrays.fill(term, 1);
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b); // a는 b의 선수과목
            inDegree[b]++;
        }
        for(int i=1; i<=n; i++)
        {
            for(int j=0; j<graph[i].size(); j++)
            {
                int node = graph[i].get(j);
                term[node] = Math.max(term[node], term[i] + 1);
            }
        }
        for(int i=1; i<=n; i++)
        {
            System.out.print(term[i] + " ");
        }
        System.out.println();
    }
}
/*
static int n,m;
    static int[] inDegree, term;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        inDegree = new int[n+1];
        term = new int[n+1];
        Arrays.fill(term, 1);
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b); // a는 b의 선수과목
            inDegree[b]++;
        }
        for(int i=1; i<=n; i++)
        {
            for(int j=0; j<graph[i].size(); j++)
            {
                int node = graph[i].get(j);
                term[node] = Math.max(term[node], term[i] + 1);
            }
        }
        for(int i=1; i<=n; i++)
        {
            System.out.print(term[i] + " ");
        }
        System.out.println();
    }
*/