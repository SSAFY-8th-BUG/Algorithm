-- 1. 없어진 기록 찾기
SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_INS I RIGHT JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.ANIMAL_ID IS NULL
ORDER BY O.ANIMAL_ID;

-- 2. 있었는데요 없었습니다
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME;

-- 3. 오랜 기간 보호한 동물
SELECT I.NAME, I.DATETIME
FROM ANIMAL_INS I LEFT JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE O.ANIMAL_ID IS NULL
ORDER BY I.DATETIME LIMIT 3;

-- 4. 보호소에서 중성화한 동물
SELECT O.ANIMAL_ID, O.ANIMAL_TYPE, O.NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.SEX_UPON_INTAKE LIKE "Intact%"
AND (
        O.SEX_UPON_OUTCOME LIKE "Spayed%"
        OR
        O.SEX_UPON_OUTCOME LIKE "Neutered%"
    );