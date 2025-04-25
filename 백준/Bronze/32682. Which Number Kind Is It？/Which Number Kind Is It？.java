import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        boolean[] isSquare = new boolean[1_000_001];
        
        for (int i = 0; i * i <= 1_000_000; i++)
        {
            isSquare[i * i] = true;
        }

        StringBuilder sb = new StringBuilder();

        while (test_case -- > 0)
        {
            int num = Integer.parseInt(br.readLine());
            if(isSquare[num] && num % 2 == 1)
            {
                sb.append("OS").append('\n');
            }
            else if(num % 2 == 1)
            {
                sb.append("O").append('\n');
            }
            else if(isSquare[num])
            {
                sb.append("S").append('\n');
            }
            else
                sb.append("EMPTY").append('\n');
        }

        System.out.print(sb);
    }
}