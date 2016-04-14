/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.ralhamami;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

/**
 *
 * @author hp main
 */
@ManagedBean
@RequestScoped
public class IndexBean {

    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
    }
    private HtmlDataTable bookTable;
    private HtmlDataTable cartTable;

    public HtmlDataTable getBookTable() {
        return bookTable;
    }

    public HtmlDataTable getCartTable() {
        return cartTable;
    }

    public void setCartTable(HtmlDataTable cartTable) {
        this.cartTable = cartTable;
    }

    public void setBookTable(HtmlDataTable bookTable) {
        this.bookTable = bookTable;
    }
    
    public Book getSelectedBook() {
        
        return (Book) bookTable.getRowData();
    }
    public int getSelectedBookItem() {
        return  cartTable.getRowIndex();
    }
    
    
}
