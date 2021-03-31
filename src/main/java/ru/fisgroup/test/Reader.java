package ru.fisgroup.test;

// Reader класс служит для хранения информации о id студента и количества взятых книг
public class Reader implements Comparable<Reader>{
    private Integer id;
    private Integer count;

    public Reader() {
    }

    public Reader(Integer id) {
        this.id = id;
        this.count = 1;
    }

    public Reader(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void incrCount() {
        this.count++;
    }

    @Override
    public int compareTo(Reader o) {
        return this.count - o.count;
    }
}
