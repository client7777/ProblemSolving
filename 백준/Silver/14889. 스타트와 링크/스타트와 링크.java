import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    static int n; // n = 짝수
    static int min = Integer.MAX_VALUE;
    static int[][] arr;
    static boolean[] used;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        used = new boolean[n];

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0; j<n; j++)
            {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divideTeam(0,0);
        System.out.print(min);
    }
    static void divideTeam(int at, int depth)
    {
        if(depth == n/2)
        {
            min = Math.min(min, score());
            return;
        }
        for(int i=at; i<n; i++)
        {
            if(!used[i])
            {
                used[i] = true;
                divideTeam(i+1, depth+1);
                used[i] = false;
            }
        }
    }
    static int score()
    {
        int start = 0;
        int link = 0;

        for(int i=0; i<n; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(used[i] && used[j])
                {
                    start += arr[i][j];
                    start += arr[j][i];
                }
                else if(!used[i] && !used[j])
                {
                    link += arr[i][j];
                    link += arr[j][i];
                }
            }
        }
        return Math.abs(start - link);
    }
}
