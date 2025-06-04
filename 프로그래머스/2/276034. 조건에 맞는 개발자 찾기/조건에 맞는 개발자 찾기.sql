-- 코드를 작성해주세요
select distinct developers.id,developers.email,developers.first_name,developers.last_name
from developers ,skillcodes codes
# (select * from skillcodes where CODE = 256 or CODE = 1024) codes
# on developers.skill_code=codes.code
where (developers.skill_code&codes.code)>0 and
codes.name in ("Python","C#")
order by developers.id asc;