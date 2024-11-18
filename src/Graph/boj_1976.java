package Graph;
// 같은 집합에 속한 도시는 직접적인 경로뿐만 아니라 간접적인 경로를 통해 여행이 가능
import java.io.*;
import java.util.StringTokenizer;

public class boj_1976
{
    static int n,m;
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 전체 도시의 수
        m = Integer.parseInt(br.readLine()); // 계획을 방문한 도시의 수

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
                if(isCon == 1) union(i,j);
            }
        }
        int[] trip = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            trip[i] = Integer.parseInt(st.nextToken());
        }
        boolean flag = true;
        for(int i=1; i<m; i++)
        {
            // 계획한 여행지에서 같은 집합이 아닌 여행지가 있다면
            if(find(trip[0]) != find(trip[i]))
            {
                flag = false;
                break;
            }
        }
        System.out.print(flag ? "YES" : "NO");
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
