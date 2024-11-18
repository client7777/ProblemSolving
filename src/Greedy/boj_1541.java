package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//최대한 큰수를 빼야됨. 덧셈으로 이루어진 부분을 먼저 계산하게 만듦
public class boj_1541
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = Integer.MAX_VALUE;
        //주어진 문자열을 - 기호를 중심으로 분리
        StringTokenizer subtraction = new StringTokenizer(br.readLine(), "-");

        while (subtraction.hasMoreTokens())
        {
            int tmp = 0;
            //-기호를 중심으로 분리한 토큰을 다시 + 기호로 분리
            StringTokenizer addition = new StringTokenizer(subtraction.nextToken(), "+");
            while (addition.hasMoreTokens())
            {
                tmp += Integer.parseInt(addition.nextToken());
            }
            //해당 토큰이 첫번째 토큰이라면
            if(sum == Integer.MAX_VALUE)
            {
                //합을 첫번째 토큰으로 갱신
                sum = tmp;
            }
            //해당 토큰이 첫번째 토큰이 아니라면 합에서 해당 토큰을 빼줌
            else
                sum -= tmp;
        }
        System.out.print(sum);
    }
}
/*
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = Integer.MAX_VALUE;

        String[] subtraction = br.readLine().split("-");

        for(int i=0; i<subtraction.length; i++)
        {
            int tmp = 0;
            // + 는 메타문자이므로 이스케이프 처리해야됨. 백슬래쉬도 단독으로 사용 불가능하므로 이스케이프 처리해야 해서 백슬래쉬 2개
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
        // split을 이용한 풀이
*/