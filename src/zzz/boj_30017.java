package zzz;
import java.io.*;
import java.util.StringTokenizer;

public class boj_30017
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int size = 0;

        if (a > b) // 패티가 더 많은 경우 
        {
            size = b + b + 1;
        } else  // 치즈가 더 많은 경우
        {
            size = a + a - 1;
        }

        System.out.println(size);
    }
}
