-- #1. 고양이와 개는 몇 마리 있을까
select animal_type, count(animal_type)
from animal_ins
group by animal_type
order by animal_type;

-- #2. 동명 동물 수 찾기
select name, count(name)
from animal_ins
group by name
having count(name)>=2
order by name;

-- #3. 입양 시각 구하기(1)
select hour(DATETIME) HOUR, count(DATETIME) COUNT
from animal_outs
where hour(DATETIME)>=9 AND hour(DATETIME)<=19
group by hour(DATETIME)
order by hour(DATETIME);

-- #4. 입양 시각 구하기(2)


