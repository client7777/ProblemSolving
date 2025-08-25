import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int need_f = Integer.parseInt(st.nextToken());
        int need_c = Integer.parseInt(st.nextToken());
        int need_e = Integer.parseInt(st.nextToken());
        int need_b = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int cookie = 0;

        while (n -- > 0){
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if(q == 1){
                if(f >= num * need_f && c >= num * need_c && e >= num * need_e && b >= num * need_b){
                    f -= num * need_f;
                    c -= num * need_c;
                    e -= num * need_e;
                    b -= num * need_b;

                    cookie += num;

                    sb.append(cookie).append('\n');
                }
                else {
                    sb.append("Hello, siumii").append('\n');
                }
            }
            else if(q == 2){
                f += num;
                sb.append(f).append('\n');
            }
            else if(q == 3){
                c += num;
                sb.append(c).append('\n');
            }
            else if(q == 4){
                e += num;
                sb.append(e).append('\n');

            }
            else if(q == 5){
                b += num;
                sb.append(b).append('\n');
            }
        }

        System.out.print(sb.toString());
    }
}
