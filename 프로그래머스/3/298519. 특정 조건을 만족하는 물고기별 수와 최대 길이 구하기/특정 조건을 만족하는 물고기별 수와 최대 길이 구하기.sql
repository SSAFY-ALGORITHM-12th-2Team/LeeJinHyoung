-- 코드를 작성해주세요
select count(*) as FISH_COUNT,MAX(length) as MAX_LENGTH,FISH_TYPE
from fish_info
group by fish_type
having AVG(IFNULL(length, 10))>=33
order by fish_type asc;