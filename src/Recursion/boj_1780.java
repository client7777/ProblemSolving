package Recursion;
// 쿼드 트리
import java.io.*;
import java.util.StringTokenizer;

public class boj_1780
{
    static int[][] map;
    static int minus = 0, zero = 0, one = 0;
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

        StringBuilder sb = new StringBuilder();
        divide(0,0,n);
        sb.append(minus + "\n");
        sb.append(zero + "\n");
        sb.append(one + "\n");

        System.out.print(sb);

    }
    static void divide(int x,int y, int size)
    {
        if(check(x,y,size))
        {
            if(map[x][y] == -1)
            {
                minus++;
            }
            if(map[x][y] == 0)
            {
                zero++;
            }
            if(map[x][y] == 1)
            {
                one++;
            }
            return;
        }
        int newSize = size/3; // 사각형을 9등분 하기 위해서 size를 3으로 나눔

        divide(x,y,newSize);
        divide(x,y+newSize,newSize);
        divide(x,y + 2*newSize,newSize);

        divide(x+newSize,y,newSize);
        divide(x+newSize,y+newSize,newSize);
        divide(x+newSize,y+2*newSize,newSize);

        divide(x+2*newSize,y,newSize);
        divide(x+2*newSize,y+newSize,newSize);
        divide(x+2*newSize,y+2*newSize,newSize);

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
