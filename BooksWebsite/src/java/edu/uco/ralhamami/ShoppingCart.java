/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.ralhamami;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.sql.DataSource;

@Named
@SessionScoped
public class ShoppingCart implements Serializable {

    private HtmlDataTable cart;
    private List<BookItem> bookItems;
    private double currentTotal;
    @Resource(name = "jdbc/mydb")
    private DataSource ds;
    private int totalQuantity;

    @PostConstruct
    public void init() {
        bookItems = new ArrayList<>();
        currentTotal = 0;

    }

    public String checkOut() throws SQLException {
        if (!bookItems.isEmpty()){
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
                PreparedStatement ps = conn.prepareStatement(
                        "insert into PURCHASES(QUANTITY,TOTALPRICE,USERNAME) values(?,?,?)", new String[]{"id"});
                ps.setInt(1, totalQuantity);
                ps.setDouble(2, currentTotal);
                ps.setString(3, FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString());

                Long id = null;
                if (ps.executeUpdate() > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (null != generatedKeys && generatedKeys.next()) {
                        id = generatedKeys.getLong(1);
                    }
                }

                if (id != null) {
                    System.out.println("id not null: " + id.intValue());
                    for (BookItem b : bookItems) {
                        ps = conn.prepareStatement(
                                "insert into PURCHASEDBOOKS(ID,TITLE,AUTHOR,PRICE,QUANTITY) values(?,?,?,?,?)");
                        ps.setInt(1, id.intValue());
                        ps.setString(2,b.getTitle());
                        ps.setString(3,b.getAuthor());
                        ps.setDouble(4,b.getPrice());
                        ps.setInt(5,b.getQuantity());
                        ps.executeUpdate();
                    }
                }
                else System.out.println("id null");
                

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
        bookItems.clear();
        totalQuantity = 0;
        currentTotal=0;
        return "checked_out";
        }
        return "index";
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(double currentTotal) {
        this.currentTotal = currentTotal;
    }

    public HtmlDataTable getCart() {
        return cart;
    }

    public void setCart(HtmlDataTable cart) {
        this.cart = cart;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

    public List<BookItem> getCartItems() {
        return bookItems;
    }

    public void updateTotal() {
        double total = 0;
        int totalquant = 0;
        for (BookItem b : bookItems) {
            total += b.getSubTotal();
            totalquant += b.getQuantity();
        }
        currentTotal = total;
        totalQuantity = totalquant;

    }

    public void addToCart(Book newBook) {
        System.out.println(newBook.getTitle() + " added");
        System.out.println(newBook.getAuthor());
        System.out.println(newBook.getPrice());
        boolean found = false;
        for (BookItem b : bookItems) {
            if (b.getTitle().matches(newBook.getTitle())) {
                b.setQuantity(b.getQuantity() + 1);
                found = true;
            }
        }
        if (!found) {
            bookItems.add(new BookItem(0, 1, newBook.getTitle(), newBook.getAuthor(), newBook.getPrice()));
        }
        updateTotal();
    }

    public void reducequantity(BookItem b) {
        System.out.println("REDUCE QTY CALLED");

        if (b.getQuantity() > 0) {
            b.reduceQ();
        }
        if (b.getQuantity() == 0) {
            bookItems.remove(b);
        }
        updateTotal();

    }
}
