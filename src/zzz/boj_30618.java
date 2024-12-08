package zzz;
// 값이 큰 숫자를 가운데에 배치해서 등장 횟수를 높인다.
import java.io.*;
import java.util.ArrayDeque;

public class boj_30618
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for(int i=n; i>=1; i--)
        {
            if(i%2==1)
            {
                dq.addFirst(i);
            }
            else
                dq.addLast(i);
        }

        for(int val:dq)
        {
            sb.append(val).append(" ");
        }
        System.out.print(sb);
    }
}
