package dfsbfs.print;

import java.util.Stack;

public class DFSStackPrint {
    /**
     * 인접행렬을 가지고 가는 길이 있는지 판단
     */
    public void getDirections(int[][] matrix, int from) {
        // 스택을 하나 선언한다.
        Stack<Integer> stack = new Stack<>();
        // 방문여부를 배열로 선언한다.
        boolean[] visited = new boolean[matrix.length];

        // 시작점을 스택에 삽입
        stack.push(from);

        // while (스택이 안비었으면) {
        while (!stack.isEmpty()) {
            // 스택에서 하나 꺼내어 int now를 선언하고 할당한다.
            int now = stack.pop();
            // 이미 방문했으면 다음으로 넘김
            if (visited[now]) {
                continue;
            }

            // now를 방문처리
            visited[now] = true;
            System.out.print(now + " ");

            // now에서 인접한 정점 중 방문한적 없는 곳을 스택에 넣는다. (역순)
            for (int i = matrix.length - 1; i >= 0; i--) {
                if (matrix[now][i] == 1 && !visited[i]) {
                    stack.push(i);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DFSStackPrint dfsStack = new DFSStackPrint();
        dfsStack.getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0
        );
        dfsStack.getDirections(new int[][]
                        {
                                {0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0},
                                {0, 1, 0, 0, 0},
                                {0, 1, 1, 0, 0},
                                {1, 1, 1, 1, 0}
                        },
                1
        );
        dfsStack.getDirections(new int[][]{
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
        // 1 2 7 6 8 3 4 5
    }
}
