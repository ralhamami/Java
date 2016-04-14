package edu.uco.ralhamami;


public class Book {
    private String title;
    private String author;
    private double price;
    private boolean editable;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }


    
    public boolean equals(Book b) {
        if (b == null) return false;
        boolean equals = true;
        if (!this.title.equals(b.getTitle())) equals = false;
        if (!this.author.equals(b.getAuthor())) equals = false;
        if (this.price != b.getPrice()) equals = false;
        return equals;
    }


    
    
    public Book() {
        price = 0;
    }
    public Book(String title, String author, double price) {
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
    
}
