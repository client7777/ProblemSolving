import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static int[] increase; //증가하는 부분 수열
    static int[] decrease; //감소하는 부분 수열
    static int[] arr;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        increase = new int[n+1];
        decrease = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            increase[i] = 1;
            decrease[i] = 1;
        }

        increaseLength();
        decreaseLength();
        
        int max = 0;

        for(int i=1; i<=n; i++)
        {
            max = Math.max(max, increase[i] + decrease[i]);
        }

        System.out.print(max-1);
    }

    static void increaseLength()
    {
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<i; j++)
            {
                if(arr[i] > arr[j])
                {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }
            }
        }
    }

    static void decreaseLength()
    {
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<i; j++)
            {
                if(arr[i] < arr[j])
                {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }
            }
        }
    }

}
