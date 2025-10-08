import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken()); // 10진수
        int n = Integer.parseInt(st.nextToken()); // 변환할 진법

        // 10진수 m → n진수 문자열 변환
        String result = Integer.toString(m, n).toUpperCase();

        System.out.println(result);
    }
}