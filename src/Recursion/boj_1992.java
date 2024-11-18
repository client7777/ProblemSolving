package Recursion;
import java.io.*;
//쿼드트리
public class boj_1992
{
    static int n;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            for(int i = 0; i < n; i++)
            {
                String str = br.readLine();
                for(int j = 0; j < n; j++)
                {
                    arr[i][j] = str.charAt(j) - '0';
                }
            }
            recur(0,0,n);
            System.out.println(sb.toString());
    }
    static void recur(int x,int y, int size)
    {
        if(check(x,y,size))
        {
            sb.append(arr[x][y]);
            return;
        }
        int half = size / 2;
        sb.append('(');
        recur(x,y,half); // 2사분면
        recur(x,y+half,half); // 1사분면
        recur(x+half,y,half); // 3사분면
        recur(x+half,y+half,half); // 4사분면
        sb.append(')');
    }
    public static boolean check(int x, int y, int size)
    {
        int val = arr[x][y];
        for(int i=x; i<x+size; i++)
        {
            for(int j=y; j<y+size; j++)
            {
                if(val != arr[i][j]) return false;
            }
        }
        return true;
    }
}
