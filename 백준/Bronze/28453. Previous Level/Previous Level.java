import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int val:arr)
        {
            if(val >= 300) sb.append(1).append(" ");
            else if(val >= 275) sb.append(2).append(" ");
            else if(val >= 250) sb.append(3).append(" ");
            else sb.append(4).append(" ");
        }
        
        System.out.print(sb);
    }
}
