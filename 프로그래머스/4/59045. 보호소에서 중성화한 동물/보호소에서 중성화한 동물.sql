-- 코드를 입력하세요
SELECT ANIMAL_ID,ins.ANIMAL_TYPE,ins.NAME
from animal_ins ins inner join animal_outs outs
using(animal_id)
where ins.sex_upon_intake like "Intact%" and (outs.sex_upon_outcome like "Spayed%" or outs.sex_upon_outcome like "Neutered%")
order by ANIMAL_ID;