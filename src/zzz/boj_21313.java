package zzz;
import java.io.*;
public class boj_21313
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        if(n%2==0)
        {
            for(int i=0; i<(n/2); i++)
            {
                sb.append("1 2 ");
            }
        }
        else
        {
            for(int i=0; i<(n/2); i++)
            {
                sb.append("1 2 ");
            }
            sb.append("3");
        }
        System.out.print(sb);
    }
}
