# fisgrouptest
Тестовое задание для компании "Финансовые Информационные Системы"
=================================================================

Необходимо разработать некоторые части приложения для учета книг в библиотеке. Описание данных, с которыми будет работать приложение – ниже.
Приложение для учета книг в библиотеке должно:
1. Хранить названия книг, ФИО авторов, наименования издательств, год издания.
2. Учитывать имеющиеся в библиотеке экземпляры конкретной книги.
3. Учитывать студентов, которым выдавалась конкретная книга. При каждой выдаче книги студенту, фиксируется дата выдачи. При возврате – дата возврата книги.

### Задания:
1.	Опишите модель данных (в любом удобном для вас представлении) для обслуживания библиотеки. Это может быть описание таблиц с типами данных, диаграмма – что угодно.
2.	Напишите SQL-запрос, который бы возвращал самого популярного автора за год. Запрос должен основываться на модели данных, которую вы описали в задании 1. 
3.	Определите понятие «злостный читатель».  Предложите алгоритм для поиска самого злостного читателя библиотеки. На любом языке программирования опишите алгоритм поиска такого читателя. Алгоритм должен основываться на модели данных, которую вы описали в задании 1.

### Выполнено:
1. Модель в виде диаграммы, изображения доступны в [sql/](https://github.com/vaha1st/figrouptest/raw/master/sql/). Так же в этой директории вы найдете все sql скрипты для создания БД и не только.
2. [Sql-запрос](https://github.com/vaha1st/figrouptest/blob/master/sql/most_popular.sql)
3. Java код находится в [src/](https://github.com/vaha1st/figrouptest/tree/master/src/main/java/ru/fisgroup/test). Для проверки алгоритма эквивалентный [sql-запрос](https://github.com/vaha1st/figrouptest/blob/master/sql/Check_algorythm.sql)
4. Для контроля доступных экземпляров книг было создано представление book_amount. `select * from book_amount;`

В качестве СУБД выбрана mysql.

### Что бы собрать и запустить проект, должно быть установлны mysql, jdk-11, maven.

1. Инициализация БД:

    1.1. Если у вас unix система, то достаточно знать пароль от root пользователя mysql и запустить скрипт в терминале из корня проекта:
    
        bash init_mysql_db.sh
        
    1.2. Если у вас windows или др, то необходимо вручную запустить скрипты [этот](https://github.com/vaha1st/figrouptest/blob/master/sql/create_db.sql) и 
  [вот этот](https://github.com/vaha1st/figrouptest/blob/master/sql/fillup_db.sql) в mysql workbench.
  
2. Из корня собрать проект:
```
mvn package
```

3. Запустить собранное приложение:
```
java -cp target/hardcoreReader-1.0-SNAPSHOT.jar ru.fisgroup.test.HardcoreReader
```

4. Ввести желаемый год для поиска "злостного читателя". Для теста в БД было внесено немного данных. Период "работы" библотеки 2019-2021 и злостный читатель будет найден только при запросе года из этого периода. Для более глубокого тестирования можно вручную подобавлять записей в БД.
![screenshot](https://github.com/vaha1st/figrouptest/raw/master/sql/Model.png)
