import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      char[] input = br.readLine().toCharArray();

      char start = 'A';

      int sec = 0;

      for(char c : input) {

          int right = Math.abs(start - c);
          int left = 26 - right;
          sec += Math.min(left, right);

          start = c;
      }

      System.out.print(sec);

    }
}
