package ru.fisgroup.test;

import java.util.Date;

// Record класс сущность для хранения строки из SQL таблицы Library
public class Record {
    private Integer id;
    private Integer bookID;
    private Date checkIn;
    private Date checkOut;
    private Integer holder;

    public Record() {
    }

    public Record(Integer id, Integer bookID, Date checkIn, Date checkOut, Integer holder) {
        this.id = id;
        this.bookID = bookID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.holder = holder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getHolder() {
        return holder;
    }

    public void setHolder(Integer holder) {
        this.holder = holder;
    }
}
