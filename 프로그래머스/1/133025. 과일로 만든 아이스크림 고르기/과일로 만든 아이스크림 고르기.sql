-- 코드를 입력하세요
SELECT first_half.FLAVOR
from first_half,icecream_info
where total_order>3000 and first_half.flavor=icecream_info.flavor and ingredient_type="fruit_based"
order by total_order desc