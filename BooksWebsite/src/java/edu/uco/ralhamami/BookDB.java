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
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 *
 * @author hp main
 */
@ManagedBean
@ApplicationScoped
public class BookDB {

    
    @Resource(name = "jdbc/mydb")
    private DataSource ds;

    

    public ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList();
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
                while(result.next()) {
                    Book b = new Book();
                    b.setTitle(result.getString(2));
                    b.setAuthor(result.getString(3));
                    b.setPrice(result.getDouble(4));
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
    
    
    
    
}
