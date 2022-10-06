-- #1. 없어진 기록 찾기
select outs.animal_id, outs.name
from animal_outs outs
left outer join animal_ins ins 
on outs.animal_id=ins.animal_id
where ins.animal_id is null
order by outs.animal_id;

-- #2. 있었는데요 없었습니다
select ins.animal_id, ins.name
from animal_ins ins, animal_outs outs
where ins.animal_id=outs.animal_id
and ins.datetime>outs.datetime
order by ins.datetime;

-- #3. 오랜 기간 보호한 동물(1)
select ins.name, ins.datetime
from animal_ins ins 
left outer join animal_outs outs
on ins.animal_id=outs.animal_id
where outs.datetime is null
order by ins.datetime
limit 3;

-- #4. 보호소에서 중성화한 동물
select ins.animal_id, ins.animal_type, ins.name
from animal_ins ins
left outer join animal_outs outs
on ins.animal_id=outs.animal_id
where ins.sex_upon_intake!=outs.sex_upon_outcome
order by ins.animal_id;