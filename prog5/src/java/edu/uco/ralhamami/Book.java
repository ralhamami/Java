package edu.uco.ralhamami;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

@Named("Book")
@ApplicationScoped

public class Book implements Serializable{
    String title;
    String author;
    double price;
    int index;
    
    @Resource(name = "jdbc/myDatasource")
    private DataSource ds;
    
    public Book(){}
    
    public Book(Book book){
       this(book.index,book.title,book.author,book.price);
    }
    public Book(int index, String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
        public List<Book> getBooks() throws SQLException {

        if (ds == null) {
            throw new SQLException("ds is null; Can't get data source");
        }

        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }

        List<Book> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select AUTHOR, TITLE, PRICE FROM BOOKS"
            );

            // retrieve customer data from database
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Book c = new Book();
                c.setAuthor(result.getString("AUTHOR"));
                c.setTitle(result.getString("TITLE"));
                c.setPrice(result.getDouble("PRICE"));
                list.add(c);
            }

        } finally {
            conn.close();
        }

        return list;
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
    
    
}
