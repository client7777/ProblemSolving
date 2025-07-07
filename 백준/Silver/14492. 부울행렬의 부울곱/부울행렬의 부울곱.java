import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());

      boolean[][] a = new boolean[n][n];
      boolean[][] b = new boolean[n][n];

      for(int i = 0; i < n; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j = 0; j < n; j++) {
              if(Integer.parseInt(st.nextToken()) == 1) {
                  a[i][j] = true;
              }
          }
      }

      for(int i = 0; i < n; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j = 0; j < n; j++) {
              if(Integer.parseInt(st.nextToken()) == 1) {
                  b[i][j] = true;
              }
          }
      }

      int cnt = 0;

      for(int i = 0; i < n; i++) {
          for(int j = 0; j < n; j++) {
              boolean flag = false;

              for(int k = 0; k < n; k++) {
                  flag |= (a[i][k] & b[k][j]);

                  if(flag) {
                      cnt++;
                      break;
                  }
              }
          }
      }

      System.out.print(cnt);

    }
}
