-- 코드를 작성해주세요
select count(*) as FISH_COUNT,MAX(length) as MAX_LENGTH,FISH_TYPE
from fish_info
group by fish_type
having avg(if(length<=10,10,length))>=33
order by fish_type asc;