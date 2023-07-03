from collections import deque


global plans, times, answer
def solution(plans2):
    global plans
    plans = plans2
    init()
    
    doHomework()    
    
    return answer

def init():
    global times, answer
    times={}
    answer = []
    plans.sort(key=lambda x : (int(x[1].split(":")[0]), int(x[1].split(":")[1])))
    
    for plan in plans:
        timeLst = plan[1].split(":")
        times[plan[0]] = int(timeLst[0])*60 + int(timeLst[1])

def doHomework():
    prev = times[plans[0][0]]
    que = deque([])
    que.append([plans[0][0], int(plans[0][2])]) #[이름, 남은시간]
    for i in range(1,len(plans)):
        plan = plans[i]
        now = times[plan[0]]
        gap = now - prev #과제할 수 있는 시간
        while(gap>0 and que): #미뤄진 과제처리
            work = que.pop()
            if (work[1] <= gap):
                gap -= work[1]
                answer.append(work[0])
            else:
                que.append([work[0], work[1]-gap])
                gap=0
                break
        prev = now
        que.append([plan[0], int(plan[2])])
    while que: #나머지 처리
        work = que.pop()
        answer.append(work[0])
            
    
    
    
    