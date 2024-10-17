-- 코드를 작성해주세요
# select
# year(differentiation_date) YEAR,
# # 

# ID
# from ecoli_data
# order by year(differentiation_date)
select YEAR,max_year.max-size_of_colony as YEAR_DEV,ID
from ecoli_data
inner join 
(select max(size_of_colony) as max ,year(differentiation_date) as year from ecoli_data group by year(differentiation_date)) max_year
on year(DIFFERENTIATION_DATE)=max_year.year
order by year,year_dev