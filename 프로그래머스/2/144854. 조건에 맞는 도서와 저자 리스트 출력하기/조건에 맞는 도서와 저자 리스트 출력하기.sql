-- 코드를 입력하세요
SELECT book_id,author_name,date_Format(published_date,"%Y-%m-%d")
as published_date
from book inner join author on author.author_id=book.author_id
where category="경제"

order by published_date