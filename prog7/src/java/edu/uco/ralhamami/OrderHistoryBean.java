package edu.uco.ralhamami;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

/**
 *
 * @author hp main
 */
@Named(value = "orderHistoryBean")
@Dependent
public class OrderHistoryBean {
    /**
     * Creates a new instance of OrderHistoryBean
     */
    public OrderHistoryBean() {
    }
    
    @Resource(name = "jdbc/mydb")
    private DataSource ds;
    private String action;
    public ArrayList<BookItem> ItemSold(int orderID) throws SQLException {
        ArrayList<BookItem> itemsSold = new ArrayList();
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
                        "select * from PURCHASEDBOOKS where id = " + orderID);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    BookItem b = new BookItem();
                    b.setTitle(rs.getString(2));
                    b.setAuthor(rs.getString(3));
                    b.setPrice(rs.getDouble(4));
                    b.setQuantity(rs.getInt(5));
                    itemsSold.add(b);
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
        return itemsSold;
    }
    
    public ArrayList<Order> Orders() throws SQLException {
        ArrayList<Order> orders = new ArrayList();
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
                        "select * from PURCHASES where username = ?");
                String tempUser = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
                if (tempUser.contains("admin")){
                    tempUser = action;
                }
                ps.setString(1, tempUser);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    Order o = new Order();
                    o.setId(rs.getInt(1));
                    o.setTotalQuantity(rs.getInt(2));
                    o.setTotalPrice(rs.getDouble(3));
                    orders.add(o);
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
        return orders;
    }
    
    public void setAction(String action) {
		this.action = action;
	}
}
