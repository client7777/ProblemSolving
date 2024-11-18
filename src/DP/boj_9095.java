package DP;
import java.io.*;
import java.util.StringTokenizer;
//arr[i] = i를 (1,2,3)의 합으로 나타내는 방법의 수
public class boj_9095
{
    static int[] arr = new int[20];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
       arr[1] = 1; arr[2] = 2; arr[3] = 4;
       for(int i=4; i<=10; i++)
       {
           arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
       }
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int t = Integer.parseInt(br.readLine());
       StringTokenizer st;
       while(t > 0)
       {
           st = new StringTokenizer(br.readLine());
           int n = Integer.parseInt(st.nextToken());
           sb.append(arr[n]).append("\n");
           t--;
       }
       System.out.print(sb.toString());
    }
}
