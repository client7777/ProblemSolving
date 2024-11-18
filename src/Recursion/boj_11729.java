package Recursion;

//함수의 정의
//원판 n개를 a번 기둥에서 b번 기둥으로 옮기는 방법을 출력하는 함수
//base condition
//n == 1일 때 a,b를 빈칸을 두고 출력
//재귀 식
//n-1개의 원판을 기둥 a에서 기둥 6-a-b로 옮긴다.
//n번 원판을 기둥 a에서 b로 옮긴다.
//n-1개의 원판을 기둥 6-a-b에서 기둥 b로 옮긴다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11729
{
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        sb.append((1<<n) - 1).append('\n');
        Hanoi(n,1,2,3);
        System.out.print(sb.toString());
    }
    //n: 원판의 개수 start:출발지 mid:옮기기 위해 이동해야 할 장소 to:목적지
    public static void Hanoi(int n, int start, int mid, int to)
    {
        if(n == 1) // 이동할 원반의 개수가 하나라면
        {
            sb.append(start + " " + to + "\n");
            return;
        }
        // A - C로 옮긴다고 가정할 때,
        // step 1: n-1개를 1에서 2로 이동
        // start지점의 n-1개의 원판을 mid 지점으로 옮긴다.
        Hanoi(n-1, start,to,mid);
        // step 2: n-1개의 원판을 옮기고 남은 n번째 원판 1개를 1에서 3으로 이동(n번째 원판을 to 지점으로 이동)
        sb.append(start + " " + to + "\n");

        Hanoi(n-1,mid,start,to);
    }
}
