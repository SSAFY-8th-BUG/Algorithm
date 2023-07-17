import math

def solution(r1, r2):
    answer = 0
    rr1 = r1*r1
    rr2 = r2*r2
    
    for x in range (1,r2+1):
        xx=x*x
        y2 = int(math.sqrt(rr2-xx))
        if(xx>rr1):
            #for y in range(0, y2+1):
            answer+=y2+1
        else:
            y1 = math.ceil(math.sqrt(rr1-xx))
            #for y in range(y1, y2+1):
            answer+=(y2-y1+1)
    return answer*4