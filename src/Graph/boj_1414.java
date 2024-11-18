package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class boj_1414
{
    static int n;
    static int totLength;
    static ArrayList<int[]> edge = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for(int i=0; i<n; i++)
        {
            parent[i] = i;
        }
        totLength = 0;
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<str.length(); j++)
            {
                if(str.charAt(j) == '0') continue; // j번째 문자가 0인 경우는 연결 x, 간선이 없음
                int length = 0;
                if(str.charAt(j) >= 'a' && str.charAt(j) <= 'z')
                {
                    length = str.charAt(j) - 'a' + 1;
                }
                else if(str.charAt(j) >= 'A' && str.charAt(j) <= 'Z')
                {
                    length = str.charAt(j) - 'A' + 27;
                }
                totLength += length;
                edge.add(new int[]{i,j,length});
            }
        }
        Collections.sort(edge, Comparator.comparingInt(o->o[2]));
        kruskal();
    }
    static void kruskal()
    {
        int used = 0;
        int tot = 0;
        for(int[] node:edge)
        {
            if(used > n-1) break;
            int from = node[0];
            int to = node[1];
            int length = node[2];
            if(find(from) != find(to))
            {
                tot += length;
                used++;
                union(from, to);
            }
        }
        System.out.print(used == n-1 ? totLength - tot : -1);
    }
    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }
    static int find(int x)
    {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
