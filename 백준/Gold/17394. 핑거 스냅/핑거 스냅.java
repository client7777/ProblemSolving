import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static final int LIMIT = 1_000_000;
    static HashSet<Integer> primeNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            primeNum = new HashSet<>();

            //a이상 b이하의 소수집합을 구함
            checkPrime(a,b);

            if(primeNum.isEmpty()){
                sb.append(-1).append('\n');
            }
            else {
                sb.append(bfs(n)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static int bfs(int n){
        boolean[] visit = new boolean[LIMIT + 1];
        Queue<Node> q = new LinkedList<>();

        visit[n] = true;
        q.add(new Node(n, 0));

        while (!q.isEmpty()){
            Node cur = q.poll();
            int curNode = cur.node;
            int curCnt = cur.cnt;

            if(primeNum.contains(curNode)){
                return curCnt;
            }

            for(int i = 0; i < 4; i++){
                int nextNode;
                switch (i){
                    case 0:
                        nextNode = curNode / 2;
                        if(!visit[nextNode]){
                            visit[nextNode] = true;
                            q.add(new Node(nextNode, curCnt + 1));
                        }
                        break;
                    case 1:
                        nextNode = curNode / 3;
                        if(!visit[nextNode]){
                            visit[nextNode] = true;
                            q.add(new Node(nextNode, curCnt + 1));
                        }
                        break;
                    case 2:
                        nextNode = curNode + 1;
                        if(nextNode > LIMIT){
                            continue;
                        }

                        if(!visit[nextNode]){
                            visit[nextNode] = true;
                            q.add(new Node(nextNode, curCnt + 1));
                        }
                        break;
                    case 3:
                        nextNode = curNode - 1;
                        if(nextNode < 0){
                            continue;
                        }
                        
                        if(!visit[nextNode]){
                            visit[nextNode] = true;
                            q.add(new Node(nextNode, curCnt + 1));
                        }
                        break;
                }
            }
        }

        return -1;
    }

    static void checkPrime(int a, int b){

        boolean[] isPrime = new boolean[b+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i <= Math.sqrt(b); i++){
            if(isPrime[i]){
                for(int j = i * i; j <= b; j += i){
                    isPrime[j] = false;
                }
            }
        }

        for(int i = a; i <= b; i++){
            if(isPrime[i]){
                primeNum.add(i);
            }
        }
    }

    static class Node{
        int node;
        int cnt;

        public Node(int node, int cnt) {
            this.node = node;
            this.cnt = cnt;
        }
    }
}