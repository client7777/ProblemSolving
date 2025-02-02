import java.io.*;

public class Main
{
    static int[][] dist = new int[102][102];
    public static void main(String[] args) throws IOException
    {   
        int n = 100;
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                dist[i][j] = 10000;
                if(i == n || j == n) dist[i][j] = 4999;
                if(i == j) dist[i][j] = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(n).append('\n');
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                sb.append(dist[i][j]).append(" ");
                if(j == n) sb.append('\n');
            }
        }
        System.out.print(sb);
    }
}