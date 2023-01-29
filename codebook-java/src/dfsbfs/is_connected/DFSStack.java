package dfsbfs.is_connected;

import java.util.Stack;

public class DFSStack {
    /**
     * 인접행렬을 가지고 가는 길이 있는지 판단
     */
    public boolean getDirections(int[][] matrix, int from, int to) {
        // 이어지는 길이 있으면 바로 true 리턴
        if (matrix[from][to] == 1) {
            return true;
        }
        // 바로 안 이어지면 DFS로 순회하며 찾아보기
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

            // now를 방문처리
            visited[now] = true;

            // if now에서 to로 가는게 있으면 true 리턴
            if (matrix[now][to] == 1) {
                return true;
            }

            // 없으면 now에서 인접한 정점 중 방문한적 없는 곳을 스택에 넣는다. (역순)
            for (int i = matrix.length - 1; i >= 0; i--) {
                if (matrix[now][i] == 1 && !visited[i]) {
                    stack.push(i);
                }
            }
        }
        // 다 돌아도 없으면 false 리턴
        return false;
    }

    public static void main(String[] args) {
        DFSStack dfsStack = new DFSStack();
        System.out.println(dfsStack.getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0,
                2
        ));
        System.out.println(dfsStack.getDirections(new int[][]
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
        System.out.println(dfsStack.getDirections(new int[][]{
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
