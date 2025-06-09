-- 코드를 입력하세요
SELECT product.product_id,product.product_name,sum(product.price*`order`.AMOUNT) as TOTAL_SALES
from food_product as product inner join food_order as `order`
using (product_id)
where `order`.produce_date>="2022-05-01" and `order`.produce_date<="2022-05-31"
group by product_id
order by TOTAL_SALES desc,product.product_id asc;