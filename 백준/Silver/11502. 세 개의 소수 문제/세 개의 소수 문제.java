import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main
{
    static ArrayList<Integer> primeList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        while (test_case -- > 0)
        {
            int n = Integer.parseInt(br.readLine());
            primeList = new ArrayList<>();

            prime(n);

            Collections.sort(primeList);

            findComb(n);
        }
        
        System.out.print(sb);
    }

    static void findComb(int n)
    {
        for(int i=0; i<primeList.size(); i++)
        {
            int a = primeList.get(i);
            for(int j=0; j<primeList.size(); j++)
            {
                int b = primeList.get(j);
                for (int c : primeList)
                {
                    if (a + b + c == n)
                    {
                        sb.append(a).append(" ").append(b).append(" ").append(c).append('\n');
                        return;
                    }
                }
            }
        }
        sb.append(0).append('\n');
    }

    static void prime(int n)
    {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for(int i=2; i<=Math.sqrt(n); i++)
        {
            if(isPrime[i])
            {
                for(int j=i * i; j<=n; j+=i)
                {
                    isPrime[j] = false;
                }
            }
        }

        for(int i=2; i<=n; i++)
        {
            if(isPrime[i]) primeList.add(i);
        }
    }
}
