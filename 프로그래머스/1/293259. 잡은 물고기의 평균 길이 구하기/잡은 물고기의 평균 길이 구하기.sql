-- 코드를 작성해주세요
select ROUND(sum(ifnull(length,10))/count(ID),2) as AVERAGE_LENGTH from fish_info 