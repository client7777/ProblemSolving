package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class boj_1644
{
    static ArrayList<Integer> primeList = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        findPrime(n);

        int left = 0;
        int right = 0;
        int cnt = 0;
        int sum = 0;

        while (true)
        {
            if(sum >= n) sum -= primeList.get(left++);
            else if(right == primeList.size()) break;
            else sum += primeList.get(right++);

            if(sum == n) cnt++;
        }

        System.out.print(cnt);
    }

    //소수 리스트 구성, 에라토스테네스의 체
    static void findPrime(int n)
    {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false; // 0, 1은 소수가 아님

        for(int i=2; i<=Math.sqrt(n); i++)
        {
            if(isPrime[i])
            {
                for(int j=i * i; j<=n; j += i)
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
