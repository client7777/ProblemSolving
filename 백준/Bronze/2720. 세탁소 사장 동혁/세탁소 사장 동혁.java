import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        int[] coin = {25,10,5,1};
        StringBuilder sb = new StringBuilder();

        while (test_case -- > 0)
        {
            int money = Integer.parseInt(br.readLine());

            for(int coins : coin)
            {
                sb.append(money / coins).append(" ");
                money %= coins;
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}
