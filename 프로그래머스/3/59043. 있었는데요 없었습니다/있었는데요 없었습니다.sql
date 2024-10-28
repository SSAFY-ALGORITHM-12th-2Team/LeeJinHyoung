-- 코드를 입력하세요
SELECT animal_ins.animal_id,animal_ins.name
from animal_ins inner join animal_outs
on animal_ins.animal_id=animal_outs.animal_id
where animal_outs.datetime<=animal_ins.datetime
order by animal_ins.datetime asc