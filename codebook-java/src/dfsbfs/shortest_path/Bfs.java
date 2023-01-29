package dfsbfs.shortest_path;

import java.util.ArrayDeque;
import java.util.Deque;

public class Bfs {
    public int robotPath(int[][] room, int[] src, int[] dst) {
        // TODO :
        final int N = room.length;
        final int M = room[0].length;
        // 방문여부 선언
        int[][] visited = new int[N][M];
        // 벽을 방문여부로 취급해도 될듯함
        for (int i = 0; i < N; i++) {
            System.arraycopy(room[i], 0, visited[i], 0, M);
        }
        // 큐 선언
        Deque<Node> queue = new ArrayDeque<>();
        // 시작점 방문처리 후 큐에 삽입
        visited[src[0]][src[1]] = 1;
        queue.addLast(new Node(src[0], src[1], 0));
        // while 큐가 비어있지 않으면
        while (!queue.isEmpty()) {
            // 큐에서 꺼내어
            Node now = queue.removeFirst();
            int x = now.getR();
            int y = now.getC();
            int time = now.getTime();
            // 목적지에 왔는지 확인
            if (x == dst[0] && y == dst[1]) {
                return now.getTime();
            }
            // 상하좌우에 대하여 방문하지 않았고 벽이 아닌곳에 대하여 방문처리 후 큐에 삽입 시간은 1증가
            if (!isOut(x - 1, y, N, M) && visited[x - 1][y] != 1) {
                visited[x - 1][y] = 1;
                queue.addLast(new Node(x - 1, y, time + 1));
            }
            if (!isOut(x, y + 1, N, M) && visited[x][y + 1] != 1) {
                visited[x][y + 1] = 1;
                queue.addLast(new Node(x, y + 1, time + 1));
            }
            if (!isOut(x + 1, y, N, M) && visited[x + 1][y] != 1) {
                visited[x + 1][y] = 1;
                queue.addLast(new Node(x + 1, y, time + 1));
            }
            if (!isOut(x, y - 1, N, M) && visited[x][y - 1] != 1) {
                visited[x][y - 1] = 1;
                queue.addLast(new Node(x, y - 1, time + 1));
            }
        }


        return 0;
    }

    private boolean isOut(int r, int c, int N, int M) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static class Node {
        int r;
        int c;
        int time;

        public Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        public int getTime() {
            return time;
        }
    }


    public static void main(String[] args) {
        Bfs solution = new Bfs();
        System.out.println(solution.robotPath(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 0}
        }, new int[]{4, 2}, new int[]{2, 2})); // 8
    }
}
