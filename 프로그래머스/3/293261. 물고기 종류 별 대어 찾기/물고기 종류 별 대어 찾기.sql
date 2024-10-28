-- 코드를 작성해주세요

select id,fish_name,length
from fish_info i1 inner join fish_name_info i2
using(fish_type)
where i1.length=
        (SELECT MAX(I2.LENGTH)
        FROM FISH_INFO I2
        WHERE i1.FISH_TYPE = i2.FISH_TYPE)
order by id


# select ANY_VALUE(info.id) as ID ,ANY_VALUE(name_info.fish_name)as FISH_NAME,max(info.length) as LENGTH
# from fish_info info inner join fish_name_info name_info
# using (fish_type)
# group by info.fish_type