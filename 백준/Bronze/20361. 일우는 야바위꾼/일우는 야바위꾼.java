import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 컵의 개수
        int x = Integer.parseInt(st.nextToken()); // 초기 공의 위치
        int k = Integer.parseInt(st.nextToken()); // 위치 변경 횟수

        boolean[] ball = new boolean[n+1];
        ball[x] = true;

        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(ball[a])
            {
                ball[b] = true;
                ball[a] = false;
            }
            else if(ball[b])
            {
                ball[a] = true;
                ball[b] = false;
            }
        }

        for(int i=1; i<=n; i++)
        {
            if(ball[i])
            {
                System.out.print(i);
                break;
            }
        }
        
    }
}
