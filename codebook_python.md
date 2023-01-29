계산 1초당 1억번
int하나 4B

## 여러개 입력을 받아 리스트로 만들기 혹은 그냥 여러개 입력받을 때
```python
list(map(int, input().strip().split()))
```


## 파일로 입출력, 더 빠르게 입력받기
```python
import sys
sys.stdin = open("input.txt", 'r')
sys.stdout = open("output.txt", 'w')
input = sys.stdin.readline
```


## DFS
- 스택
  1. 탐색 시작 노드를 스택에 삽입한디.
  2. 스택의 최상단 에서 꺼내어 방문여부 확인하고 방문한적 없으면 방문처리 후 해당 노드의 인접 노드중 방문하지 않은 노드가 있으면
   그 인접 노드를을 스택에 넣는다. 방문한적 있으면 그 최상단 노드는 그냥 폐기한다.
     1. 종료조건이 있다면 스택에서 pop과 방문처리 후 종료조건을 확인한다.
     2. 주변노드 탐색 순서를 반대로 해야 재귀랑 순서가 같다.
  3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.
- 재귀
  1. 인자로 받은 노드를 방문처리 한다.
     1. 종료조건이 있다면 여기서 확인한다.
  2. 방문처리 한 노드의 인접 노드중 방문하지 않은 노드에 대하여 재귀호출한다.
     1. 재귀는 방문처리를 여기에서 대신 해도 무방하다. <br> 
     백트래킹의 경우 함수 호출 전후 답과 방문여부를 되돌려야 한다. <br> 
     백트래킹에서 만약 함수안에서 방문처리를 하면 방문여부배열을 깊은 복사하여 전달한다.
  3. 1, 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.

## BFS
1. 탐색 시작 노드를 방문처리 후 큐에 삽입한다.
2. 큐에서 노드를 꺼내 해당 노드의 인접노드 중에서 방문하지 않은 노드를 모두 방문처리 후 큐에 삽입한다.
   1. 종료조건이 있으면 여기서 조건을 확인한다.
   2. 백트래킹의 경우 방문여부를 깊은복사하여 체크하고 큐에 삽입한다.
3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.


## 디스조인트 셋
```python
parent = list(i for i in range(N + 1))

def find(u):
    if u == parent[u]:
        return u
    parent[u] = find(parent[u])
    return parent[u]

def merge(u, v):
    u = find(u)
    v = find(v)
    parent[u] = v
```


## 이항계수
```python
DP = list(list(0 for _ in range(N+1)) for _ in range(N+1))

for i in range(N+1):
    for j in range(N+1):
        if j == 0 or i == j:
            DP[i][j] = 1
        else:
            DP[i][j] = DP[i-1][j-1] + DP[i-1][j]
```


## dfs
```python
def dfs(graph, v, visited):
    visited[v] = True
    print(v, end='')
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)

graph = [
    [],
    [],
    [],
]

visited = [False] * 9

dfs(graph, 1, visited)
```


## bfs
```python
from collections import deque

def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True
    while queue:
        v = queue.popleft()
        print(v, end=' ')
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True

graph = [
    [],
    [],
    [],
]

visited = [False] * 9

bfs(graph, 1, visited)
```
