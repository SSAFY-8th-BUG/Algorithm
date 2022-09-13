# 프로그래머스 sql 고득점 kit - join

# LV3 없어진 기록 찾기
-- 서브쿼리
SELECT animal_id, name
from animal_outs o
where o.animal_id not in (
    select animal_id from animal_ins
);

-- join
select o.animal_id, o.name
from animal_outs o left join animal_ins i
using (animal_id)
where i.animal_id is null
order by o.animal_id;

# LV3 있었는데요 없었습니다
SELECT animal_id, i.name
from animal_ins i join animal_outs o
using (animal_id)
where i.datetime > o.datetime
order by i.datetime;


# LV3 오랜 기간 보호한 동물(1)
SELECT i.name, i.datetime
from animal_ins i left join animal_outs o
using(animal_id)
where o.animal_id is null
order by i.datetime
limit 3;


# LV4 보호소에서 중성화한 동물
SELECT i.animal_id, i.ANIMAL_TYPE, i.name
from animal_ins i join animal_outs o
using(animal_id)
where not i.sex_upon_intake = o.SEX_UPON_OUTCOME
order by animal_id;
