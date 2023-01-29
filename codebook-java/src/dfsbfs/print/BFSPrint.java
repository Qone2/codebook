package dfsbfs.print;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFSPrint {
    /**
     * 인접행렬을 가지고 가는 길이 있는지 판단
     */
    public void getDirections(int[][] matrix, int from) {
        // TODO:
        // 큐 선언
        Queue<Integer> queue = new ArrayDeque<>();

        // 방문 여부 선언
        boolean[] visited = new boolean[matrix.length];

        // 시작점 방문처리
        visited[from] = true;
        System.out.print(from + " ");
        // 시작점 큐에 삽입
        queue.add(from);

        // while 큐가 비어있지 않으면
        while (!queue.isEmpty()) {
            // 큐에서 꺼내어
            Integer now = queue.poll();

            // 직접 갈 수 없다면 연결된 정점 중 방문하지 않은 곳을 방문처리후 큐에 넣는다.
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[now][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    System.out.print(i + " ");
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BFSPrint solution1 = new BFSPrint();
        solution1.getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0
        );
        solution1.getDirections(new int[][]
                        {
                                {0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0},
                                {0, 1, 0, 0, 0},
                                {0, 1, 1, 0, 0},
                                {1, 1, 1, 1, 0}
                        },
                1
        );
        solution1.getDirections(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0}
        }, 1);
        // 1 2 3 8 7 4 5 6
    }
}
