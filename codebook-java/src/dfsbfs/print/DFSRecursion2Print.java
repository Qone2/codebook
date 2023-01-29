package dfsbfs.print;

public class DFSRecursion2Print {
    /**
     * 인접행렬을 가지고 가는 길이 있는지 판단
     */
    public void getDirections(int[][] matrix, int start) {
        // TODO:
        // 불변성을 위해 그래프를 복사
        int[][] tmpMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                tmpMatrix[i][j] = matrix[i][j];
            }
        }

        // 방문여부 선언
        boolean[] visited = new boolean[matrix.length];

        // 시작점부터 재귀선언
        recursion(tmpMatrix, start, visited);

        System.out.println();
    }

    private void recursion(int[][] matrix, int now, boolean[] visited) {
        // 현재 정점 방문처리
        visited[now] = true;
        System.out.print(now + " ");

        // 연결된 정점들을 보고 방문한 적이 없는 정점을 재귀호출
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[now][i] == 1 && !visited[i]) {
                recursion(matrix, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        DFSRecursion2Print dfsRecursion2 = new DFSRecursion2Print();
        dfsRecursion2.getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0);
        dfsRecursion2.getDirections(new int[][]
                        {
                                {0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0},
                                {0, 1, 0, 0, 0},
                                {0, 1, 1, 0, 0},
                                {1, 1, 1, 1, 0}
                        },
                1);
        dfsRecursion2.getDirections(new int[][]{
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
