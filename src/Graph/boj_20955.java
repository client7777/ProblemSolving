package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_20955
{
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }

        int cnt = 0;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            //사이클이 발생한 경우 시냅스 끊는 연산 발생
            if(union(u,v))
            {
                cnt++;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i=1; i<=n; i++)
        {
            set.add(find(i));
        }

        //모든 집합을 연결
        cnt += set.size() -1;
        
        System.out.print(cnt);
    }

    static int find(int x)
    {
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return true;

        parent[rootY] = rootX;

        return false;
    }
}
