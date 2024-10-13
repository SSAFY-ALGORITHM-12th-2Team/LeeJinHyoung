-- 코드를 입력하세요
SELECT animal_outs.animal_id,animal_outs.name
from animal_ins right outer join  animal_outs
on animal_ins.animal_id = animal_outs.animal_id 
where animal_ins.animal_id is null
order by animal_outs.animal_id