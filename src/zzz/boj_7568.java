package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_7568
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Info[] arr = new Info[n];

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i] = new Info(x,y);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
        {
            int rank = 1;
            for(int j=0; j<n; j++)
            {
                if(i == j) continue;
                if(arr[i].x < arr[j].x && arr[i].y < arr[j].y) rank++;
            }
            sb.append(rank).append(" ");
        }

        System.out.print(sb);
    }

    static class Info
    {
        int x;
        int y;

        public Info(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
