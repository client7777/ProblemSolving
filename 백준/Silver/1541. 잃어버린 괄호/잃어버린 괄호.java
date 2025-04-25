import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//최대한 큰수를 빼야됨. 덧셈으로 이루어진 부분을 먼저 계산하게 만듦
public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = Integer.MAX_VALUE;

        String[] subtraction = br.readLine().split("-");

        for(int i=0; i<subtraction.length; i++)
        {
            int tmp = 0;
            String[] addition = subtraction[i].split("\\+");

            for(int j=0; j<addition.length; j++)
            {
                tmp += Integer.parseInt(addition[j]);
            }
            if(sum == Integer.MAX_VALUE)
            {
                sum = tmp;
            }
            else
                sum -= tmp;
        }
        System.out.print(sum);
    }
}
