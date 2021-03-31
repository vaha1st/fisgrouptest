# Заполнение таблицы книг наименованиями
insert into books (title, year_of_publishing)
values
	("Гарри Поттер и узник Азкабана", 2019),
	("Зеленая миля", 2020),
	("Унесенные ветром", 2020),
	("Прислуга", 2016),
	("Граф Монте-Кристо", 2019)
;

# Заполнение таблицы авторов
insert into author (name)
values
	("Джоан Роулинг"),
	("Стивен Кинг"),
    ("Маргарет Митчелл"),
	("Кэтрин Стокетт"),
	("Александр Дюма")
;

# Заполнение таблицы издателей
insert into publishing (p_name)
values
	("Азбука-Аттикус"),
	("Махаон"),
	("АСТ"),
	("Иностранка"),
	("Фантом Пресс")
;

# Заполнение таблицы соответствия книга-авторы
insert into book_authors (book_id, author_id)
values
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5)
;

# Заполнение таблицы соответствия книга-издательства
insert into book_publishing (book_id, p_house_id)
values
	(1, 1),
	(1, 2),
	(2, 3),
	(3, 1),
	(3, 4),
	(4, 5),
	(5, 1)
;

# Заполнение таблицы студентов
insert into students (full_name)
values
	("ADMIN"),
	("Иван Иванов"),
	("Петр Петров"),
    ("Александра Александровна"),
    ("Евгения Евгеньевна")
;

# Заполнение таблицы учета новыми книгами от ADMIN
insert into library (book_id, check_in, holder)
values
	(1, date(sysdate()), 1),
	(1, date(sysdate()), 1),
	(1, date(sysdate()), 1),
	(1, date(sysdate()), 1),
	(2, date(sysdate()), 1),
	(2, date(sysdate()), 1),
	(2, date(sysdate()), 1),
	(3, date(sysdate()), 1),
	(3, date(sysdate()), 1),
	(4, date(sysdate()), 1),
	(4, date(sysdate()), 1),
	(4, date(sysdate()), 1),
	(4, date(sysdate()), 1),
	(5, date(sysdate()), 1),
	(5, date(sysdate()), 1),
	(5, date(sysdate()), 1)
;

# Имитация книгооборота
insert into library (book_id, check_in, check_out, holder)
values
	(1, str_to_date('01-12-2020', '%d-%m-%Y'), date(sysdate()) - 1, 2),
	(2, null, str_to_date('20-03-2021', '%d-%m-%Y'), 2),
	(3, null, str_to_date('20-03-2020', '%d-%m-%Y'), 2),
	(1, null, str_to_date('25-07-2020', '%d-%m-%Y'), 3),
	(1, date(sysdate()) - 2, null, 3),
	(2, null, str_to_date('25-07-2020', '%d-%m-%Y'), 3),
	(3, null, str_to_date('25-07-2020', '%d-%m-%Y'), 3),
    (3, date(sysdate()) - 1, null, 3),
	(4, null, str_to_date('13-04-2019', '%d-%m-%Y'), 4),
    (4, date(sysdate()) - 3, null, 4),
	(1, null, str_to_date('09-03-2020', '%d-%m-%Y'), 4),
    (1, date(sysdate()) - 4, null, 4),
	(2, null, str_to_date('13-04-2019', '%d-%m-%Y'), 4),
	(5, null, str_to_date('20-03-2021', '%d-%m-%Y'), 4),
	(5, null, str_to_date('20-03-2021', '%d-%m-%Y'), 5)
;