-- 코드를 작성해주세요
select ITEM_ID,ITEM_NAME
from (select ITEM_ID from item_tree where PARENT_ITEM_ID is null) as root
inner join ITEM_INFO
using (ITEM_ID);