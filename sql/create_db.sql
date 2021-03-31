# Создание БД, переключение на нее и создание пользователя
create database if not exists fisgrouptest;
create user if not exists 'admin'@'localhost' identified by 'password';
GRANT ALL ON fisgrouptest.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
use fisgrouptest;

# Отключение проверки внешних ключей что бы беспрепятсвенно удалять таблицы
set foreign_key_checks = 0;

# Создание таблицы книг
drop table if exists books cascade;
create table books (
	id int auto_increment,
	title nvarchar(100) not null,
	year_of_publishing year(4),
    primary key(id)
);

# Создание таблицы авторов
drop table if exists author cascade;
create table author (
	id int auto_increment,
	name nvarchar(100) not null,
    primary key(id)
);

# Создание таблицы издательств
drop table if exists publishing cascade;
create table publishing (
	id int auto_increment,
	p_name nvarchar(100) not null,
    primary key(id)
);

# Создание таблицы соответствия авторов к книгам
drop table if exists book_authors cascade;
create table book_authors (
	book_id int,
	author_id int,
    foreign key (book_id) references books (id),
    foreign key (author_id) references author (id)
);

# Создание таблицы соответствия издательств к книгам
drop table if exists book_publishing cascade;
create table book_publishing (
	book_id int,
	p_house_id int,
    foreign key (book_id) references books (id),
    foreign key (p_house_id) references publishing (id)
);

# Создание таблицы студентов. Служебное имя для добавления новых книг ADMIN, id 1
drop table if exists students cascade;
create table students (
	id int auto_increment,
    full_name nvarchar(100) not null,
    primary key (id)
);

# Создание таблицы учета в библиотеке.
drop table if exists library cascade;
create table library (
	quiery_id int auto_increment,
    book_id int,
    check_in date,
    check_out date,
    holder int,
    primary key (quiery_id),
    foreign key (book_id) references books (id),
    foreign key (holder) references students (id)
);

# Создание представления с информацией о количестве экземпляров книг
drop view if exists book_amount;
create view book_amount
	as select id, title, count(check_in) - count(check_out)
		as remain from books b join library l on b.id = l.book_id
		group by id order by id;

# Возврат проверки на внешний ключ
set foreign_key_checks = 1;