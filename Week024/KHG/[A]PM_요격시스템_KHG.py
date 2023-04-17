def solution(targets):
    targets.sort(key = lambda x : (x[0],x[1]) )
    #print(targets)
    answer = count(targets)
    return answer

def count(targets):
    cnt=1
    ms,me=targets[0]
    for target in targets[1:]:
        s,e=target
        if s>=me:
            cnt+=1
            ms,me = target
            continue
        ms = max(ms,s)
        me = min(me,e)
    return cnt