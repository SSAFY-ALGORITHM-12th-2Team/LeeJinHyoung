-- 코드를 입력하세요
SELECT in.ANIMAL_ID,in.name
from animal_ins as `in` inner join animal_outs as `out`
using (animal_id)
where out.DATETIME-in.DATETIME
order by out.datetime-in.datetime desc
limit 2;