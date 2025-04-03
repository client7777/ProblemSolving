import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int Acount = 100;
        int Bcount = 100;

        for(int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(A > B)
            {
                Bcount -= A;
            }else if(A < B)
            {
                Acount -= B;
            }
        }
        System.out.println(Acount);
        System.out.println(Bcount);
    }
}