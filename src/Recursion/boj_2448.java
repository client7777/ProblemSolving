package Recursion;
// n = 3 * 2^k 꼴로 주어짐
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2448
{
    static char[][] star;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        star = new char[n][2*n-1];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(star[i],' ');
        }
        StringBuilder sb = new StringBuilder();

        printStart(0,n-1,n); // 삼각형의 맨 위, 정가운데에서 시작

        for(int i=0; i<n; i++)
        {
            for(char an:star[i])
            {
                sb.append(an);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static void printStart(int x,int y,int size)
    {
        if(size == 3)
        {
            star[x][y] = '*';
            star[x+1][y-1] = star[x+1][y+1] = '*';
            for(int i=y-2; i<=y+2; i++)
            {
                star[x+2][i] = '*';
            }
            return;
        }

        int half = size/2;

        printStart(x,y,half); // 상단 삼각형
        printStart(x+half, y-half,half); // 왼쪽 하단 삼각형
        printStart(x+half, y+half, half); // 오른쪽 하단 삼각형
    }
}
