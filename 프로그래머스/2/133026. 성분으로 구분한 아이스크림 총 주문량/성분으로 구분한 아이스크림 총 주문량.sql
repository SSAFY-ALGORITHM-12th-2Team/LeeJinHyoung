-- 코드를 입력하세요
SELECT ingredient_type,sum(total_order)
from first_half inner join icecream_info
on icecream_info.flavor= first_half.flavor
group by ingredient_type
order by sum(total_order)