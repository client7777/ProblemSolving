import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int cntA = 0;
        int cntB = 0;

        for(int i=0; i<n; i++)
        {
            if(str.charAt(i) == 'A') cntA ++;
            else cntB++;
        }

        if(cntA == cntB) System.out.print("Tie");
        else if(cntA > cntB) System.out.print("A");
        else System.out.print("B");
     }
}