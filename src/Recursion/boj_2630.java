package Recursion;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2630
{
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    static int white = 0, blue = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        quad(0,0,n);
        sb.append(white).append('\n');
        sb.append(blue);
        System.out.print(sb);
    }
    static void quad(int x,int y, int size)
    {
        if(check(x,y,size))
        {
            if(map[x][y] == 0)
            {
                white++;
            }
            else
                blue++;
            return;
        }

        int half = size/2;

        quad(x,y,half);
        quad(x+half,y,half);
        quad(x,y+half,half);
        quad(x+half,y+half,half);
    }
    static boolean check(int x,int y,int size)
    {
        int color = map[x][y];
        for(int i=x; i<x+size; i++)
        {
            for(int j=y; j<y+size; j++)
            {
                if(map[i][j] != color) return false;
            }
        }
        return true;
    }
}
