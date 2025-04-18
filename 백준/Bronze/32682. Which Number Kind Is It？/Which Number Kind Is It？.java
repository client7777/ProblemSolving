import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        boolean[] isSquare = new boolean[1_000_001];

        for (int i = 0; i * i <= 1_000_000; i++)
        {
            isSquare[i * i] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++)
        {
            int N = Integer.parseInt(br.readLine());
            if (isSquare[N] && N % 2 == 1)
            {
                sb.append("OS\n");
            } else if (isSquare[N])
            {
                sb.append("S\n");
            } else if (N % 2 == 1)
            {
                sb.append("O\n");
            }
            else
            {
                sb.append("EMPTY\n");
            }
        }

        System.out.print(sb);
    }
}