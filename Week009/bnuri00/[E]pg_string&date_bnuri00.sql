# 프로그래머스 sql 고득점 kit - string&date

# LV2 루시와 엘라 찾기
SELECT ANIMAL_ID,	NAME,	SEX_UPON_INTAKE
from ANIMAL_INS
where name in ( "Lucy", "Ella", "Pickle", "Rogan", "Sabrina", "Mitty")
order by ANIMAL_ID;

# LV2 이름에 el이 들어가는 동물 찾기
SELECT ANIMAL_ID,	NAME
from ANIMAL_INS
where animal_type = "dog"
and name like "%el%"
order by NAME;

# LV2 중성화 여부 파악하기
SELECT ANIMAL_ID,	NAME,	if(SEX_UPON_INTAKE like "Neutered%" or SEX_UPON_INTAKE like "Spayed%","O","X" ) "중성화"
from ANIMAL_INS
order by ANIMAL_ID;

# LV3 오랜 기간 보호한 동물(2)
SELECT i.ANIMAL_ID,	i.NAME 
-- TIMESTAMPDIFF(MINUTE, i.datetime, o.dateTime)
from ANIMAL_INS i join ANIMAL_OUTS o
using(ANIMAL_ID)
order by TIMESTAMPDIFF(MINUTE, i.datetime, o.dateTime) desc
limit 2
;


# LV2 DATETIME에서 DATE로 형 변환
SELECT ANIMAL_ID,	NAME, date_format(datetime, '%Y-%m-%d') "날짜"
from ANIMAL_INS
order by animal_id;
