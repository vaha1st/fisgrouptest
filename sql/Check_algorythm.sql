select * from book_amount;
select * from library;
select full_name, count(full_name) as books from library l join students s on l.holder = s.id
where year(check_out) = 2019
group by full_name order by full_name desc;