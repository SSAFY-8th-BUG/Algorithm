# 프로그래머스 sql 고득점 kit - group by

# LV2 고양이와 개는 몇 마리 있을까
SELECT animal_type, count(animal_type) count
from animal_ins
group by animal_type
having animal_type = 'Cat'
    or animal_type = 'Dog'
order by animal_type;

# LV2 동명 동물 수 찾기
SELECT name, count(name) count
from ANIMAL_INS
group by name
having count(name) > 1
order by name;

# LV2 입양 시각 구하기(1)
SELECT hour(datetime) hour, count(*) count
from animal_outs
where hour(datetime) between 9 and 19
group by hour(datetime)
order by hour;


# LV4 입양 시각 구하기(2) - 많이 어려움;;
select a.hour, ifnull(count, 0) count
from (
        select @h := @h + 1 as hour
        from animal_outs, 
            (select @h := -1) tmp 
        limit 24
    ) a
    left join
    (
        select hour(datetime) hour, count(*) count
        from animal_outs
        group by hour
    ) b
on a.hour = b.hour
