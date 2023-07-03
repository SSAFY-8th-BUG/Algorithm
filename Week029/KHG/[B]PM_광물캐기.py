
global gokgengs, minerals, size, goks, answer
def solution(picks, minerals2):
    global gokgengs, minerals, size, goks, answer
    answer = 9999999
    gokgengs = picks[:]
    minerals = minerals2
    size = len(minerals)
    goks = ['d','i','s']
    
    rec(0, '', 0, 0)
    return answer

def rec(idx, gok, left, tired): #재뤼함수로 dfs
    global gokgengs, answer
    if(tired>=answer): return
    if(idx == size):
        answer = tired
        return
    
    mineral = minerals[idx]
    if( left==0 ): #새곡갱이 선택할 차례
        if(sum(gokgengs)==0): #쓸곡갱이 없음
            answer=tired
            return
        for i in range(3): #곡갱이 하나 선택
            if(gokgengs[i]==0): continue
            gokgengs[i]-=1
            rec(idx+1, goks[i], 4, tired + getTired(goks[i], mineral))
            gokgengs[i]+=1
    else: #이전에 선택한 곡갱이 사용
        rec(idx+1, gok, left-1, tired + getTired(gok, mineral))
    
    
def getTired(gok, mineral):
    if(gok == 'd'): return 1
    if(gok =='i'):
        if mineral[0] =='d': return 5
        else: return 1
    if(gok == 's'):
        if mineral[0] == 'd': return 25
        elif mineral[0] =='i': return 5
        else: return 1
    return -1
    
    