package edu.uco.ralhamami;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

@Named("Book")
@ApplicationScoped

public class Book{
    String title;
    String author;
    double price;
    int index;
    boolean editable;

    public Book(){}
    
    public Book(Book book){
       this(book.index,book.title,book.author,book.price);
    }
    public Book(int index, String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
