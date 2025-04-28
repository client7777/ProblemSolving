import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long s = Long.parseLong(br.readLine());

        long sum = 0;
        int cnt = 0;

        int idx = 1;

        while (true)
        {
            if(sum + idx > s) break;
            sum += idx;
            idx++;
            cnt++;
        }

        System.out.print(cnt);
    }
}
