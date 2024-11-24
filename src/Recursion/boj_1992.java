package Recursion;

import java.io.*;
//쿼드트리
public class boj_1992
{
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        quad(0,0,n);
        System.out.print(sb);
    }
    static void quad(int x,int y, int size)
    {
        if(check(x,y,size))
        {
            sb.append(map[x][y]);
            return;
        }
        int half = size/2; // 현재 구역을 4개로 나눔
        sb.append('(');
        //왼쪽 위
        quad(x,y,half);
        //오른쪽 위
        quad(x,y+half,half);
        //왼쪽 아래
        quad(x+half,y,half);
        //오른쪽 아래
        quad(x+half, y+half, half);
        sb.append(')');
    }
    static boolean check(int x,int y,int size)
    {
        int color = map[x][y];
        for(int i=x; i<x+size; i++)
        {
            for(int j=y; j<y+size; j++)
            {
                if(map[i][j] != color) return false; // 다른 색이 존재한다면 압축 불가능
            }
        }
        return true;
    }
}
