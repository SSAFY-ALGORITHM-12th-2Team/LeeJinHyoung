-- 코드를 입력하세요
SELECT order_id,product_id,date_format(out_date,"%Y-%m-%d") as out_date,
(case
    when out_date is null then "출고미정"
    when datediff(out_date,'2022-05-01')<=0 then "출고완료"
    else '출고대기'
 end
) as 출고여부
from food_order
group by order_id
order by order_id asc;
