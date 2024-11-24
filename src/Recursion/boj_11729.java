package Recursion;
// n개의 원판을 출발지에서 목적지로 옮기려면 n-1개의 원판을 출발지에서 보조기둥으로 옮김
// 가장 큰 원판을 출발지에서 목적지로 옮김
// 보조기둥에 있는 n-1개의 원판을 목적지로 옮김
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

        sb.append((1<<n)-1).append('\n');
        hanoi(1,3,n);
        System.out.print(sb);
    }

    /*
        n = 옮길 원판의 개수
        start = 현재 원판이 위치한 출발지
        mid = 옮기기 위해 이동해야 할 장소, 보조 기둥
        to = 목적지
    */
    
    static void hanoi(int a, int b, int n)
    {
        if(n == 1)
        {
            sb.append(a + " " + b).append('\n');
            return;
        }

        hanoi(a,6-a-b,n-1); // 6-a-b -> 보조기둥의 번호를 정하기 위함
        sb.append(a + " " + b).append('\n');
        hanoi(6-a-b, b, n-1);
    }
}
/*
static void hanoi(int n, int start, int mid, int to)
    {
        if(n==1)
        {
            sb.append(start + " " + to).append('\n');
            return;
        }

        //n-1개의 원판을 a에서 b로 이동
        hanoi(n-1, start, to, mid);

        //start 지점의 n번째 원판을 목적지로 옮긴다.
        sb.append(start + " " + to).append('\n');

        //원판 n-1개를 mid에서 to로 이동
        hanoi(n-1,mid, start, to);
    }*/