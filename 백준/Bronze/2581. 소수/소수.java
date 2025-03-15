import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    static int primeSum = 0;
    static ArrayList<Integer> primeList = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        checkPrime(m,n);

        if(primeList.isEmpty())
        {
            System.out.print(-1);
        }
        else
        {
            System.out.println(primeSum);
            System.out.print(primeList.get(0));
        }

    }

    static void checkPrime(int m, int n)
    {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for(int i=2; i<=Math.sqrt(n); i++)
        {
            if(isPrime[i])
            {
                for(int j=i*i; j<=n; j+=i)
                {
                    isPrime[j] = false;
                }
            }
        }
        for(int i=m; i<=n; i++)
        {
            if(isPrime[i])
            {
                primeList.add(i);
                primeSum += i;
            }
        }
    }
}
