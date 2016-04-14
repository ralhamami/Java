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

@Named(value = "BookDatabase")
@ApplicationScoped

public class BookDatabase implements Serializable {
    List<Book> books = new ArrayList<Book>();    
     
    public void initialize(){
        books.add(new Book(0,"Book 1","Rajiv",15.85));
        books.add(new Book(1,"Book 2","Rayan",37.25));
        books.add(new Book(2,"Book 3","Mosa",27.25));
        books.add(new Book(3,"Book 4","John",31.49));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    
}