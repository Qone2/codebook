package dfsbfs.is_connected;

public class DFSRecursion2 {
    /**
     * 인접행렬을 가지고 가는 길이 있는지 판단
     */
    public boolean getDirections(int[][] matrix, int from, int to) {
        // TODO:
        // from 에서 to로 가는게 바로 있으면 바로 true 리턴
        if (matrix[from][to] == 1) {
            return true;
        }

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
        return recursion(tmpMatrix, from, to, visited);
    }

    private boolean recursion(int[][] matrix, int from, int to, boolean[] visited) {
        // 현재 정점 방문처리
        visited[from] = true;
        // 직접 가는게 있으면 true 리턴
        if (matrix[from][to] == 1) {
            return true;
        }
        // 연결된 정점들을 보고 방문한 적이 없는 정점을 재귀호출
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[from][i] == 1 && !visited[i]) {
                if (recursion(matrix, i, to, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DFSRecursion2 dfsRecursion2 = new DFSRecursion2();
        System.out.println(dfsRecursion2.getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0,
                2));
        // true
        System.out.println(dfsRecursion2.getDirections(new int[][]
                        {
                                {0, 1, 0, 0, 0},
                                {0, 0, 0, 1, 0},
                                {0, 1, 0, 0, 0},
                                {0, 1, 1, 0, 0},
                                {1, 1, 1, 1, 0}
                        },
                1,
                4));
        // false
        System.out.println(dfsRecursion2.getDirections(new int[][]{
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
