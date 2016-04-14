package edu.uco.ralhamami;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.System.out;
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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

@Named("DB")
@RequestScoped

public class DB implements Serializable{
    @Resource(name = "jdbc/myDatasource")
    private DataSource ds;
    String titleMatch;
    String title;
    String author;
    Double price;
    public List<Book> getBooks() throws SQLException {

        if (ds == null) {
            throw new SQLException("ds is null; Can't get data source");
        }

        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }
        
        List<Book> books = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select AUTHOR, TITLE, PRICE, EDITABLE FROM BOOKS"
            );

            // retrieve customer data from database
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Book c = new Book();
                c.setAuthor(result.getString("AUTHOR"));
                c.setTitle(result.getString("TITLE"));
                c.setPrice(result.getDouble("PRICE"));
                c.setEditable(result.getBoolean("EDITABLE"));
                books.add(c);
            }

        } finally {
            conn.close();
        }
        
        return books;
    }
    public void setBooks(Book book, int index) throws SQLException{
        if (ds == null) {
            throw new SQLException("ds is null; Can't get data source");
        }

        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE BOOKS SET TITLE=?, AUTHOR=?, PRICE=?, EDITABLE=false WHERE TITLE = ?"
            );
            ps.setString(1, book.title);
            ps.setString(2, book.author);
            ps.setDouble(3, book.price);
            ps.setString(4, book.title);
            //FacesMessage lnameErr = new FacesMessage(FacesMessage.SEVERITY_ERROR,book.title+"\n"+book.author+"\n"+book.price,null);
            //FacesContext.getCurrentInstance().addMessage("", lnameErr);

            // retrieve customer data from database
            ps.execute();
        } finally {
            conn.close();
        }
		//book.setEditable(false);
    }
    public String editAction(Book book) throws SQLException{
        
        if (ds == null) {
            throw new SQLException("ds is null; Can't get data source");
        }

        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE BOOKS SET EDITABLE = true WHERE TITLE = ?"
            );
            ps.setString(1, book.title);

            // retrieve customer data from database
            ps.execute();
        } finally {
            conn.close();
        }
		//book.setEditable(true);
		return null;
	}

    public void setBookItem() throws SQLException {
        if (ds == null) {
            throw new SQLException("ds is null; Can't get data source");
        }

        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO BOOKS (TITLE, AUTHOR, PRICE) VALUES(?,?,?)"
            );
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setDouble(3, price);

            // retrieve customer data from database
            ps.execute();
        } finally {
            conn.close();
        }
		//book.setEditable(true);
    }
    
    public void delete(Book book) throws SQLException {
        if (ds == null) {
            throw new SQLException("ds is null; Can't get data source");
        }

        Connection conn = ds.getConnection();

        if (conn == null) {
            throw new SQLException("conn is null; Can't get db connection");
        }

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM BOOKS WHERE TITLE = ?"
            );
            ps.setString(1, book.title);

            // retrieve customer data from database
            ps.execute();
        } finally {
            conn.close();
        }
		//book.setEditable(true);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}
