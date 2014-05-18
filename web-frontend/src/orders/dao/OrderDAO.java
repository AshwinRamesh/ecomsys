package orders.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import orders.model.Order;

public class OrderDAO {
    private DataSource ds;
    private String getOrdersByUser = "SELECT * FROM orders WHERE user_id = ?";
    private String getAllOrders = "SELECT * FROM orders";
    private String createOrder = "INSERT into orders (user_id, status, shipping_address_1, shipping_address_2, city, postcode, final_cost) VALUES (?,?,?,?,?,?,?)";
    private String updateOrderStatus = "UPDATE orders SET status = ? WHERE id = ?";

    public OrderDAO() throws Exception{
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            ds = (DataSource) envCtx.lookup("jdbc/ShoppingCart");
        }catch (NamingException e){
            throw new Exception("cannot find database");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Boolean insertOrder(Order o) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(createOrder);
            ps.setString(1, String.valueOf(o.getUserId()));
            ps.setString(2, o.getStatus());
            ps.setString(3, o.getShippingAddress1());
            ps.setString(4, o.getShippingAddress2());
            ps.setString(5, o.getCity());
            ps.setString(6, o.getPostCode());
            ps.setString(7, String.valueOf(o.getFinalCost()));
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in creating order");
            return false;
        }
    }

    public List<Order> getUserOrders(int userId) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(getOrdersByUser);
            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<Order>();
            while(rs.next()) {
                orders.add(new Order(
                    rs.getInt("user_id"),
                    rs.getString("status"),
                    rs.getString("shipping_address_1"),
                    rs.getString("shipping_address_2"),
                    rs.getString("city"),
                    rs.getString("postcode"),
                    rs.getDouble("final_cost")
                ));
            }
            rs.close();
            ps.close();
            c.close();
            return orders;
        } catch (SQLException e) {
            System.out.println("Error in getting user order");
            return null;
        }
    }

    public List<Order> getOrders() {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(getAllOrders);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<Order>();
            while(rs.next()) {
                orders.add(new Order(
                    rs.getInt("user_id"),
                    rs.getString("status"),
                    rs.getString("shipping_address_1"),
                    rs.getString("shipping_address_2"),
                    rs.getString("city"),
                    rs.getString("postcode"),
                    rs.getDouble("final_cost")
                ));
            }
            rs.close();
            ps.close();
            c.close();
            return orders;
        } catch (SQLException e) {
            System.out.println("Error in getting user order");
            return null;
        }
    }

    public Boolean updateStatusOrder(Order o) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(updateOrderStatus);
            ps.setString(1, o.getStatus());
            ps.setString(2, String.valueOf(o.getOrderId()));
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in updating order status");
            return false;
        }
    }

}
