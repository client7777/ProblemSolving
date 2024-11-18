package zzz;
import java.util.*;
import java.io.*;
public class boj_1547
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int ball = 1; // 공의 현재 위치
        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            if(x == ball) // 만약 x가 공이 있는 컵이라면
            {
                ball = y; // 공의 위치는 y로 바뀜
            }
            else if(y == ball) // 만약 y가 공이 있는 컵이라면
            {
                ball = x; // 공의 위치는 x로 바뀜
            }
        }
        System.out.print(ball);

    }
}
