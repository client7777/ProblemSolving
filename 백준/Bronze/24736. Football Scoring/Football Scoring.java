import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        int[] arr = {6,3,2,1,2};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int sum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int val : arr)
        {
            sum += val * Integer.parseInt(st.nextToken());
        }

        sb.append(sum).append(" ");

        sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int val : arr)
        {
            sum += val * Integer.parseInt(st.nextToken());
        }

        sb.append(sum);

        System.out.print(sb);
    }
}