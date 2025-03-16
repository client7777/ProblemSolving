import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main
{
    static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[v+1][v+1];
        for(int i=1; i<=v; i++)
        {
            for(int j=1; j<=v; j++)
            {
                if(i == j) continue;
                map[i][j] = INF;
            }
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = Math.min(map[a][b], c);
            map[b][a] = Math.min(map[b][a], c);
        }

        st = new StringTokenizer(br.readLine());
        int ji = Integer.parseInt(st.nextToken());
        int sung = Integer.parseInt(st.nextToken());

        for(int k=1; k<=v; k++)
        {
            for(int i=1; i<=v; i++)
            {
                for(int j=1; j<=v; j++)
                {
                    if(map[i][j] > map[i][k] + map[k][j])
                    {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int minDist = INF;
        for(int i=1; i<=v; i++)
        {
            if(i == ji || i == sung) continue;
            minDist = Math.min(minDist, map[ji][i] + map[sung][i]);
        }

        ArrayList<Integer> candidateNode = new ArrayList<>();
        for(int i=1; i<=v; i++)
        {
            if(i == ji || i == sung) continue;
            if(map[ji][i] + map[sung][i] == minDist)
            {
                if(map[ji][i] <= map[sung][i]) candidateNode.add(i);
            }
        }

        int ans = -1;
        int dist = INF;
        for(int index:candidateNode)
        {
            if(dist > map[ji][index] || (dist == map[ji][index] && index < ans))
            {
                ans = index;
                dist = map[ji][index];
            }
        }

        System.out.print(ans);
    }
}
