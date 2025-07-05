import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      List<String> list = new ArrayList<String>();
      for(int i = 0; i < 3; i++){
          list.add(br.readLine());
      }

      boolean startWith_l = false;
      boolean startWith_k = false;
      boolean startWith_p = false;

      for(String str : list){

          if(str.startsWith("l")){
              startWith_l = true;
          }
          else if(str.startsWith("k")){
              startWith_k = true;
          }
          else if(str.startsWith("p")){
              startWith_p = true;
          }
      }

      if(startWith_l && startWith_k && startWith_p){
          System.out.print("GLOBAL");
      }
      else{
          System.out.print("PONIX");
      }

    }
}
