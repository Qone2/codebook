1초당 1억번 연산

## 스캐너로 파일 읽기
```Scanner scanner = new Scanner(new File("input.txt"));```

## 스캐너로 키보드 입력 받기
```Scanner scanner = new Scanner(System.in);```

## something -> String
```String.valueOf(e);```

## 배열길이 벗어나거나 음수인 인덱스 처리
```(length + (index) % length) % length```


## DFS
- 스택
  1. 탐색 시작 노드를 스택에 삽입한디.
  2. 스택의 최상단 에서 꺼내어 방문여부 확인하고 방문한적 없으면 방문처리 후 해당 노드의 인접 노드중 방문하지 않은 노드가 있으면
   그 인접 노드를을 스택에 넣는다. 방문한적 있으면 그 최상단 노드는 그냥 폐기한다.
  3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.
- 재귀
  1. 호출된 노드를 방문처리 한다.
  2. 방문처리 한 노드의 인접 노드중 방문하지 않은 노드에 대하여 재귀호출한다.
  3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.

## BFS
1. 탐색 시작 노드를 방문처리 후 큐에 삽입한다.
2. 큐에서 노드를 꺼내 해당 노드의 인접노드 중에서 방문하지 않은 노드를 모두 방문처리 후 큐에 삽입한다.
3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.


## 다익스트라
1. 출발 노드를 설정한다.
2. 최단 거리 테이블을 초기화 한다.
3. 방문하지 않은 노드 중에서 최단 거리가 가장 짧은 노드를 선택한다.
4. 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블을 갱신한다.
5. 위 과정에서 3과 4번을 반복한다.
```java
class Node implements Comparable<Node>{
    private int index;
    private int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}
public class Solution2_2 {
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static int n;
    public static int[] distance;
    public static boolean[] visited;

    public static void dijkstra(int start) {
        // 시작 노드로 가기위한 최단 경로는 0으로 설정하여, 큐에 삽입
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(start, 0));
        distance[start] = 0;
        // 큐가 비어있지 않다면
        while (priorityQueue.isEmpty() == false) {
            // 가장 최단 거리가 짧은 노드에 대한정보 꺼내기
            int now = priorityQueue.poll().getIndex();
            // 현재 노드가 방문한 노드라면 무시
            if (visited[now] == true) {
                continue;
            }
            visited[now] = true;
            // 현재 노드와 연결된 다른 인접한 노드들을 확인
            for (Node node : graph.get(now)) {
                int cost = distance[now] + node.getDistance();
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < distance[node.getIndex()]) {
                    distance[node.getIndex()] = cost;
                    priorityQueue.add(new Node(node.getIndex(), cost));
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        n = scanner.nextInt();
        int m = scanner.nextInt();
        int start = scanner.nextInt();
        distance = new int[n + 1];
        Arrays.fill(distance, 1234567890);
        visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int distance = scanner.nextInt();
            graph.get(v1).add(new Node(v2, distance));
        }

        dijkstra(start);

        for (int i = 1; i < n + 1; i++) {
            if (distance[i] == 1234567890) {
                System.out.println("Infinity");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}
```

## 플로이드 워셜
점화식 : D[a][b] = min(D[a][b], D[a][k] + D[k][b])
```java
public class Solution {
    public static final int INF = 987654321;
    public static int n, m;
    public static int[][] graph;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        // 노드의 개수
        n = scanner.nextInt();
        // 간선의 개수
        m = scanner.nextInt();
        graph = new int[n + 1][n + 1];
        // 그래프 무한으로 초기화
        for (int[] g : graph) {
            Arrays.fill(g, INF);
        }
        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }
        // 각 간선에 대한 정보를 입력받아, 그 값으로 초기화
        for (int i = 0; i < m; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int dist = scanner.nextInt();
            graph[v1][v2] = dist;
        }
        // 점화식에 따라 플로이드 워셜 알고리즘을 수행
        for (int k = 1; k < n + 1; k++) {
            for (int a = 1; a < n + 1; a++) {
                for (int b = 1; b < n + 1; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }
        // 수행된 결과를 출력
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("INF" + " ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
```

## 서로소 집합(디스조인트셋)
```java
public class Solution {
    public static int[] parent;

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    // 두 원소가 속한 집합을 합치기
    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        // 노드의 개수와 간선(union 연산)의 개수 입력받기
        int v = scanner.nextInt();
        int e = scanner.nextInt();
        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        parent = new int[v + 1];
        for (int i = 1; i < v + 1; i++) {
            parent[i] = i;
        }
        // union 연산을 각각 수행
        for (int i = 0; i < e; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            unionParent(v1, v2);
        }
        // 각 원소가 속한 집합 출력
        System.out.print("각 원소가 속한 집합 : ");
        for (int i = 1; i < v + 1; i++) {
            System.out.print(findParent(i) + " ");
        }
        System.out.println();
        // 부모 테이블 내용 출력
        System.out.print("부모 테이블 내용 : ");
        for (int i = 1; i < v + 1; i++) {
            System.out.print(parent[i] + " ");
        }
    }
}
```
