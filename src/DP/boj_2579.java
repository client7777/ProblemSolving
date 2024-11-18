package DP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 계단 오르기
// d[i][j] = 현재까지 j개의 계단을 연속해서 밟고 i번째 계단까지 올라섰을 때 점수 합의 최댓값,
// 단 i번째 계단은 반드시 밟아야 함
public class boj_2579
{
    static int[][] d;
    static int[] s;

    public static void main(String[] args)throws IOException
    {
        //d[k][1] = max(d[k-2][1], d[k-2][2]) + s[k]
        //d[k][2] = d[k-1][1] + s[k]  k-1번째의 계단을 밟을 당시에 1개의 계단을 연속해서 밟은 상태여야 함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 계단의 수
        d = new int[301][3];
        s = new int[n+1];
        for(int i=1;i<=n;i++)
        {
            s[i] = Integer.parseInt(br.readLine());
        }
        if(n==1)
        {
            System.out.println(s[1]); return;
        }
        d[1][1] = s[1];
        d[1][2] = 0;
        if(n >= 2)
        {
            d[2][1] = s[2];
            d[2][2] = s[1] + s[2];
        }
        for(int i=3; i<=n; i++)
        {
            d[i][1] = Math.max(d[i-2][2], d[i-2][1]) + s[i];
            d[i][2] = d[i-1][1] + s[i];
        }
        System.out.print(Math.max(d[n][1], d[n][2]));
    }
}
//n이 1,2일 때, 별도로 처리해주는 이유는 동적 프로그래밍(DP)에서 사용하는 초기화와 점화식이 문제의 입력 크기에 따라 정상적으로 동작하지 않을 수 있기 때문
//예를 들어 n이 1일 때 d[2][1], d[2][2] 같은 초기화는 필요하지 않고 배열의 인덱스 2에 접근하는 것 자체가 잘못됨
//위와 같은 경우 ArrayIndexOutOfBoundsException이 발생
//n == 2일 때, d[3][1]이나 d[3][2]는 존재하지 않으므로, 코드가 잘못된 인덱스를 참조하게 되어 오류를 일으킵니다.
//따라서 입력 크기 n이 작을 때, 해당 크기에 맞는 초기값을 적절히 설정하고, 루프나 점화식 계산이 필요 없는 경우 바로 결과를 반환하도록 합니다.
//이렇게 하면 코드가 더 안전하게 실행되고, 입력의 크기에 따라 올바른 결과를 얻을 수 있습니다.