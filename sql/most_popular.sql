select name as author, count(name) as taken from author a
join book_authors ba on a.id = ba.author_id
join library l on ba.book_id = l.book_id
where l.holder != 1 and year(l.check_out) = 2021
group by name order by taken desc limit 1;