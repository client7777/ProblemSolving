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

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if(a == 0)
        {
            System.out.print(c * c - b);
        }
        else if(b == 0)
        {
            System.out.print(c * c - a);
        }
        else if(c == 0)
        {
            System.out.print((int)Math.sqrt(a + b));
        }
    }
}
