import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        long sum = 0;
        while (test_case -- > 0)
        {
            long num = Long.parseLong(br.readLine());

            long val = num / 10;
            long val2 = num % 10;

            sum += (long)Math.pow(val, val2);
        }

        System.out.print(sum);
     }
}