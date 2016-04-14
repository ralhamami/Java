package edu.uco.ralhamami;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "ShoppingCart")
@SessionScoped

public class ShoppingCart implements Serializable {
    List<BookItem> cart = new ArrayList<BookItem>();
    double total = 0;
    
    public void add(Book book){
        int index = 0;
        BookItem copy = new BookItem(book);
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

    public List<BookItem> getCart() {
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