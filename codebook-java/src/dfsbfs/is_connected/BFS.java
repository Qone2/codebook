package dfsbfs.is_connected;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {
    /**
     * 인접행렬을 가지고 가는 길이 있는지 판단
     */
    public boolean getDirections(int[][] matrix, int from, int to) {
        // TODO:
        // 직접 가는 길이 있다면 true 리턴
        if (matrix[from][to] == 1) {
            return true;
        }

        // 큐 선언
        Queue<Integer> queue = new ArrayDeque<>();

        // 방문 여부 선언
        boolean[] visited = new boolean[matrix.length];

        // 시작점 방문처리
        visited[from] = true;
        // 시작점 큐에 삽입
        queue.add(from);

        // while 큐가 비어있지 않으면
        while (!queue.isEmpty()) {
            // 큐에서 꺼내어
            Integer now = queue.poll();
            // 그곳에서 직접 갈 수 있다면 true 리턴
            if (matrix[now][to] == 1) {
                return true;
            }
            // 직접 갈 수 없다면 연결된 정점 중 방문하지 않은 곳을 방문처리후 큐에 넣는다.
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[now][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        // 큐가 비었다면 false 리턴
        return false;
    }

    public static void main(String[] args) {
        BFS solution1 = new BFS();
        System.out.println(solution1.getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0,
                2
        ));
        // true
        System.out.println(solution1.getDirections(new int[][]
                        {
                                {0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0},
                                {0, 1, 0, 0, 0},
                                {0, 1, 1, 0, 0},
                                {1, 1, 1, 1, 0}
                        },
                1,
                4
        ));
        // false
        System.out.println(solution1.getDirections(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0}
        }, 5, 6));
        // true
    }
}
