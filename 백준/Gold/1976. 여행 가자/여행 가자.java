import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static int[] parent;
    static boolean flag = true;
    public static void main(String[] args) throws IOException
    {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         n = Integer.parseInt(br.readLine());
         m = Integer.parseInt(br.readLine());

         parent = new int[n+1];
         for(int i=1; i<=n; i++)
         {
             parent[i] = i;
         }
         StringTokenizer st;
         for(int i=1; i<=n; i++)
         {
             st = new StringTokenizer(br.readLine());
             for(int j=1; j<=n; j++)
             {
                int isCon = Integer.parseInt(st.nextToken());
                if(isCon == 1)
                {
                    union(i,j); // i,j 도시를 연결
                }
             }
         }
         st = new StringTokenizer(br.readLine());
         int[] trip = new int[m];
         for(int i=0; i<m; i++)
         {
             trip[i] = Integer.parseInt(st.nextToken());
         }
         for(int i=1; i<m; i++)
         {
             if(find(trip[0]) != find(trip[i]))
             {
                 flag = false;
                 break;
             }
         }
         System.out.print(flag ? "YES" : "NO");
    }
    static int find(int x)
    {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
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
}
