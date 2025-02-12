//테이블 정의, 점화식 찾기, 초기값 설정
//가장 긴 증가하는 부분 수열
import java.io.*;
import java.util.StringTokenizer;
public class Main
{
    //d[i] = arr[i]를 마지막 원소로 가지는 가장 긴 증가하는 부분 수열의 길이
    static int[] arr, d;
    static int ans = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        d = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i <= n; i++)
        {
            d[i] = 1;
            //각 원소는 최소 자기 자신만을 포함하는 길이 1의 부분 수열을 가짐
        }
        for(int i = 1; i <= n; i++)
        {
            for(int j=1; j<i; j++)
            {
                // 증가하는 수열 조건. 참인 경우에만 arr[j]의 마지막 원소에 arr[i]를 추가하여 새로운 부분 수열을 만들 수 있다.
                if(arr[j] < arr[i])
                {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
            ans = Math.max(ans, d[i]);
        }
        System.out.print(ans);
    }
}
