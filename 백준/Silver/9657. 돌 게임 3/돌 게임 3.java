import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    static int[] d;
    static int[] num = {1,3,4};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        d = new int[1001];
        d[1] = 1;
        d[2] = 0;
        d[3] = 1;
        d[4] = 1;

        for(int i=5; i<=n; i++)
        {
            for(int j : num)
            {
                if(d[i - j] == 0)
                {
                    d[i] = 1;
                    break;
                }
                else
                    d[i] = 0;
            }
        }
        System.out.print(d[n] == 1 ? "SK" : "CY");
    }
}
