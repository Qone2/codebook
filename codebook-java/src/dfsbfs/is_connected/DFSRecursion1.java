package dfsbfs.is_connected;

public class DFSRecursion1 {
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

        // 직접가는 경우가 없으면 연결된 정점을 살핀다.
        for (int i = 0; i < tmpMatrix.length; i++) {
            // 연결된 정점 중 방문한 적이 없는 정점을 방문처리 후 스택에 넣는다(재귀를 한다.)
            if (tmpMatrix[from][i] == 1) {
                tmpMatrix[from][i] = 0;
                if (getDirections(tmpMatrix, i, to)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DFSRecursion1 dfsRecursion1 = new DFSRecursion1();
        System.out.println(dfsRecursion1.getDirections(new int[][]
                        {
                                {0, 1, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                                {0, 1, 0, 0}
                        },
                0,
                2));
        // true
        System.out.println(dfsRecursion1.getDirections(new int[][]
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
        System.out.println(dfsRecursion1.getDirections(new int[][]{
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
