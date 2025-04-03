import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] arr = new int[4+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= 4; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        System.out.print(apple(x, arr));
    }
    static int apple(int x, int[] arr)
    {
        for(int i=1; i<=4; i++)
        {
            if(x == arr[i])
            {
                return i;
            }
        }
        return 0;
    }
}
