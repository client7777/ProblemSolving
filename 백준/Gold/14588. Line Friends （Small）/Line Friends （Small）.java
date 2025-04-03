import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                map[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        Node[] nodes = new Node[n+1];

        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(l,r);
        }

        for(int i=1; i<=n; i++)
        {
            for(int j=i+1; j<=n; j++)
            {
                int l1 = nodes[i].x;
                int r1 = nodes[i].y;
                int l2 = nodes[j].x;
                int r2 = nodes[j].y;

                if(check(l1,r1,l2,r2))
                {
                    map[i][j] = map[j][i] = 1;
                }
            }
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());
        for(int i=0; i<q; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(map[a][b] == Integer.MAX_VALUE / 2 ? -1 : map[a][b]).append('\n');
        }

        System.out.print(sb);
    }

    static boolean check(int l1, int r1, int l2, int r2)
    {
        if((l1 >= l2 && l1 <= r2) || (r1 >= l2 && r1 <= r2) || (l2 >= l1 && l2 <= r1))
            return true;

        return false;
    }

    static class Node
    {
        int x;
        int y;

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
