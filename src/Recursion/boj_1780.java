package Recursion;
// 쿼드 트리
import java.io.*;
import java.util.StringTokenizer;

public class boj_1780
{
    static int n;
    static int[][] arr;
    static int minus = 0; // -1로 이루어진 영역
    static int zero = 0; // 0으로 이루어진 영역
    static int one = 0; // 1로 이루어진 영역
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
            {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recur(0,0,n);
        sb.append(minus).append("\n").append(zero).append("\n").append(one).append("\n");
        System.out.print(sb.toString());
    }
    static void recur(int x,int y,int size)
    {
        if(check(x, y, size))
        {
            if(arr[x][y] == -1)
            {
                minus++;
            }
            else if(arr[x][y] == 0)
            {
                zero++;
            }
            else if(arr[x][y] == 1)
            {
                one++;
            }
            return;
        }
        //사각형을 총 9개의 영역으로 나눔
        int newSize = size/3;
        recur(x,y,newSize);
        recur(x,y+ newSize,newSize);
        recur(x,y+ 2*newSize,newSize);

        recur(x+ newSize,y,newSize);
        recur(x+ newSize,y+ newSize,newSize);
        recur(x+ newSize,y+ 2*newSize,newSize);

        recur(x+ 2*newSize,y,newSize);
        recur(x+ 2*newSize,y+ newSize,newSize);
        recur(x+ 2*newSize,y+ 2*newSize,newSize);
    }
    static boolean check(int x,int y, int size)
    {
        int val = arr[x][y];
        for(int i=x; i<x+size; i++)
        {
            for(int j=y; j<y+size; j++)
            {
                if(val != arr[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
}
