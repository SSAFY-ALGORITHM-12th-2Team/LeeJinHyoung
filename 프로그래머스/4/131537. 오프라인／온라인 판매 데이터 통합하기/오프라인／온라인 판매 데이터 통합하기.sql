-- 코드를 입력하세요
SELECT date_format(subquery.sales_date,"%Y-%m-%d"),product_id,user_id,sales_AMOUNT
FROM (
    SELECT sales_date,product_id,user_id,sales_amount
    FROM online_sale `on`

    UNION

    SELECT sales_date,product_id,null,sales_amount
    FROM offline_sale off
) AS subquery
where year(subquery.sales_date)=2022 and month(subquery.sales_date)=3
order by sales_date asc,product_id asc,user_id asc;