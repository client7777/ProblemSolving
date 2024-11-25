package Recursion;
// 분할과 정복
import java.io.*;
import java.util.Arrays;

public class boj_2447
{
    static char[][] star;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        star = new char[n][n];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(star[i], ' ');
        }

        printStar(0,0,n);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
        {
            for(char ans:star[i])
                sb.append(ans);
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static void printStar(int x,int y,int n)
    {
        if(n == 1)
        {
            star[x][y] = '*';
            return;
        }

        int half = n/3;

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(i == 1 && j == 1) continue;
                printStar(x + (i*half), y + (j*half), half);
            }
        }
    }
}
