package dfsbfs.shortest_path;

public class DfsRecursion {
    public int robotPath(int[][] room, int[] src, int[] dst) {
        // TODO :
        final int N = room.length;
        final int M = room[0].length;
        // 발자취 선언
        int[][] steps = new int[N][M];
        // 벽을 발자취에 기록
        for (int i = 0; i < N; i++) {
            System.arraycopy(room[i], 0, steps[i], 0, M);
        }


        // 시간을 1부터 하여 시작점부터 출발
        // 타임을 0부터 할려면 visited를 추가로 만들어서 처음가는 곳을 steps[x][y] == 0 이 아니라 visited[x][y] == 0 으로 판별하고,
        // 방문시 visited[x][y] == 1 을 추가로 기록해야 한다
        recursion(steps, src[0], src[1], 1, dst, room);

        // 목표지점의 발자취
        return steps[dst[0]][dst[1]] -  1;
    }

    private void recursion(int[][] steps, int x, int y, int time, int[] dst, int[][] room) {
        final int N = steps.length;
        final int M = steps[0].length;
        // 바깥이거나 벽인경우 리턴
        if (isOut(x, y, N, M) || room[x][y] == 1) {
            return;
        }

        // 처음가는 곳 이거나 최단시간 갱신이 가능하면 발걸음(시간)기록
        if (steps[x][y] == 0 || steps[x][y] > time) {
            steps[x][y] = time;
        } else {
            return;
        }

        recursion(steps, x - 1, y, time + 1, dst, room);
        recursion(steps, x, y + 1, time + 1, dst, room);
        recursion(steps, x + 1, y, time + 1, dst, room);
        recursion(steps, x, y - 1, time + 1, dst, room);
    }

    private boolean isOut(int r, int c, int N, int M) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    public static void main(String[] args) {
        DfsRecursion solution = new DfsRecursion();
        System.out.println(solution.robotPath(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 0}
        }, new int[]{4, 2}, new int[]{2, 2})); // 8
    }
}
