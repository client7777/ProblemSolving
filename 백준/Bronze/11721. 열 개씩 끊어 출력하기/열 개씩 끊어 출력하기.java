import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringBuilder sb = new StringBuilder();

        char[] ch = str.toCharArray();

        for(int i=0; i<ch.length; i++)
        {
            sb.append(ch[i]);
            if((i+1) % 10 == 0) sb.append('\n');
        }

        System.out.print(sb);
    }
}
