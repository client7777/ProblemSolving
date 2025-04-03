import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++)
        {
            BigInteger num = new BigInteger("0");

            int n = Integer.parseInt(br.readLine());

            while (n -- > 0)
            {
                BigInteger val = new BigInteger(br.readLine());

                num = num.add(val);
            }

            if(num.compareTo(BigInteger.ZERO) < 0)
            {
                sb.append("-").append('\n');
            }
            else if(num.compareTo(BigInteger.ZERO) > 0)
            {
                sb.append("+").append('\n');
            }
            else
                sb.append("0").append('\n');
        }

        System.out.print(sb);
    }
}