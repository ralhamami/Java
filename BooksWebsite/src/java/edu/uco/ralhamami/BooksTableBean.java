/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.ralhamami;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@ManagedBean
@SessionScoped
public class BooksTableBean {

    @Resource(name = "jdbc/mydb")
    private DataSource ds;
    private Book editBook;
    private ArrayList<Book> books;
    private HtmlInputText titleInput;
    private HtmlInputText authorInput;
    private HtmlInputText priceInput;
    private String titleText;
    private String authorText;
    private double priceText;
    @NotEmpty
    private String insertTitle;
    @NotEmpty
    private String insertAuthor;
    @NotNull
    private double insertPrice;
    boolean adding;

    public String getInsertTitle() {
        return insertTitle;
    }

    public void setInsertTitle(String insertTitle) {
        this.insertTitle = insertTitle;
    }

    public String getInsertAuthor() {
        return insertAuthor;
    }

    public void setInsertAuthor(String insertAuthor) {
        this.insertAuthor = insertAuthor;
    }

    public double getInsertPrice() {
        return insertPrice;
    }

    public void setInsertPrice(double insertPrice) {
        this.insertPrice = insertPrice;
    }
    

    public String getAuthorText() {
        return authorText;
    }

    public void setAuthorText(String authorText) {
        this.authorText = authorText;
    }

    public double getPriceText() {
        return priceText;
    }

    public void setPriceText(double priceText) {
        this.priceText = priceText;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public Book getEditBook() {
        return editBook;
    }

    public void setEditBook(Book editBook) {
        this.editBook = editBook;
    }

    public HtmlInputText getTitleInput() {
        return titleInput;
    }

    public void setTitleInput(HtmlInputText titleInput) {
        this.titleInput = titleInput;
    }

    public HtmlInputText getAuthorInput() {
        return authorInput;
    }

    public void setAuthorInput(HtmlInputText authorInput) {
        this.authorInput = authorInput;
    }

    public HtmlInputText getPriceInput() {
        return priceInput;
    }

    public void setPriceInput(HtmlInputText priceInput) {
        this.priceInput = priceInput;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public String edit(Book b) {
        titleText = b.getTitle();
        authorText = b.getAuthor();
        priceText = b.getPrice();
        editBook = b;
        return null;
    }

    public void save(Book oldBook) throws SQLException {
        String oldTitle = oldBook.getTitle();
        String oldAuthor = oldBook.getAuthor();
        double oldPrice = oldBook.getPrice();
        String newTitle = titleInput.getValue().toString();
        String newAuthor = authorInput.getValue().toString();
        double newPrice = Double.parseDouble(priceInput.getValue().toString());
        if (ds == null) {
            throw new SQLException("Cannot get DataSource");
        }

        Connection conn = ds.getConnection();
        if (conn == null) {
            throw new SQLException("Cannot get connection");
        }

        try {
            conn.setAutoCommit(false);
            boolean committed = false;
            try {
                PreparedStatement authenticateQuery = conn.prepareStatement(
                        "UPDATE BOOKS "
                        + "SET TITLE=?, AUTHOR=?, PRICE=?"
                        + "WHERE TITLE=?");
                authenticateQuery.setString(1, newTitle);
                authenticateQuery.setString(2, newAuthor);
                authenticateQuery.setDouble(3, newPrice);
                authenticateQuery.setString(4, oldTitle);

                authenticateQuery.executeUpdate();

                conn.commit();
                committed = true;
            } finally {
                if (!committed) {
                    conn.rollback();
                }
            }
        } finally {
            conn.close();
        }

        editBook = null;
    }
    
    public void add() throws SQLException {
        if (ds == null) {
            throw new SQLException("Cannot get DataSource");
        }

        Connection conn = ds.getConnection();
        if (conn == null) {
            throw new SQLException("Cannot get connection");
        }

        try {
            conn.setAutoCommit(false);
            boolean committed = false;
            try {
                PreparedStatement authenticateQuery = conn.prepareStatement(
                        "INSERT INTO BOOKS(TITLE,AUTHOR,PRICE) values(?,?,?)");
                authenticateQuery.setString(1, insertTitle);
                authenticateQuery.setString(2, insertAuthor);
                authenticateQuery.setDouble(3, insertPrice);

                authenticateQuery.executeUpdate();

                conn.commit();
                committed = true;
            } finally {
                if (!committed) {
                    conn.rollback();
                }
            }
        } finally {
            conn.close();
            titleInput.setSubmittedValue(null);
            authorInput.setSubmittedValue(null);
            priceInput.setSubmittedValue(null);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Book Added!!!",null);
            FacesContext.getCurrentInstance().addMessage("formAdd", msg);
        }
    }

    public void delete(Book b) throws SQLException {
        if (ds == null) {
            throw new SQLException("Cannot get DataSource");
        }

        Connection conn = ds.getConnection();
        if (conn == null) {
            throw new SQLException("Cannot get connection");
        }

        try {
            conn.setAutoCommit(false);
            boolean committed = false;
            try {
                PreparedStatement authenticateQuery = conn.prepareStatement(
                        "DELETE FROM BOOKS "
                        + "WHERE TITLE=?");
                authenticateQuery.setString(1, b.getTitle());

                authenticateQuery.executeUpdate();

                conn.commit();
                committed = true;
            } finally {
                if (!committed) {
                    conn.rollback();
                }
            }
        } finally {
            conn.close();
        }
    }

    public ArrayList<Book> getBooks() throws SQLException {
        books = new ArrayList();
        if (ds == null) {
            throw new SQLException("Cannot get DataSource");
        }

        Connection conn = ds.getConnection();
        if (conn == null) {
            throw new SQLException("Cannot get connection");
        }

        try {
            conn.setAutoCommit(false);
            boolean committed = false;
            try {
                PreparedStatement authenticateQuery = conn.prepareStatement(
                        "select * from BOOKS");

                ResultSet result = authenticateQuery.executeQuery();
                while (result.next()) {
                    Book b = new Book();
                    b.setTitle(result.getString(2));
                    b.setAuthor(result.getString(3));
                    b.setPrice(result.getDouble(4));
                    if (b.equals(editBook)) {
                        b.setEditable(true);
                    }
                    books.add(b);
                }

                conn.commit();
                committed = true;
            } finally {
                if (!committed) {
                    conn.rollback();
                }
            }
        } finally {
            conn.close();
        }
        return books;

    }

    public boolean isAdding() {
        return adding;
    }

    public void setAdding(boolean adding) {
        this.adding = adding;
    }
    
    

}
