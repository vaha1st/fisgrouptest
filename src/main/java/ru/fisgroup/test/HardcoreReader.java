package ru.fisgroup.test;

import java.sql.*;
import java.sql.Date;
import java.util.*;
/*  HardcoreReader (злостный читатель) - по аналогии со злостным курильщиком, это человек кто много читает.
*   Этот алгоритм извлекает данные из БД о записях библиотеки(таблица LIBRARY), находит id студента с наибольшим
*   количеством записей о взятии книг за указанный год. Если такой читатель найден, обращается к таблице БД STUDENTS
*   и получает полное имя по id с выводом на экран. */
public class HardcoreReader {

    // mysql конфиги
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url =
            "jdbc:mysql://localhost:3306/fisgrouptest?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String user = "admin";
    private static final String password = "password";

    // JDBC база
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    // findReader() находит элемент из списка читателей по id и возвращает индекс объекта в списке
    private static int findReader(List<Reader> readers, Integer id){
        if (id >= 0) {
            for (Reader reader : readers) {
                if (reader.getId() == id)
                    return readers.indexOf(reader);
            }
        }
        return (-1);
    }

    // findHardcoreReader() принимает на вход результат запроса "select * from library" в БД и год для поиска читателя
    private static Reader findHardcoreReader(ResultSet rs, int year) throws SQLException{

        LinkedList<Record> records = new LinkedList<>();
        List<Reader> readers = new ArrayList<Reader>();
        Reader hardcoreReader;

        // Преобразует резльтат обращения к таблице LIBRARY в список сущностей Record
        while (rs.next()){
            records.add(new Record(rs.getInt(1), rs.getInt(2), rs.getDate(3),
                    rs.getDate(4), rs.getInt(5)));
        }
        /* Проходит по каждому элементу полученного списка и записывает всех студентов удовлетворяющих условиям:
        * 1.Должна быть check_out (дата взятия книги);
        * 2.Год этой даты должен соответствовать указанному во входящих параметрах;
        * Если условия выполняются, идет проверка есть ли такой студент в списке readers. Если есть, инкрементироуется
        * счетчик для конкретного студента, если нет, создается новый элемент в списке студентов с счетчиком = 1. */
        for (Record record : records){
            if (record.getCheckOut() != null && ((Date) record.getCheckOut()).toLocalDate().getYear() == year) {
                int readerId = findReader(readers, record.getHolder());
                if (readerId >= 0) {
                    readers.get(readerId).incrCount();
                } else {
                    readers.add(new Reader(record.getHolder(), 1));
                }
            }
        }
        // Попытка поиска студента с наибольшим счетчиком. Принцип сравнения указан в компараторе класса Reader.
        // Если список readers пустой, отлавливается exception и на экран выводится сообение что читателя не найдено.
        try {
            hardcoreReader = Collections.max(readers);
        } catch (NoSuchElementException e){
            System.out.println("За текущий год нет злостных читателей");
            return null;
        }
        return hardcoreReader;
    }

    public static void main(String[] args) {

        String query = "select * from library";     // SQL запрос на получение всех записей библиотеки
        Reader hardReader;                          // Результирующий читатель
        Scanner scanner = new Scanner(System.in);   // Сканер для чтения года с консоли
        int year = 0;

        System.out.println("За какой год найти \"злостного читателя\"?");
        // Считывает с экрана пока не получит значение правильного формата
        while (true){
            try {
                year = scanner.nextInt();
                if (year != 0) break;
            } catch (InputMismatchException e){
                System.out.println("Неправильный формат ввода. Пожалуйста используйте YYYY. Например 2020");
                continue;
            } finally {
                scanner.nextLine();
            }
        }
        // Стандартное обращение к БД через JDBC
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            hardReader = findHardcoreReader(rs, year);
            if (hardReader != null) {
                rs.close();
                query = "select full_name from students where id = " + hardReader.getId();
                rs = statement.executeQuery(query);
                if (rs.next()) {
                    System.out.println("Злостный читататель за " + year + " год: " + rs.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
