-- 코드를 입력하세요
select p.ID,p.NAME,p.HOST_ID
from places p inner join
(SELECT  HOST_ID from places 
group by HOST_ID
having count(host_id)>=2) h
on(p.host_id=h.host_id)
order by ID