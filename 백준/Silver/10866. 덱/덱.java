import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (n -- > 0){
            String[] str = br.readLine().split(" ");

            String command = str[0];

            if(command.equals("push_back")){
                String val = str[1];
                dq.addLast(Integer.valueOf(val));
            }
            else if(command.equals("push_front")){
                String val = str[1];
                dq.addFirst(Integer.valueOf(val));
            }
            else if(command.equals("front")){
                boolean flag = dq.isEmpty();

                sb.append(flag ? -1 : dq.peekFirst()).append('\n');
            }
            else if(command.equals("back")){
                boolean flag = dq.isEmpty();

                sb.append(flag ? -1 : dq.peekLast()).append('\n');
            }
            else if(command.equals("empty")){
                sb.append(dq.isEmpty() ? 1 : 0).append('\n');
            }
            else if(command.equals("size")){
                sb.append(dq.size()).append('\n');
            }
            else if(command.equals("pop_front")){
                boolean flag = dq.isEmpty();

                sb.append(flag ? -1 : dq.pollFirst()).append('\n');
            }
            else if(command.equals("pop_back")){
                boolean flag = dq.isEmpty();

                sb.append(flag ? -1 : dq.pollLast()).append('\n');
            }
        }

        System.out.print(sb);
    }
}
