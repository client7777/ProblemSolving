package Hash;

import java.io.*;
import java.util.*;

public class boj_7662
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> tree = new TreeMap<>();
            for(int i=0; i<k; i++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String operation = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                if(operation.equals("I"))
                {
                    tree.put(value, tree.getOrDefault(value, 0)+1);
                }
                else
                {
                    if(tree.size() == 0) continue;
                    int num = value == 1 ? tree.lastKey() : tree.firstKey();
                    if(tree.put(num, tree.get(num)-1) == 1)
                        tree.remove(num);
                }
            }
            sb.append(tree.size() == 0 ? "EMPTY" : tree.lastKey() + " " + tree.firstKey()).append('\n');
        }
        System.out.print(sb);
    }
}
/*
public class boj_7662
{
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++)
        {
            int input = Integer.parseInt(br.readLine());
            Queue<Integer> min = new PriorityQueue<>();
            Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
            map = new HashMap<>();

            for (int i = 0; i < input; i++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String op = st.nextToken();

                if (op.equals("I"))
                {
                    int num = Integer.parseInt(st.nextToken());
                    max.add(num);
                    min.add(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else
                {
                    int type = Integer.parseInt(st.nextToken());
                    if (map.size() == 0) continue;  // 맵이 비어있다면 연산을 무시
                    if (type == 1) { // 최댓값 삭제
                        delete(max);
                    } else { // 최솟값 삭제
                        delete(min);
                    }
                }
            }

            if (map.size() == 0)
            {
                sb.append("EMPTY\n");
            } else {
                int res = delete(max);  // 최댓값
                sb.append(res).append(" ");
                if (map.size() > 0) res = delete(min);  // 최솟값
                sb.append(res).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static int delete(Queue<Integer> q) {
        int res = 0;
        while (true) {
            res = q.poll();
            int cnt = map.getOrDefault(res, 0);

            // 만약 이 값이 이미 삭제되었거나, 더 이상 존재하지 않는다면
            if (cnt == 0) continue;

            if (cnt == 1) {
                map.remove(res);  // 값이 1번 남아있다면 삭제
            } else {
                map.put(res, cnt - 1);  // 값이 2번 이상 남아있다면 개수만 감소
            }
            break;  // 유효한 값을 찾으면 반복을 종료
        }
        return res;
    }
}
*/