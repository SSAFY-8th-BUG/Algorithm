-- 동물 보호소에 들어온 동물 중 고양이와 개가 각각 몇 마리인지 조회하는 SQL문을 작성해주세요. 이때 고양이를 개보다 먼저 조회해주세요.
SELECT ANIMAL_TYPE, count(*) 'count'
    from animal_ins
    group by animal_type
    having animal_type = 'cat' or animal_type = 'dog'
    order by animal_type;
    
-- 동물 보호소에 들어온 동물 이름 중 두 번 이상 쓰인 이름과 해당 이름이 쓰인 횟수를 조회하는 SQL문을 작성해주세요. 이때 결과는 이름이 없는 동물은 집계에서 제외하며, 결과는 이름 순으로 조회해주세요.
SELECT NAME, COUNT(NAME) 'COUNT'
    FROM ANIMAL_INS
    GROUP BY NAME
    HAVING NAME IS NOT NULL AND COUNT(NAME) >= 2
    ORDER BY NAME;
    
-- 보호소에서는 몇 시에 입양이 가장 활발하게 일어나는지 알아보려 합니다. 09:00부터 19:59까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회하는 SQL문을 작성해주세요. 이때 결과는 시간대 순으로 정렬해야 합니다.
SELECT HOUR(DATETIME) 'HOUR', COUNT(DATETIME) 'COUNT'
    FROM ANIMAL_OUTS
    GROUP BY HOUR(DATETIME)
    HAVING HOUR >= 9 AND HOUR <= 19
    ORDER BY HOUR(DATETIME);
    
-- 보호소에서는 몇 시에 입양이 가장 활발하게 일어나는지 알아보려 합니다. 0시부터 23시까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회하는 SQL문을 작성해주세요. 이때 결과는 시간대 순으로 정렬해야 합니다.
WITH RECURSIVE TIME AS(
        SELECT 0 AS H UNION ALL SELECT H+1 FROM TIME WHERE H < 23
    )
SELECT H AS 'HOUR', COUNT(HOUR(DATETIME)) AS 'COUNT'
    FROM TIME
    LEFT JOIN ANIMAL_OUTS
    ON (H=HOUR(DATETIME))
    GROUP BY H;
    
-- 천재지변으로 인해 일부 데이터가 유실되었습니다. 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는 동물의 ID와 이름을 ID 순으로 조회하는 SQL문을 작성해주세요.
SELECT AO.ANIMAL_ID, AO.NAME
    FROM ANIMAL_INS AI RIGHT JOIN ANIMAL_OUTS AO
    USING (ANIMAL_ID)
    WHERE AI.ANIMAL_ID IS NULL;
    
-- 관리자의 실수로 일부 동물의 입양일이 잘못 입력되었습니다. 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 시작일이 빠른 순으로 조회해야합니다
SELECT AI.ANIMAL_ID, AI.NAME
    FROM ANIMAL_INS AI, ANIMAL_OUTS AO
    WHERE AI.ANIMAL_ID = AO.ANIMAL_ID
    AND AI.DATETIME > AO.DATETIME
    ORDER BY AI.DATETIME;
    
-- 아직 입양을 못 간 동물 중, 가장 오래 보호소에 있었던 동물 3마리의 이름과 보호 시작일을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 시작일 순으로 조회해야 합니다.
SELECT AI.NAME, AI.DATETIME
    FROM ANIMAL_INS AI LEFT JOIN ANIMAL_OUTS AO
    USING (ANIMAL_ID)
    WHERE AI.NAME IS NOT NULL AND AO.NAME IS NULL
    ORDER BY AI.DATETIME
    LIMIT 3;
    
-- 보호소에서 중성화 수술을 거친 동물 정보를 알아보려 합니다. 보호소에 들어올 당시에는 중성화1되지 않았지만, 보호소를 나갈 당시에는 중성화된 동물의 아이디와 생물 종, 이름을 조회하는 아이디 순으로 조회하는 SQL 문을 작성해주세요.
SELECT DISTINCT AI.ANIMAL_ID, AI.ANIMAL_TYPE, AI.NAME
    FROM ANIMAL_INS AI, ANIMAL_OUTS AO
    WHERE AI.ANIMAL_ID = AO.ANIMAL_ID
    AND ((AI.SEX_UPON_INTAKE = 'Intact Male' AND AO.SEX_UPON_OUTCOME = 'Neutered Male')
    OR (AI.SEX_UPON_INTAKE = 'Intact Female' AND AO.SEX_UPON_OUTCOME = 'Spayed Female'))
    ORDER BY AI.ANIMAL_ID;