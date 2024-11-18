package Recursion;
import java.io.*;
import java.util.StringTokenizer;
// 쿼드트리
public class boj_2630
{
    static int[][] arr;
    static int n;
    static int count_white = 0;
    static int count_blue = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++)
            {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recur(0,0,n);
        sb.append(count_white).append("\n").append(count_blue).append("\n");
        System.out.print(sb.toString());
    }
    static void recur(int x, int y, int size)
    {
        if(colorCheck(x,y,size))
        {
            if(arr[x][y] == 0 )
            {
                count_white++;
            }
            else
            {
                count_blue++;
            }
            return;
        }
        int newSize = size/2;
        recur(x,y,newSize); // 2사분면
        recur(x,y+newSize,newSize); // 1사분면
        recur(x+newSize,y,newSize); // 3사분면
        recur(x+newSize,y+newSize,newSize); // 4사분면
    }
    static boolean colorCheck(int x, int y, int size)
    {
        int color = arr[x][y];

        for(int i=x; i<x + size; i++)
        {
            for(int j=y; j<y + size; j++)
            {
                if(arr[i][j] != color)
                    return false;
            }
        }
        return true;
    }
}
