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

@Named(value = "ShoppingCart")
@SessionScoped

public class ShoppingCart implements Serializable {
    @Resource(name = "jdbc/myDatasource")
    private DataSource ds;
    
    List<BookItem> cart = new ArrayList<BookItem>();
    double total = 0;
    
    public void add(String book) throws SQLException{
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
                    "select AUTHOR, TITLE, PRICE FROM BOOKS WHERE TITLE=?"
            );
            ps.setString(1, book);
            // retrieve customer data from database
            ResultSet result = ps.executeQuery();

            Book c = new Book();
            while (result.next()) {
                c.setAuthor(result.getString("AUTHOR"));
                c.setTitle(result.getString("TITLE"));
                c.setPrice(result.getDouble("PRICE"));
                list.add(c);
            }

        } finally {
            conn.close();
        }
        int index = 0;
        BookItem copy = new BookItem(c);
        boolean newItem = true;
        for (int i=0; i<cart.size(); i++){
            if (cart.get(i).title.equals(copy.title)){
                newItem=false;
                index = i;
            }
        }
        if (newItem)
            cart.add(copy);
        else{
            cart.get(index).subtotal += book.price;
            cart.get(index).quantity += 1;
        }
        total += copy.price;
        newItem=true;
    }
    
    public void remove(BookItem book){
        total -= book.price;
        book.subtotal -= book.price;
        if (book.quantity == 1)
            cart.remove(book);
        if (book.quantity > 0)
            book.quantity-=1;
    }
    
     @Resource(name = "jdbc/myDatasource")
     private DataSource ds;
     public List<BookItem> getCart(){
         return cart;
     }

    public void setCart(List<BookItem> cart) {
        this.cart = cart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}