-- 코드를 입력하세요
SELECT category,sum(sales)
from book,book_sales
where book_sales.sales_date like '2022-01%' and book.book_id=book_sales.book_id
group by category
order by category asc