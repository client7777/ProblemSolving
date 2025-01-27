import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        if(n <= 100000 && n % 2024 == 0)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}