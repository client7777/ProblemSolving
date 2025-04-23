import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = {500, 100, 50, 10, 5, 1};

        int cnt = 0;

        int money = 1000 - n;
        for(int val : arr)
        {
            cnt += (money / val);
            money %= val;
        }

        System.out.print(cnt);
    }
}
