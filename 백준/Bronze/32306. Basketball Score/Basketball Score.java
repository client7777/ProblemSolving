import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        int sumA = 0, sumB = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strA = br.readLine().split(" ");
        String[] strB = br.readLine().split(" ");

        for(int i=0; i<3; i++)
        {
           sumA += Integer.parseInt(strA[i]) * (i+1);
        }

        for(int i=0; i<3; i++)
        {
            sumB += Integer.parseInt(strB[i]) * (i+1);
        }

        if(sumA == sumB)
        {
            System.out.print(0);
        }
        else if(sumA > sumB)
        {
            System.out.print(1);
        }
        else
            System.out.print(2);
    }
}
