import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        int[][] arr = new int[15][15]; // arr[i][k] = i층 k호에 사는 사람의 수

        //0층에는 각 호마다 i명씩 거주
        for(int i=0; i<=14; i++)
        {
            arr[0][i] = i;
        }
        
        //1층부터 모든 호를 돌면서 몇명씩 사는지 계산
        for(int i=1; i<=14; i++)
        {
            for(int j=1; j<=14; j++)
            {
                arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            int k = Integer.parseInt(br.readLine()); // 층
            int n = Integer.parseInt(br.readLine()); // 호

            sb.append(arr[k][n]).append('\n');
        }

        System.out.print(sb);
    }
}
