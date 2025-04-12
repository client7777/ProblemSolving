import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Pos[] pos = new Pos[n];

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pos[i] = new Pos(a,b);
        }

        Arrays.sort(pos, Comparator.comparingInt(o -> o.a));

        int[] d = new int[n];
        Arrays.fill(d, 1);

        int max = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<i; j++)
            {
                if(pos[i].b > pos[j].b)
                {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }

            max = Math.max(max, d[i]);
        }

        System.out.print(n - max);
    }

    static class Pos
    {
        int a;
        int b;

        public Pos(int a, int b)
        {
            this.a = a;
            this.b = b;
        }
    }
}
