package Backtracking;
//암호 만들기
// 오름차순, 최소 2개의 자음과 최소 1개의 모음
// 암호는 L개의 알파벳으로 구성, C = 사용했을법한 알파벳의 수
// 최소 한개의 모음만 포함되면 상관 x ?
import java.io.*;
import java.util.*;
public class boj_1759
{
    static char[] ans; // 정답을 담을 배열
    static char[] alpha; // 사용했을 법한 알파벳
    static int l,c;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        ans = new char[l];
        alpha = new char[c];

        st = new StringTokenizer(br.readLine());
        // 데이터를 공백을 기준으로 구분해서 한줄로 읽어옴
        for(int i=0; i<c; i++)
        {
            alpha[i] = st.nextToken().charAt(0);
        }
        // 오름차순으로 정렬
        Arrays.sort(alpha);
        dfs(0,0);
        System.out.print(sb);
    }
    static void dfs(int start, int depth)
    {
        if(depth==l)
        {
            if(check(ans))
            {
                for(char ch : ans)
                {
                    sb.append(ch);
                }
                sb.append('\n');
            }
            //조합이 유효한지 검사한 뒤에 return
            //return문이 유효성검사 블록 내에 존재하면 조합이 유효한 경우에만 return을 해줘서 문제가 발생할 수 있다.
            //return을 해주는 이유는 더이상 깊이 탐색을 계속할 이유가 없기 때문이다.
            //조합이 완성되었다면 조합이 유효하든지 하지 않든지 return을 해줘야 한다.
            return;
        }
        for(int i=start; i<alpha.length; i++)
        {
            ans[depth] = alpha[i];
            dfs(i+1, depth+1);
        }
    }
    //완성된 조합에 대한 유효성을 검사할 함수
    static boolean check(char[] ans)
    {
        int mo = 0; // 모음
        int ja = 0; // 자음
        for(char ch:ans)
        {
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            {
                mo++;
            }
            else
            {
                ja++;
            }
        }
        return mo >= 1 && ja >= 2;
    }
}
