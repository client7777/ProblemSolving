import java.io.*;
// d[i] = i를 1로 만들기 위해 필요한 연산의 최솟값
public class Main
{
    static int[] d,pre;
    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       d = new int[n+1];
       pre = new int[n+1];
       d[1] = 0;
       pre[1] = 0;

       for(int i=2; i<=n; i++)
       {
           d[i] = d[i-1]+1;
           pre[i] = i-1;
           if(i%2==0 && d[i] > d[i/2] + 1)
           {
               d[i] = d[i/2] + 1;
               pre[i] = i/2;
           }
           if(i%3==0 && d[i] > d[i/3] + 1)
           {
               d[i] = d[i/3] + 1;
               pre[i] = i/3;
           }
       }
       sb.append(d[n]).append("\n");
       int cur = n;
       while(n > 0)
       {
           sb.append(cur).append(' ');
           if(cur == 1) break;
           cur = pre[cur];
       }
       System.out.print(sb);
    }
}
