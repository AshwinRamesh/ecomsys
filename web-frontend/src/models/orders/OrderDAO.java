package models.orders;

import java.sql.Connection;
import java.sql.DriverManager;
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


public class OrderDAO {
    private DataSource ds;
    private String getUserId = "SELECT id FROM users WHERE username = ? LIMIT 1";
    private String getOrdersByUser = "SELECT * FROM orders WHERE user_id = (SELECT id FROM users WHERE username = ?)";
    private String getAllOrders = "SELECT * FROM orders";
    private String createOrder = "INSERT into orders (user_id, status, shipping_address_1, shipping_address_2, city, postcode, final_cost, ship_cost, product_cost) VALUES (?,?,?,?,?,?,?,?,?)";
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

    public Integer getUserId(String username) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(getUserId);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            boolean res = rs.next();
            Integer id;
            if (res){
            	id = rs.getInt("id");
            } else {
            	id = null;
            }
            rs.close();
            ps.close();
            c.close();
            return id;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error in getting user id");
            return null;
        }
    }

    public Boolean insertOrder(Order o) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(createOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(o.getUserId()));
            ps.setString(2, o.getStatus());
            ps.setString(3, o.getShippingAddress1());
            ps.setString(4, o.getShippingAddress2());
            ps.setString(5, o.getCity());
            ps.setString(6, o.getPostCode());
            ps.setString(7, String.valueOf(o.getFinalCost()));
            ps.setString(8, String.valueOf(o.getShipCost()));
            ps.setString(9, String.valueOf(o.getCost()));


            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
            	throw new SQLException("Creating order failed");
            }
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
            	o.setOrderId(keys.getInt(1));
            } else {
            	throw new SQLException("Creating order failed");
            }
            ps.close();
            c.close();
            return true;
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            System.out.println("Error in creating order");
            return false;
        }
    }

    public List<Order> getUserOrders(String username) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(getOrdersByUser);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<Order>();
            while(rs.next()) {
            	Order o = new Order(rs.getInt("user_id"),
                        rs.getString("status"),
                        rs.getString("shipping_address_1"),
                        rs.getString("shipping_address_2"),
                        rs.getString("city"),
                        rs.getString("postcode"),
                        rs.getDouble("product_cost"),
            			rs.getDouble("ship_cost"),
            			rs.getDouble("final_cost"));
                	o.setOrderId(rs.getInt("id"));
                    orders.add(o);
            }
            rs.close();
            ps.close();
            c.close();
            return orders;
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            System.out.println("Error in getting user order");
            return null;
        }
    }

    public List<Order> getOrders() {
        try{
        	try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomsys", "root", "password");
            PreparedStatement ps = c.prepareStatement(getAllOrders);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<Order>();
            while(rs.next()) {
            	Order o = new Order(rs.getInt("user_id"),
                        rs.getString("status"),
                        rs.getString("shipping_address_1"),
                        rs.getString("shipping_address_2"),
                        rs.getString("city"),
                        rs.getString("postcode"),
                        rs.getDouble("product_cost"),
            			rs.getDouble("ship_cost"),
            			rs.getDouble("final_cost"));
                	o.setOrderId(rs.getInt("id"));
                    orders.add(o);
            }
            rs.close();
            ps.close();
            c.close();
            return orders;
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            System.out.println("Error in getting user order");
            return null;
        }
    }

    public Boolean updateStatusOrder(int orderId, String status) {
        try{
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(updateOrderStatus);
            ps.setString(1, status);
            ps.setString(2, String.valueOf(orderId));
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
