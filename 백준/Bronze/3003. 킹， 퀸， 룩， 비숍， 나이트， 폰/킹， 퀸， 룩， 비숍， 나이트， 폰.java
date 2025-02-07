import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[6];

        for(int i=0; i<6; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<6; i++)
        {
            if(i == 0)
            {
                sb.append(1 - arr[i]).append(" ");
            }
            else if(i == 1)
            {
                sb.append(1 - arr[i]).append(" ");
            }
            else if(i == 2)
            {
                sb.append(2 - arr[i]).append(" ");
            }
            else if(i == 3)
            {
                sb.append(2 - arr[i]).append(" ");
            }
            else if(i == 4)
            {
                sb.append(2 - arr[i]).append(" ");
            }
            else sb.append(8 - arr[i]).append(" ");
        }

        System.out.print(sb);
    }
}
