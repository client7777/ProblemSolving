import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        int setA = 0;
        int setB = 0;
        int setC = 0;

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            setA += Integer.parseInt(st.nextToken());
            setB += Integer.parseInt(st.nextToken());
            setC += Integer.parseInt(st.nextToken());

            int min = Math.min(setA, Math.min(setB, setC));

            if(min < 30) sb.append("NO").append('\n');
            else
            {
                sb.append(min).append('\n');
                setA -= min;
                setB -= min;
                setC -= min;
            }
        }

        System.out.print(sb);
    }
}
