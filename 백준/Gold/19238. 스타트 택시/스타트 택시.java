import java.io.*;
import java.util.*;

public class Main {
    static int n, m, fuel;
    static int taxiX, taxiY;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<int[]> start = new ArrayList<>(); // 승객 출발 좌표
    static ArrayList<int[]> end = new ArrayList<>(); // 승객 목적지 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxiX = Integer.parseInt(st.nextToken()) - 1;
        taxiY = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            start.add(new int[]{startX, startY});
            end.add(new int[]{endX, endY});
            map[startX][startY] = i + 2; // 승객의 번호를 지정
        }

        for (int i = 0; i < m; i++) {
            if (!findAndGoDestination()) {
                System.out.print(-1);
                return;
            }
        }
        System.out.print(fuel);
    }

    static boolean findAndGoDestination() {
        int[] passengerData = findClosestPassenger();
        if (passengerData == null) return false;

        int passengerIdx = passengerData[0];
        int sx = start.get(passengerIdx)[0];
        int sy = start.get(passengerIdx)[1];
        int ex = end.get(passengerIdx)[0];
        int ey = end.get(passengerIdx)[1];

        // 승객까지의 거리
        int distToPassenger = bfs(taxiX, taxiY, sx, sy);
        if (distToPassenger == -1 || distToPassenger > fuel) return false;

        // 승객 위치로 이동 및 연료 소모
        fuel -= distToPassenger;
        taxiX = sx;
        taxiY = sy;

        // 승객 목적지까지의 거리 계산
        int distToDestination = bfs(taxiX, taxiY, ex, ey);
        if (distToDestination == -1 || distToDestination > fuel) return false;

        // 목적지로 이동 및 연료 업데이트
        fuel -= distToDestination;
        fuel += distToDestination * 2;

        // 택시 좌표를 목적지로 갱신
        taxiX = ex;
        taxiY = ey;

        // 승객 태운 후 승객 좌표를 맵에서 제거
        map[sx][sy] = 0;

        return true;
    }

    static int[] findClosestPassenger() {
        boolean[][] visit = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{taxiX, taxiY, 0});
        visit[taxiX][taxiY] = true;

        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            // 우선순위: 거리, X좌표, Y좌표 순으로 승객을 선택
            if (p1[2] != p2[2]) return p1[2] - p2[2]; // 거리 순
            if (p1[0] != p2[0]) return p1[0] - p2[0]; // X좌표 순
            return p1[1] - p2[1]; // Y좌표 순
        });

        int minDist = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            // 더 이상 최소 거리의 승객을 찾을 수 없으면 종료
            if (curDist > minDist) break;

            // 승객이 있는 좌표일 경우
            if (map[curX][curY] >= 2) {
                int passengerIdx = map[curX][curY] - 2; // 승객 번호
                pq.add(new int[]{curX, curY, curDist, passengerIdx});
                minDist = curDist;
            }

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if (!OOB(nX, nY) && !visit[nX][nY] && map[nX][nY] != 1) {
                    visit[nX][nY] = true;
                    q.add(new int[]{nX, nY, curDist + 1});
                }
            }
        }

        // 승객을 찾지 못했으면 null 반환, 찾았으면 우선순위큐에서 승객 인덱스 반환
        return pq.isEmpty() ? null : new int[]{pq.poll()[3]};
    }

    static int bfs(int startX, int startY, int endX, int endY) {
        boolean[][] visit = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});
        visit[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            if (curX == endX && curY == endY) {
                return curDist;
            }
            for (int dir = 0; dir < 4; dir++) {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if (OOB(nX, nY) || visit[nX][nY] || map[nX][nY] == 1) continue;
                q.add(new int[]{nX, nY, curDist + 1});
                visit[nX][nY] = true;
            }
        }
        return -1;
    }

    static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
