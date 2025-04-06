import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        int[] a = new int[10];
        int[] b = new int[10];
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<10; i++)
        {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<10; i++)
        {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for(int i=0; i<10; i++)
        {
            if(a[i] > b[i]) cnt1++;

            if(b[i] > a[i]) cnt2++;
        }

        if(cnt1 > cnt2)
        {
            System.out.print("A");
        }
        else if(cnt1 == cnt2)
        {
            System.out.print("D");
        }
        else
            System.out.print("B");
    }
}