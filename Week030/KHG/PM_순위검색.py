from bisect import bisect_left

global queryList, num, table
def solution(infos, querys):
    answer = [0 for i in range(len(querys))]
    table={}
    for info in infos:
        infoLst = info.split(" ")

        db = table
        for i in range(4):
            data = infoLst[i]
            if(i==3):
                if data not in db:
                    db[data] = [int(infoLst[4])]
                else:
                    db[data].append(int(infoLst[4]))
            else:
                if data not in db:
                    db[data] = {}
                db = db[data]
                
    tableSort(0,table) #정렬
    #print(table)
    
    
    global queryList, num
    for i,query in enumerate(querys): 
        num=0
        queryList = query.split(" and ")
        endList = (queryList.pop().split(" "))
        endList[1] = int(endList[1])
        queryList+= endList
        #print(queryList)
        getPeople(0, table)
        answer[i]=num
        
    return answer

def tableSort(idx,db): #정렬
    global table
    if(idx==4):
        db.sort()
    else:
        for key in db:
            tableSort(idx+1, db[key])
    

def getPeople(idx, db):
    global num
    if idx==4:
        i = bisect_left(db,queryList[idx]) #이분탐색
        num+= (len(db)-i)
        return
    if queryList[idx] =="-":
        for key in db:
            getPeople(idx+1, db[key])
    else:
        if queryList[idx] in db:
            getPeople(idx+1, db[queryList[idx]])    
            
    