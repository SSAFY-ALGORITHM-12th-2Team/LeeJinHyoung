-- 코드를 입력하세요
SELECT YEAR(SALES_DATE) AS YEAR, 
		MONTH(SALES_DATE) AS MONTH, 
        GENDER, 
        COUNT(DISTINCT sale.USER_ID) AS USERS
from user_info `user` inner join online_sale sale
on `user`.user_id=sale.user_id
where `user`.gender is not null
group by YEAR(sale.sales_date),MONTH(sale.sales_date),`user`.gender
order by year,month,gender