import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();

            while (st.hasMoreTokens())
            {
                arr.add(Integer.parseInt(st.nextToken()));
            }

            arr.sort(Comparator.comparingInt(o->o));

            int max = Integer.MIN_VALUE;
            for(int i=0; i<arr.size()-1; i++)
            {
                int a = arr.get(i);

                for(int j=i+1; j<arr.size(); j++)
                {
                    int b = arr.get(j);

                    max = Math.max(max, gcd(a,b));
                }
            }
            sb.append(max).append('\n');
        }
        System.out.print(sb);
    }

    static int gcd(int a,int b)
    {
        while (b != 0)
        {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
