-- 코드를 입력하세요
SELECT distinct(car_id)
from car_rental_company_car inner join car_rental_company_rental_history
using (car_id)
where car_type="세단" and MONTH(START_date)=10
order by car_id desc;