from collections import deque
global graph, N

def solution(n, wires):
    global graph,N
    N=n
    answer = 999999999
    
    graph={}
    for w in wires:   #그래프 만들기
        if w[0] in graph:
            graph[w[0]].append(w[1])
        else:
            graph[w[0]]=[w[1]]
        if w[1] in graph:
            graph[w[1]].append(w[0])
        else:
            graph[w[1]]=[w[0]]
            
            
    for wire in wires: #끊을 전선 선택
        answer = min(answer,count(wire))
            
    return answer

def count(wire):
    v1,v2 = wire
    visited = [False]*(N+1)
    visited[v1]=True
    cnt=1
    que = deque([v1])
    
    while(que):
        cv = que.popleft()
        for nv in graph[cv]:
            if(cv==v1 and nv==v2): continue #끊은 전선
            if(visited[nv]): continue
            visited[nv]=True
            cnt+=1
            que.append(nv)
    return abs(N-cnt - cnt)
    
    
    